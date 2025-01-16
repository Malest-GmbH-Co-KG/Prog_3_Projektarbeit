import javafx.stage.Stage;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.sqlite.SQLiteDataSource;
import presenters.LoginPresenter;
import javafx.application.Application;
import com.Prog_3_Projektarbeit.generated.tables.daos.UserDao;

import java.sql.Connection;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        DSLContext dslContext;
        Connection connection;
        UserDao userDao;
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:budget_planner_db.db");
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dslContext = DSL.using(connection, SQLDialect.SQLITE);
        userDao = new com.Prog_3_Projektarbeit.generated.tables.daos.UserDao(dslContext.configuration());
        LoginPresenter loginPresenter = new LoginPresenter(userDao);
        loginPresenter.start();
    }
    //Datenbankverbindung wird hergestellt
    //über die Klasse loginPresenter wird die Anwendung gestartet und die Datenbankverbindung übergeben

    public static void main(String[] args) {
        launch(args);

    }

}
