package model;

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
import static org.mockito.Mockito.mock;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class BudgetModelTest {
    private DSLContext dslContext;
    private Connection connection;
    private BudgetDao budgetDao;
    private BudgetModel budgetModel;
    private HaveDao haveDao;
    private BudgetPresenter presenter;
    private HaveModel haveModel;
    private UserDao userDao;


    //Vor jedem Test wird eine In-Memory SQLite-Datenbank eingerichtet
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


        presenter = mock(BudgetPresenter.class); // Mocking vom Presenter
        budgetModel = new BudgetModel(budgetDao,haveDao ,presenter, haveModel, userDao);
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void addBudget() {
        //Testdaten
        String budget_name = "TestBudget";
        float amount = 100;
        String username = "TestUser";


        int budget_id = budgetModel.addBudget(budget_name, amount, username);

        List<Have> checkHave = haveDao.fetchByUserName(username);
        assertEquals(1, checkHave.size());
        //Überprüfen ob Budget in der Datenbank vorhanden ist
        Budget checkBudget = budgetDao.fetchOneByBudgetId(budget_id);
        assertEquals(budget_name, checkBudget.getBudgetName());
        assertEquals(amount, checkBudget.getAmmount());
    }

    @Test
    void setCurrentUser() {

        String username = "TestUser";
        budgetModel.setCurrentUser(username);
        assertEquals(username, budgetModel.getUsername());
    }

    @Test
    void deleteBudget_true() {
        String budget_name = "TestBudget";
        float amount = 100;
        String username = "TestUser";


        int budget_id = budgetModel.addBudget(budget_name, amount, username);
        budgetModel.deleteBudget(budget_id);

        assertEquals(0, haveDao.fetchByBudgetId(budget_id).size());
    }



}