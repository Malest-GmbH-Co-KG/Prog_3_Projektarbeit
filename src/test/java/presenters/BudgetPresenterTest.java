package presenters;

import com.Prog_3_Projektarbeit.generated.tables.daos.BudgetDao;
import com.Prog_3_Projektarbeit.generated.tables.daos.HaveDao;
import com.Prog_3_Projektarbeit.generated.tables.daos.UserDao;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import model.BudgetModel;
import model.HaveModel;
import model.UserModel;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class BudgetPresenterTest {

    private LoginPresenter loginPresenter = mock(LoginPresenter.class);
    private Stage stage = mock(Stage.class);
    private BudgetPresenter budgetPresenter;
    private DSLContext dslContext;
    private Connection connection;
    private UserDao userDao;
    private UserModel userModel;
    private HaveDao haveDao;
    private HaveModel haveModel;
    private BudgetModel budgetModel;
    private BudgetDao budgetDao;




    @BeforeEach
    void setUp() throws SQLException {
        // In-Memory SQLite-Datenbank einrichten
        connection = DriverManager.getConnection("jdbc:sqlite::memory:");
        dslContext = DSL.using(connection, SQLDialect.SQLITE);
        new JFXPanel(); // Initialisiert JavaFX-Thread

        //Daos und Model erstellen
        userDao = new UserDao(dslContext.configuration());
        userModel = new UserModel(userDao);
        haveDao = new HaveDao(dslContext.configuration());
        haveModel = new HaveModel(dslContext);


        // BudgetPresenter erstellen
        budgetPresenter = new BudgetPresenter(userDao,loginPresenter,haveModel,stage);

        //Budget Dao und Model erstellen
        budgetDao = new BudgetDao(dslContext.configuration());
        budgetModel = new BudgetModel(budgetDao,haveDao,budgetPresenter,haveModel,userDao);

        // Tabellen erstellen
        dslContext.query("""
                CREATE TABLE budget (
                        budget_Id INTEGER UNIQUE NOT NULL PRIMARY KEY,
                        budget_name VARCHAR(255) NOT NULL,
                        ammount FLOAT NOT NULL,
                        allTransactionAmmount FLOAT,
                        created_at TEXT
                )
            """).execute();

        BudgetModel budgetModel = new BudgetModel(budgetDao,haveDao,budgetPresenter,haveModel,userDao);

        dslContext.query("""
                CREATE TABLE user (
                        user_name VARCHAR(255) UNIQUE NOT NULL PRIMARY KEY,
                        Vorname VARCHAR(255) NOT NULL,
                        Nachname VARCHAR(255) NOT NULL,
                        password VARCHAR(256) NOT NULL,
                        salt VARCHAR(256) NOT NULL,
                        created_at TEXT
                )
            """).execute();

        UserModel userModel = new UserModel(userDao);

        dslContext.query("""
                CREATE TABLE have (
                        budget_Id INTEGER UNIQUE NOT NULL PRIMARY KEY,
                        user_name VARCHAR(255) NOT NULL
                )
            """).execute();

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

    }

    @Test
    void addBudget_true() {
        // Arrange
        String budgetName = "newBudget";
        float budgetAmmount = 10.0f;
        String username = "newUsername";

        // Assert
        assertEquals(1,budgetModel.addBudget(budgetName,budgetAmmount,username));

    }
    @Test
    void deleteBudget_true() {
        // Arrange
        int budgetId = 1;

        // Assert
        assertEquals(true,budgetModel.deleteBudget(budgetId));

    }
    @Test
    void addUsertoBudget_true() {
        // Arrange
        String budgetName = "newBudget";
        int budgetId = 2;
        String budgetUsername = "newBudgetusername";
        String username = "newUsername";
        float budgetAmmount = 10.0f;
        String password = "newPassword";
        String vorname = "newVorname";
        String nachname = "newNachname";

        //Act
        userModel.addUser(username,password,vorname,nachname);
        budgetModel.addBudget(budgetName,budgetAmmount,budgetUsername);

        // Assert
        assertEquals(true,budgetModel.addUserToBudget(username,budgetId));
    }

}
