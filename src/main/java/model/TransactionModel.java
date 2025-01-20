package model;

import com.Prog_3_Projektarbeit.generated.tables.daos.TransactionsDao;
import com.Prog_3_Projektarbeit.generated.tables.pojos.Transactions;
import com.Prog_3_Projektarbeit.generated.tables.daos.HaveDao;
import com.Prog_3_Projektarbeit.generated.tables.pojos.Have;
import views.TransactionView;
import views.BudgetView;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionModel {
    private final TransactionsDao transactionsDao;
    private final HaveDao haveDao;
    private TransactionView transactionView;

    public TransactionModel (HaveDao haveDao, TransactionsDao transactionsDao, TransactionView transactionView) {
        this.transactionsDao = transactionsDao;
        this.haveDao = haveDao;
        this.transactionView = transactionView;
    }

    public float addTransaction (String name, String username, BigDecimal amount, String description, LocalDate date) {
        Transactions newTransaction = new Transactions();
        newTransaction.setTransactionName(name);
        newTransaction.setUserId(username);
        newTransaction.setAmmount(amount);
        newTransaction.setDescription(description);
        newTransaction.setDate(date);

        int getTransactionID = newTransaction.getBudgetId();
        Have updateHave = new Have();
        updateHave.setUserName(username);
        updateHave.setBudgetId(getTransactionID);
        haveDao.update(updateHave);
        if (transactionView != null) {
            //transactionView.update(username)
        }
        return getTransactionID;
    }

}
