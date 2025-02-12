package presenters;

import com.Prog_3_Projektarbeit.generated.tables.daos.HaveDao;
import javafx.stage.Stage;
import model.HaveModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import views.LoginView;
import model.UserModel;
import com.Prog_3_Projektarbeit.generated.tables.daos.UserDao;
import com.Prog_3_Projektarbeit.generated.tables.pojos.User;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;


class LoginPresenterTest {
    private LoginPresenter loginPresenter;
    private UserModel userModel;
    private LoginView view = mock(LoginView.class);
    private Stage stage = mock(Stage.class);
    private BudgetPresenter budgetPresenter = mock(BudgetPresenter.class);
    private DSLContext dslContext;
    private Connection connection;
    private UserDao userDao;
    private HaveModel haveModel;




    @BeforeEach
    void setUp() throws SQLException{
        // In-Memory SQLite-Datenbank einrichten
        connection = DriverManager.getConnection("jdbc:sqlite::memory:");
        dslContext = DSL.using(connection, SQLDialect.SQLITE);

        //Daos und Model erstellen
        userDao = new UserDao(dslContext.configuration());
        userModel = new UserModel(userDao);




        // LoginPresenter erstellen
        loginPresenter = new LoginPresenter(userDao, stage);
        loginPresenter.setView(view);
        loginPresenter.startBudget(budgetPresenter);



        // Tabellen erstellen
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

    }

    @Test
    void authenticateUser_true() {
        //Arrange
        userModel.addUser("testuser", "vor", "nach", "password123");
        assertEquals(true, loginPresenter.authenticateUser("testuser", "password123"));

    }

    @Test
    void createUser_true() {
        // Arrange
        String username = "newUser";
        String vorname = "John";
        String nachname = "Doe";
        String password = "newPassword";

        // Act
        boolean result = loginPresenter.createUser(username, vorname, nachname, password);

        // Assert
        assertEquals(true, result);


    }



}