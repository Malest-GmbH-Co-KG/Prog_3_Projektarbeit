package presenters;

import javafx.stage.Stage;
import com.Prog_3_Projektarbeit.generated.tables.daos.BudgetDao;
import presenters.LoginPresenter;
import com.Prog_3_Projektarbeit.generated.tables.daos.UserDao;
import views.BudgetView;


public class BudgetPresenter {
    private  UserDao UserDao;
    private BudgetDao budgetDao;
    private BudgetView view;
    Stage stage = new Stage();
    LoginPresenter loginPresenter;

    public BudgetPresenter(UserDao userDao, LoginPresenter loginPresenter) {
        this.UserDao = userDao;
        this.budgetDao = new BudgetDao(userDao.configuration());
        this.view = new BudgetView(this);
        this.loginPresenter = loginPresenter;

    }
    public void showBudgets(String username) {

        view.show(stage);//zeigt die Budgets des angemeldeten Users
    }


    public void addBudget(String budgetName, double budgetAmount) {
    }

    public void showMovementsForBudget(String selectedBudget) {
    }

    public void loadBudgets() {
    }

    public void back() {
        loginPresenter.start(); //startet die Anwendung bzw die GUI
        stage.close();

    }
}
