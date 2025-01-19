package model;

import com.Prog_3_Projektarbeit.generated.tables.daos.BudgetDao;
import com.Prog_3_Projektarbeit.generated.tables.pojos.Budget;
import com.Prog_3_Projektarbeit.generated.tables.daos.HaveDao;
import com.Prog_3_Projektarbeit.generated.tables.pojos.Have;
import views.BudgetView;

public class BudgetModel {
    private final BudgetDao budgetDao;
    private final HaveDao haveDao;
    private BudgetView view;
    private String username;

    public BudgetModel(BudgetDao budgetDao, HaveDao haveDao, BudgetView view) {
        this.budgetDao = budgetDao;
        this.haveDao = haveDao;
        this.view = view;
    }
    public int addBudget(String name, float amount, String username) {
        Budget newBudget = new Budget();
        newBudget.setBudgetName(name);
        newBudget.setAmmount(amount);
        budgetDao.insert(newBudget);

        int getBudgetId = (int) newBudget.getBudgetId();
        Have newHave = new Have();
        newHave.setUserName(username);
        newHave.setBudgetId(getBudgetId);
        haveDao.insert(newHave);
        if (view != null){
            view.updateBudgetList(username);
        }

        return getBudgetId;

    }

    public void setCurrentUser(String username) {
       this.username = username;
    }
}
