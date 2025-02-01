package model;

import com.Prog_3_Projektarbeit.generated.tables.daos.TransactionsDao;
import com.Prog_3_Projektarbeit.generated.tables.pojos.Transactions;
import com.Prog_3_Projektarbeit.generated.tables.daos.HaveDao;
import com.Prog_3_Projektarbeit.generated.tables.pojos.Have;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presenters.TransactionPresenter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>Klasse zum Erstellen einer Transaction.</h2> <br>
 * <h3>Hier wird die Transaktion vorbereitet, d.h <br>
 * alle Methoden, die angeboten werden von der<br>
 * Transaktion, werden hier erstellt, aber nicht<br>
 * instanziiert.</h3>
 */
public class TransactionModel {
    private final TransactionsDao transactionsDao;
    private final HaveDao haveDao;
    private final HaveModel haveModel;
    private TransactionPresenter presenter;
    private String username;
    private int budgetId;


    // Konstruktor
    public TransactionModel (HaveDao haveDao, TransactionsDao transactionsDao, TransactionPresenter presenter, HaveModel haveModel) {
        this.transactionsDao = transactionsDao;
        this.haveDao = haveDao;
        this.presenter = presenter;
        this.haveModel = haveModel;
    }

    /**<h3>Hinzufügen einer Transaktion</h3>
     * @param name Name der Transaktion
     * @param amount Höhe der Ausgabe/Einnahme
     * @param description Beschreibeung der Transaktion
     * @param transactiondate Datum der Transaktion
     */
    public int addTransaction (String name, BigDecimal amount, String description, LocalDate transactiondate) {
        Transactions newTransaction = new Transactions();
        newTransaction.setTransactionName(name);
        newTransaction.setCategory("General");
        newTransaction.setBudgetId(budgetId);
        newTransaction.setUserId(username);
        newTransaction.setAmmount(amount);

        newTransaction.setDescription(description);
        newTransaction.setDate(transactiondate);
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

    /**<h3>Löschen einer Transaktion</h3>
     *
     * @param transactionID welche Transaktion gelöscht werden soll
     * @param budgetID aus welchem Budget es gelöscht werden soll
     */
    public void deleteTransaction (int transactionID, int budgetID) {
        //Löschen der Transaktion
        transactionsDao.deleteById(transactionID);
        //Löschen der Transaktion aus der Tabelle "Have"
        haveModel.deleteByTransactionId(transactionID);
        presenter.showTransactions(budgetID, username);
    }

    /**<h3>Nutzer der Transaktion zuordnen</h3>
     *
     * @param username Nutzername der getätigten Transaktion
     * @param budgetID In welchem Budget die Transaktion getätigt wurde
     */
    public void setCurrentUser (String username, int budgetID) {
        this.username = username;
        this.budgetId = budgetID;}

    /** <h3>Ersteller des Transaktions-Strings</h3>
     *
     * @return Die Daten, welche angezeigt werden sollen
     */
    public ObservableList<String> getTransactionList() {
        ObservableList<String> transactions;
        List<String> transactionNames = new ArrayList<>();
            List<Transactions> budget = transactionsDao.fetchByBudgetId(budgetId);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            for (Transactions transaction : budget) {
                String formattedDate = transaction.getDate().format(formatter);
                transactionNames.add(transaction.getTransactionName() + " ("
                        + transaction.getTransactionId() + ")  "
                        + username + " - "
                        + formattedDate + " - "
                        + transaction.getAmmount() );
        }
        transactions = FXCollections.observableArrayList(transactionNames);
        return transactions;
    }

    /**
     *
     * @param transactionId ID der Transaktion
     * @return Die Transaktion, nach der verlangt wird
     */
    public Transactions getTransactionById(int transactionId) {
        return transactionsDao.fetchOneByTransactionId(transactionId);
    }

}
