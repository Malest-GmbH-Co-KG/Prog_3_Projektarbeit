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
        this.view = new TransactionView(this);
        this.stage = stage;
        this.budgetPresenter = budgetPresenter;
        this.transactionModel = new TransactionModel(haveDao,transactionsDao,this, haveModel);
    }

    public void showTransactions(int budgetId, String username) {
        this.Username = username;
        this.budgetId = budgetId;
        transactionModel.setCurrentUser(Username, budgetId);
        view.show(stage);
    }

    public void loadTransactions() {}


    public void addTransaction (String transactionName, BigDecimal transactionAmount, String description, LocalDate transactionDate) {
        transactionModel.addTransaction(transactionName, transactionAmount,description,transactionDate );
    }
    public ObservableList<String> getTransactionList() {
        return transactionModel.getTransactionList();
    }
    public String getTransactionDescription(int transactionId) {
        Transactions transaction = transactionModel.getTransactionById(transactionId);
        return (transaction != null) ? transaction.getDescription() : "No description available.";
    }

    public void back() {
        budgetPresenter.showBudgets(this.Username);

    }
    public void deleteTransaction(int transactionID, int budgetID) {
        transactionModel.deleteTransaction(transactionID, budgetID);
    }

    public int getBudgetId() {
        return budgetId;
    }

    public BigDecimal getAllTransactions() {
        BigDecimal sum = new BigDecimal(0);
        List<Transactions> transactions = transactionsDao.fetchByBudgetId(budgetId);
        for (Transactions transaction : transactions) {
            sum = sum.add(transaction.getAmmount());
        }
        return sum;
    }


    public Float getBudgetAmmount() {
        return budgetPresenter.getBudgetAmmount(budgetId);
    }

    public Float getRestAmmount(int budgetId) {
        this.budgetId = budgetId;
        return view.getrestAmmount(); }
}
