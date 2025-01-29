package model;

import com.Prog_3_Projektarbeit.generated.tables.daos.BudgetDao;
import com.Prog_3_Projektarbeit.generated.tables.pojos.Budget;
import com.Prog_3_Projektarbeit.generated.tables.daos.HaveDao;
import com.Prog_3_Projektarbeit.generated.tables.pojos.Have;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presenters.BudgetPresenter;


import java.util.ArrayList;
import java.util.List;

public class BudgetModel {
    private final BudgetDao budgetDao;
    private final HaveModel haveModel ;

    public String getUsername() {
        return username;
    }

    private final HaveDao haveDao;
    private BudgetPresenter presenter;
    private String username;

    public BudgetModel(BudgetDao budgetDao, HaveDao haveDao, BudgetPresenter presenter, HaveModel haveModel) {
        this.budgetDao = budgetDao;
        this.haveDao = haveDao;
        this.presenter = presenter;
        this.haveModel = haveModel;
    }

    public int addBudget(String name, float amount, String username) {
        Budget newBudget = new Budget();
        newBudget.setBudgetName(name);
        newBudget.setAmmount(amount);
        budgetDao.insert(newBudget);

        int getBudgetId = newBudget.getBudgetId();
        Have newHave = new Have();
        newHave.setUserName(username);
        newHave.setBudgetId(getBudgetId);
        haveDao.insert(newHave);
        if (presenter != null) {
            presenter.showBudgets(username);
        }

        return getBudgetId;

    }

    public void setCurrentUser(String username) {
        this.username = username;
    }

    public void deleteBudget(int budgetId) {
        budgetDao.deleteById(budgetId);
        haveModel.deleteByBudgetId(budgetId);
        presenter.showBudgets(username);
    }

    public ObservableList<String> getBudgetList(String username) {

            ObservableList<String> budgets;
            List<String> budgetDetails = new ArrayList<>();
            List<Integer> budgetIds = new ArrayList<>(haveDao.fetchByUserName(username).stream().map(Have::getBudgetId).toList());
            for (int id : budgetIds) {
                Budget budget = budgetDao.fetchOneByBudgetId(id);
                if (budget != null) {
                    budgetDetails.add(budget.getBudgetName() + " (" + budget.getBudgetId() + ") - " + presenter.getRestAmmount(budget.getBudgetId()));

                }
            }
            budgets = FXCollections.observableArrayList(budgetDetails);
            return budgets;

    }
    public Float getBudgetAmmount(int budgetId) {
        return budgetDao.fetchOneByBudgetId(budgetId).getAmmount();
    }
}
