package model;

import com.Prog_3_Projektarbeit.generated.tables.daos.TransactionsDao;
import com.Prog_3_Projektarbeit.generated.tables.daos.UserDao;
import com.Prog_3_Projektarbeit.generated.tables.pojos.Budget;
import com.Prog_3_Projektarbeit.generated.tables.pojos.Have;
import org.junit.jupiter.api.Test;


import com.Prog_3_Projektarbeit.generated.tables.daos.BudgetDao;
import com.Prog_3_Projektarbeit.generated.tables.daos.HaveDao;
import presenters.BudgetPresenter;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.sqlite.SQLiteDataSource;
import presenters.TransactionPresenter;

import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionModelTest {
    private DSLContext dslContext;
    private Connection connection;

    private HaveDao haveDao;
    private HaveModel haveModel;
    private TransactionModel transactionModel;
    private TransactionsDao transactionsDao;
    private TransactionPresenter presenter;

    private String username;

    @BeforeEach
    void setUp() throws SQLException {
        // In-Memory SQLite-Datenbank einrichten
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite::memory:");
        connection = dataSource.getConnection();
        dslContext = DSL.using(connection, SQLDialect.SQLITE);

        // Tabellen erstellen
        dslContext.query("""
    CREATE TABLE IF NOT EXISTS transactions (
        transaction_id INTEGER PRIMARY KEY AUTOINCREMENT,
        transaction_name TEXT,
        budget_id INTEGER,
        description TEXT,
        ammount FLOAT,
        date LocalDate,
        category TEXT,
        user_id text
    )"""
        ).execute();

        dslContext.query("""
    CREATE TABLE IF NOT EXISTS have(
        budget_id INTEGER,
        user_name TEXT
    )"""
        ).execute();

        transactionsDao = new TransactionsDao(dslContext.configuration());
        haveDao = new HaveDao(dslContext.configuration());
        presenter = mock(TransactionPresenter.class);
        haveModel = new HaveModel(dslContext);
        transactionModel = new TransactionModel(haveDao, transactionsDao, presenter, haveModel);
        username = "test";

    }
    @Test
    void addTransaction_true() {
        int budgetId = 1;
        String username = "test";
        transactionModel.setCurrentUser(username, budgetId);
        int transactionId = transactionModel.addTransaction("Test", BigDecimal.valueOf(100), "Test", LocalDate.now() );
        assertNotNull(haveDao.fetchByBudgetId(budgetId));
        assertNotNull(transactionModel.getTransactionById(transactionId));
    }


    @Test
    void deleteTransaction_true() {
        int budgetId = 1;
        String username = "test";
        transactionModel.setCurrentUser(username, budgetId);
        int transactionId = transactionModel.addTransaction("Test", BigDecimal.valueOf(100), "Test", null);
        transactionModel.deleteTransaction(transactionId, budgetId);
        assertEquals(0, haveDao.fetchByBudgetId(budgetId).size());
        assertNull(transactionModel.getTransactionById(transactionId));
    }

    @Test
    void setCurrentUser_true() {
        int budgetId = 1;
        transactionModel.setCurrentUser(username, budgetId);
        assertEquals(username, transactionModel.getUsername());
        assertEquals(budgetId, transactionModel.getBudgetId());
    }

    @Test
    void getTransactionList_true() {
        int budgetId = 1;
        transactionModel.setCurrentUser(username, budgetId);
        transactionModel.addTransaction("Test", BigDecimal.valueOf(100), "Test", LocalDate.now());
        List<String> transactionList = transactionModel.getTransactionList();
        assertNotNull(transactionList);
    }

    @Test
    void getTransactionById_true() {
        int budgetId = 1;
        transactionModel.setCurrentUser(username, budgetId);
        int transactionId = transactionModel.addTransaction("Test", BigDecimal.valueOf(100), "Test", null);
        assertNotNull(transactionModel.getTransactionById(transactionId));
    }

    @Test
    void changeDescription_true() {
        int budgetId = 1;
        transactionModel.setCurrentUser(username, budgetId);
        int transactionId = transactionModel.addTransaction("Test", BigDecimal.valueOf(100), "Test", null);
        transactionModel.changeDescription(transactionId, "New Description");
        assertEquals("New Description", transactionModel.getTransactionById(transactionId).getDescription());
    }
}