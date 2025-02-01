package model;

import com.Prog_3_Projektarbeit.generated.tables.daos.TransactionsDao;
import com.Prog_3_Projektarbeit.generated.tables.pojos.Budget;
import com.Prog_3_Projektarbeit.generated.tables.pojos.Transactions;
import com.Prog_3_Projektarbeit.generated.tables.daos.HaveDao;
import com.Prog_3_Projektarbeit.generated.tables.pojos.Have;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presenters.TransactionPresenter;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionModel {
    private final TransactionsDao transactionsDao;
    private final HaveDao haveDao;
    private final HaveModel haveModel;
    private TransactionPresenter presenter;
    private String username;
    private int budgetId;

    public String getUsername() {return username;}
    // Konstruktor
    public TransactionModel (HaveDao haveDao, TransactionsDao transactionsDao, TransactionPresenter presenter, HaveModel haveModel) {
        this.transactionsDao = transactionsDao;
        this.haveDao = haveDao;
        this.presenter = presenter;
        this.haveModel = haveModel;
    }
    //Hinzufügen einer Transaktion
    public int addTransaction (String name, BigDecimal amount, String description, LocalDate date) {
        Transactions newTransaction = new Transactions();
        newTransaction.setTransactionName(name);
        newTransaction.setCategory("General");
        newTransaction.setBudgetId(budgetId);
        newTransaction.setUserId(username);
        newTransaction.setAmmount(amount);

        newTransaction.setDescription(description);
        newTransaction.setDate(date);
        transactionsDao.insert(newTransaction);

        int getBudgetId = newTransaction.getBudgetId();
        int getTransactionID =newTransaction.getTransactionId();
        //Hinzufügen der Transaktion in die Tabelle "Have"
        Have updateHave = new Have();
        updateHave.setUserName(username);
        updateHave.setBudgetId(getBudgetId);
        haveDao.update(updateHave);
        if (presenter != null) {
            presenter.showTransactions(getBudgetId, username);
        }
        return getTransactionID;
    }

    public void deleteTransaction (int transactionID, int budgetID) {
        //Löschen der Transaktion
        transactionsDao.deleteById(transactionID);
        //Löschen der Transaktion aus der Tabelle "Have"
        haveModel.deleteByTransactionId(transactionID);
        presenter.showTransactions(budgetID, username);
    }

    public void setCurrentUser (String username, int budgetID) {
        this.username = username;
        this.budgetId = budgetID;}

    public ObservableList<String> getTransactionList() {
        ObservableList<String> transactions;
        List<String> transactionNames = new ArrayList<>();
            List<Transactions> budget = transactionsDao.fetchByBudgetId(budgetId);
            for (Transactions transaction : budget) {
                transactionNames.add(transaction.getTransactionName() + " (" + transaction.getTransactionId() + ")  " + username + " - " + transaction.getAmmount() + " - " + transaction.getDescription());
        }
        transactions = FXCollections.observableArrayList(transactionNames);
        return transactions;
    }

    public Transactions getTransactionById(int transactionId) {
        return transactionsDao.fetchOneByTransactionId(transactionId);
    }

}
