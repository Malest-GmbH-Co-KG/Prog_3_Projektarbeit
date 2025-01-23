package presenters;

import com.Prog_3_Projektarbeit.generated.tables.User;
import com.Prog_3_Projektarbeit.generated.tables.daos.HaveDao;
import com.Prog_3_Projektarbeit.generated.tables.daos.TransactionsDao;
import com.Prog_3_Projektarbeit.generated.tables.daos.UserDao;
import com.Prog_3_Projektarbeit.generated.tables.pojos.Transactions;
import com.Prog_3_Projektarbeit.generated.tables.pojos.Have;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.HaveModel;
import model.TransactionModel;
import views.TransactionView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionPresenter {
    private UserDao UserDao;
    TransactionModel transactionModel;
    private TransactionView view;
    private TransactionsDao transactionsDao;
    private HaveDao haveDao;
    private HaveModel haveModel;
    LoginPresenter loginPresenter;
    String Username;
    LocalDate date;
    Stage stage;

    public TransactionPresenter(UserDao userDao,LoginPresenter loginPresenter, HaveModel haveModel, Stage stage) {
        this.UserDao = userDao;
        this.transactionsDao = new TransactionsDao(userDao.configuration());
        this.haveDao = new HaveDao(userDao.configuration());
        this.view = new TransactionView(this);
        this.stage = stage;
        this.loginPresenter = loginPresenter;
        this.transactionModel = new TransactionModel(haveDao,transactionsDao,this, haveModel);
    }

    public void showTransactions(String username) {
        this.Username = username;
        transactionModel.setCurrentUser(Username);
        view.show(stage);
    }

    public void loadTransactions() {}


    public void addTransaction (String transactionName, BigDecimal transactionAmount, String description) {
        transactionModel.addTransaction(transactionName,Username, transactionAmount,description, date );
    }
    public ObservableList<String> getTransactionList() {
        ObservableList<String> transactions;
        List<String> transactionNames = new ArrayList<>();
        List<Integer> transactionIDs = transactionsDao.fetchByUserId(Username)
                .stream()
                .map(Transactions::getTransactionId)
                .toList();
        int id;
        for (int i = 0;transactionIDs.size() > i ; i++) {
            id = transactionIDs.get(i);
            transactionNames.add(transactionsDao.fetchByTransactionId(id).stream().map(Transactions::getTransactionName).toList().getFirst());
        }
        transactions = FXCollections.observableList(transactionNames);
        return transactions;
    }

    public void back() {
        loginPresenter.startTransaction(this);
        stage.close();
    }
    public void deleteTransaction(int transactionID) {
        transactionModel.deleteTransaction(transactionID);
    }
}
