package presenters;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import com.Prog_3_Projektarbeit.generated.tables.daos.BudgetDao;
import model.BudgetModel;
import com.Prog_3_Projektarbeit.generated.tables.daos.HaveDao;
import com.Prog_3_Projektarbeit.generated.tables.daos.UserDao;
import views.BudgetView;
import com.Prog_3_Projektarbeit.generated.tables.pojos.Budget;
import com.Prog_3_Projektarbeit.generated.tables.pojos.Have;
import java.util.ArrayList;
import java.util.List;


public class BudgetPresenter {
    private  UserDao UserDao;
    private BudgetDao budgetDao;
    private HaveDao haveDao;
    private BudgetView view;
    Stage stage = new Stage();
    LoginPresenter loginPresenter;
    BudgetModel budgetModel;
    String Username;

    public BudgetPresenter(UserDao userDao, LoginPresenter loginPresenter) {
        this.UserDao = userDao;
        this.budgetDao = new BudgetDao(userDao.configuration());
        this.haveDao = new HaveDao(userDao.configuration());
        this.view = new BudgetView(this);
        this.loginPresenter = loginPresenter;
        this.budgetModel = new BudgetModel(budgetDao,haveDao,view);

    }

    public void showBudgets(String username) {

        this.Username = username;
        view.show(stage);//zeigt die Budgets des angemeldeten Users
    }


    public void addBudget(String budgetName, float budgetAmount) {
        budgetModel.addBudget(budgetName,budgetAmount,Username);
    }

    public void showTransacionForBudget(String selectedBudget) {
    }

    public void loadBudgets() {
    }

    public ObservableList<String> getBudgetList(){
        //Methode nur für budgetList in der BudgetView Klasse
        ObservableList<String> budgets;
        List<String> budgetNames = new ArrayList<>();
        List<Integer> budgetIds = new ArrayList<>(haveDao.fetchByUserName(Username).stream().map(Have::getBudgetId).toList());
        int id;
        for(int i=0;budgetIds.size() > i;i++) {
            id = budgetIds.get(i);
            budgetNames.add(budgetDao.fetchByBudgetId(id).stream().map(Budget::getBudgetName).toList().getFirst());
        }
        budgets = FXCollections.observableArrayList(budgetNames);
        return budgets;
    }

    public void back() {
        loginPresenter.start(); //startet die Anwendung bzw die GUI
        stage.close();

    }
}
