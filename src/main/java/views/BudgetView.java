package views;

import com.Prog_3_Projektarbeit.generated.tables.daos.UserDao;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.UserModel;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.sqlite.SQLiteDataSource;
import presenters.LoginPresenter;
import views.LoginView;

import java.sql.Connection;
import java.sql.SQLException;


public class BudgetView {
    private LoginPresenter presenter;
    private DSLContext dslContext;
    private Connection connection;
    private UserDao userDao;
    private LoginView loginView;

    private TextField usernameField;
    private Label messageLabel;



    public void updateBudgetList() {
        
    }

    public void start(Stage stage) {

    }
}
