package presenters;


import javafx.collections.ObservableList;
import javafx.stage.Stage;
import com.Prog_3_Projektarbeit.generated.tables.daos.BudgetDao;
import model.BudgetModel;
import com.Prog_3_Projektarbeit.generated.tables.daos.HaveDao;
import com.Prog_3_Projektarbeit.generated.tables.daos.UserDao;
import model.HaveModel;
import views.BudgetView;



public class BudgetPresenter {
    private  UserDao UserDao;
    private BudgetDao budgetDao;
    private HaveDao haveDao;
    private BudgetView view;
    Stage stage;
    LoginPresenter loginPresenter;
    BudgetModel budgetModel;
    String Username;
    private HaveModel haveModel;
    private TransactionPresenter transactionPresenter;

    public BudgetPresenter(UserDao userDao, LoginPresenter loginPresenter, HaveModel haveModel, Stage stage) {
        this.UserDao = userDao;
        this.budgetDao = new BudgetDao(userDao.configuration());
        this.haveDao = new HaveDao(userDao.configuration());
        this.view = new BudgetView(this);
        this.stage = stage;
        this.loginPresenter = loginPresenter;
        this.budgetModel = new BudgetModel(budgetDao,haveDao,this, haveModel);
        this.transactionPresenter = new TransactionPresenter(userDao,this, haveModel, stage);
        view.setTransactionPresenter(transactionPresenter);

    }



    public void showBudgets(String username) {

        this.Username = username;
        budgetModel.setCurrentUser(Username);
        view.show(stage);//zeigt die Budgets des angemeldeten Users
    }


    public void addBudget(String budgetName, float budgetAmount) {
        budgetModel.addBudget(budgetName,budgetAmount,Username);
    }

    public ObservableList<String> getBudgetList() {
        return budgetModel.getBudgetList(Username);
    }


    public void back() {
        loginPresenter.startBudget(this); //startet die Anwendung bzw die GUI
        stage.close();

    }

    public void deleteBudget(int budgetId) {
        budgetModel.deleteBudget(budgetId);

    }


    public String getUsername() {
        return Username;
    }

    public Float getBudgetAmmount(int budgetId) {
        return budgetModel.getBudgetAmmount(budgetId);
    }

    public Float getRestAmmount(int budgetId){ return transactionPresenter.getRestAmmount(budgetId);}
}
