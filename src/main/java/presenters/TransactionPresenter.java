package presenters;

import com.Prog_3_Projektarbeit.generated.tables.daos.HaveDao;
import com.Prog_3_Projektarbeit.generated.tables.daos.TransactionsDao;
import com.Prog_3_Projektarbeit.generated.tables.daos.UserDao;
import com.Prog_3_Projektarbeit.generated.tables.pojos.Transactions;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.HaveModel;
import model.TransactionModel;
import views.TransactionView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * <h2>Klasse zum Übergeben der Transaktionen an die View</h2><br>
 * <h3>Hier werden alle Methoden instanziiert und an die TransactionView übergeben</h3>
 */
public class TransactionPresenter {

    TransactionModel transactionModel;
    private TransactionView view;
    private TransactionsDao transactionsDao;
    private HaveDao haveDao;
    private HaveModel haveModel;
    private int budgetId;
    BudgetPresenter budgetPresenter;
    String Username;
    Stage stage;

    public TransactionPresenter(UserDao userDao,BudgetPresenter budgetPresenter, HaveModel haveModel, Stage stage) {


        this.transactionsDao = new TransactionsDao(userDao.configuration());
        this.haveDao = new HaveDao(userDao.configuration());
        this.view = new TransactionView(this,budgetPresenter);
        this.stage = stage;
        this.budgetPresenter = budgetPresenter;
        this.transactionModel = new TransactionModel(haveDao,transactionsDao,this, haveModel);
    }

    /** <h3>Übergibt die Transaktionen, die verlangt werden </h3>
     *
     * @param budgetId Aus welchem Budget die Tranaktion kommt
     * @param username Welcher Nutzer die Transaktion getätigt hat
     */
    public void showTransactions(int budgetId, String username) {
        this.Username = username;
        this.budgetId = budgetId;
        transactionModel.setCurrentUser(Username, budgetId);
        view.setStageMaximized(stage);
        view.show(stage);

    }

    /** <h3>Übergibt die hinzuzufügende Transaktion</h3>
     *
     * @param transactionName Bezeichnung der Transaktion
     * @param transactionAmount Betrag der Transaktion
     * @param description Beschreibung der Transaktion
     * @param transactionDate Datum der Transaktion
     */
    public void addTransaction (String transactionName, float transactionAmount, String description, LocalDate transactionDate) {

        if(getRestAmmount(budgetId) < (getBudgetAmmount()*0.2)){
            view.lowBudgetWarning();

        }
        if (transactionAmount > getBudgetAmmount()) {
            view.highBudgetWarning();
            view.setStageMaximized(stage);
        }else if (getAllTransactions()+transactionAmount > 0) {
            view.highBudgetWarning();
            view.setStageMaximized(stage);

        }else{
            transactionModel.addTransaction(transactionName, transactionAmount,description,transactionDate );
        }
    }

    /**
     *
     * @return Die Liste der Transaktionen
     */
    public ObservableList<String> getTransactionList() {
        return transactionModel.getTransactionList();
    }

    public String getTransactionDescription(int transactionId) {
        Transactions transaction = transactionModel.getTransactionById(transactionId);
        return (transaction != null) ? transaction.getDescription() : "No description available.";
    }

    /**
     * <h3>Geht zum vorherigen Fenster</h3>
     */
    public void back() {
        budgetPresenter.showBudgets(this.Username);

    }

    /**
     * <h3>Übergeber der zu Löschenden Transaktion</h3>
     */
    public void deleteTransaction(int transactionID, int budgetID) {
        transactionModel.deleteTransaction(transactionID, budgetID);
    }

    public int getBudgetId() {
        return budgetId;
    }

    /**
     * @return Eine Liste aller Transaktionen
     */
    public float getAllTransactions() {
        float sum = 0;
        List<Transactions> transactions = transactionsDao.fetchByBudgetId(budgetId);
        for (Transactions transaction : transactions) {
            sum = sum + transaction.getAmmount().floatValue();
        }
        return sum;
    }


    public Float getBudgetAmmount() {
        return budgetPresenter.getBudgetAmmount(budgetId);
    }

    public Float getRestAmmount(int budgetId) {
        this.budgetId = budgetId;
        return view.getrestAmmount(); }

    public List<String> getAllUsersforBudget() {
        List<String> users = budgetPresenter.getAllUsersforBudget(budgetId);
        return users;
    }

    public void changeDescription(int transactionId, String description) {
        transactionModel.changeDescription(transactionId, description);
    }

}
