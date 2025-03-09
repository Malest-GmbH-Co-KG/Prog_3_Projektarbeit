package presenters;

import com.Prog_3_Projektarbeit.generated.tables.daos.HaveDao;
import com.Prog_3_Projektarbeit.generated.tables.daos.TransactionsDao;
import com.Prog_3_Projektarbeit.generated.tables.daos.UserDao;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.HaveModel;
import model.TransactionModel;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class TransactionPresenterTest {

    private LoginPresenter loginPresenter = mock(LoginPresenter.class);
    private Stage stage = mock(Stage.class);
    private BudgetPresenter budgetPresenter = mock(BudgetPresenter.class);
    private TransactionPresenter transactionPresenter;
    private DSLContext dslContext;
    private Connection connection;
    private UserDao userDao;
    private HaveDao haveDao;
    private HaveModel haveModel;
    private TransactionModel transactionModel;
    private TransactionsDao transactionsDao;



    @BeforeEach
    void setUp() throws SQLException {
        // In-Memory SQLite-Datenbank einrichten
        connection = DriverManager.getConnection("jdbc:sqlite::memory:");
        dslContext = DSL.using(connection, SQLDialect.SQLITE);

        //Daos und Model erstellen
        userDao = new UserDao(dslContext.configuration());
        haveDao = new HaveDao(dslContext.configuration());
        haveModel = new HaveModel(dslContext);
        transactionsDao = new TransactionsDao(dslContext.configuration());



        // TransactionPresenter erstellen
        transactionPresenter = new TransactionPresenter(userDao,budgetPresenter,haveModel,stage);

        // TransactionModel erstellen
        transactionModel = new TransactionModel(haveDao,transactionsDao,transactionPresenter,haveModel);

        // Tabellen erstellen
        dslContext.query("""
                CREATE TABLE transactions (
                        transaction_Id INTEGER UNIQUE NOT NULL PRIMARY KEY,
                        transaction_name VARCHAR(255) NOT NULL,
                        budget_Id INTEGER NOT NULL,
                        User_Id VARCHAR(256),
                        Category Varchar(255) NOT NULL,
                        ammount FLOAT NOT NULL,
                        date TEXT,
                        description VARCHAR(255) NOT NULL
                )
            """).execute();

        TransactionPresenter transactionPresenter = new TransactionPresenter(userDao,budgetPresenter,haveModel,stage);

        dslContext.query("""
                CREATE TABLE have (
                        budget_Id INTEGER UNIQUE NOT NULL PRIMARY KEY,
                        user_name VARCHAR(255) NOT NULL
                )
            """).execute();
    }

    @Test
    void addTransaction_true(){
        // Arrange
        String transactionName = "New Transaction";
        float transactionAmount = 100.00f;
        String description = "New Description";
        LocalDate transactionDate = LocalDate.now();

        //assert
        assertEquals(1, transactionModel.addTransaction(transactionName, transactionAmount, description, transactionDate));

    }
    @Test
    void deleteTransaction_true(){
        // Arrange
        int transactionId = 1;
        int budgetId = 2;

        // assert
        assertEquals(true,transactionModel.deleteTransaction(transactionId,budgetId));
    }
}

