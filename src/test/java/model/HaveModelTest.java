package model;

import com.Prog_3_Projektarbeit.generated.tables.daos.BudgetDao;
import com.Prog_3_Projektarbeit.generated.tables.daos.HaveDao;
import com.Prog_3_Projektarbeit.generated.tables.daos.UserDao;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sqlite.SQLiteDataSource;
import presenters.BudgetPresenter;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class HaveModelTest {
    private DSLContext dslContext;
    private Connection connection;
    private HaveModel haveModel;
    private BudgetModel budgetModel;
    private HaveDao haveDao;
    private UserDao userDao;
    private BudgetDao budgetDao;
    private BudgetPresenter presenter;



    @BeforeEach
    void setUp() throws SQLException {
        // In-Memory SQLite-Datenbank einrichten
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite::memory:");
        connection = dataSource.getConnection();
        dslContext = DSL.using(connection, SQLDialect.SQLITE);

        // Tabellen erstellen
        dslContext.query("""
    CREATE TABLE IF NOT EXISTS budget (
        budget_id INTEGER PRIMARY KEY AUTOINCREMENT,
        budget_name TEXT NOT NULL,
        ammount INTEGER,
        allTransactionAmmount FLOAT,
        created_at TEXT
    )
""").execute();

        dslContext.query("""
    CREATE TABLE IF NOT EXISTS have (
        budget_id INTEGER,
        user_name INTEGER,
        PRIMARY KEY (budget_id, user_name)
    )
""").execute();

        dslContext.query("""
    CREATE TABLE IF NOT EXISTS user (
        user_name TEXT PRIMARY KEY,
        vorname TEXT,
        nachname TEXT,
        password TEXT NOT NULL,
        salt TEXT ,
        created_at TEXT

    )
""").execute();



        // DAO und Presenter initialisieren
        budgetDao = new BudgetDao(dslContext.configuration());
        haveDao = new HaveDao(dslContext.configuration());
        haveModel = new HaveModel(dslContext);
        userDao = new UserDao(dslContext.configuration());


        presenter = mock(BudgetPresenter.class); // Mocking the presenter
        budgetModel = new BudgetModel(budgetDao,haveDao ,presenter, haveModel, userDao);

    }
    @AfterEach
    void tearDown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }



    @Test
    void deleteByBudgetId() {
        int budgetId = budgetModel.addBudget("Test", 100, "Test");
        haveModel.deleteByBudgetId(budgetId);
        assertEquals(haveDao.fetchByBudgetId(budgetId).size(), 0);
    }

    @Test
    void getAllUsersforBudget_true() {
        int budgetId = budgetModel.addBudget("Test", 100, "Test");
        haveModel.getAllUsersforBudget(budgetId);
        assertEquals(haveDao.fetchByBudgetId(budgetId).size(), 1);
    }

}