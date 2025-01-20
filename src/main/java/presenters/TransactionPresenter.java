package presenters;

import com.Prog_3_Projektarbeit.generated.tables.User;
import com.Prog_3_Projektarbeit.generated.tables.daos.HaveDao;
import com.Prog_3_Projektarbeit.generated.tables.daos.TransactionsDao;
import com.Prog_3_Projektarbeit.generated.tables.pojos.Transactions;
import com.Prog_3_Projektarbeit.generated.tables.pojos.Have;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.TransactionModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionPresenter {
    TransactionModel transactionModel;
    private TransactionsDao transactionsDao;
    private HaveDao haveDao;
    String Username;
    LocalDate date;


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
            transactionNames.add(transactionsDao.fetchByTransactionId(id).stream().map(Transactions::get).toList().getFirst());
        }
        transactions = FXCollections.observableList(transactionNames);
        return transactions;
    }
}
