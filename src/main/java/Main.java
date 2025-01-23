import javafx.stage.Stage;
import model.HaveModel;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.sqlite.SQLiteDataSource;
import presenters.BudgetPresenter;
import presenters.LoginPresenter;
import javafx.application.Application;
import com.Prog_3_Projektarbeit.generated.tables.daos.UserDao;
import presenters.TransactionPresenter;

import java.sql.Connection;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //Datenbankverbindung wird hergestellt
        DSLContext dslContext;
        Connection connection;
        UserDao userDao;
        HaveModel haveModel;
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:budget_planner_db.db");
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dslContext = DSL.using(connection, SQLDialect.SQLITE);
        //über die Klasse loginPresenter wird die Anwendung gestartet und die Datenbankverbindung übergeben
        userDao = new com.Prog_3_Projektarbeit.generated.tables.daos.UserDao(dslContext.configuration());
        haveModel = new HaveModel(dslContext);

        //Stage wird erstellt und übergeben
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Budget Planner");
        //LoginPresenter wird erstellt und übergeben
        LoginPresenter loginPresenter = new LoginPresenter(userDao, primaryStage);
        //BudgetPresenter wird erstellt und übergeben
        BudgetPresenter budgetPresenter = new BudgetPresenter(userDao,loginPresenter, haveModel, stage);

        //startet die Anwendung
        loginPresenter.startBudget(budgetPresenter);
    }
    //Datenbankverbindung wird hergestellt
    //über die Klasse loginPresenter wird die Anwendung gestartet und die Datenbankverbindung übergeben

    public static void main(String[] args) {
        launch(args);

    }

}
