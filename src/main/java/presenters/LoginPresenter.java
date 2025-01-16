package presenters;
import javafx.stage.Stage;
import model.UserModel;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.sqlite.SQLiteDataSource;
import views.LoginView;
import com.Prog_3_Projektarbeit.generated.tables.pojos.User;

import java.sql.Connection;
import java.sql.SQLException;

public class LoginPresenter {
    private final UserModel userModel;
    private LoginView view;
    private BudgetPresenter budgetPresenter;
    Stage stage = new Stage();



    public LoginPresenter(com.Prog_3_Projektarbeit.generated.tables.daos.UserDao userDao) {
        this.userModel = new UserModel(userDao);
        this.view = new LoginView(stage);
        view.setPresenter(this);
        this.budgetPresenter = new BudgetPresenter(userDao, this);
    }

    public void start(){
        view.start( );//startet die Anwendung bzw die GUI

    }
    public void setView(LoginView view) {
        this.view = view;
    }


    public void authenticateUser(String username, String password) {
        User user = userModel.authenticateUser(username, password);
        if (user !=null) {
            view.showSuccess("User authenticated successfully");
            view.closeStage(); //schließt das Login Fenster
            budgetPresenter.showBudgets(user.getUserName()); //zeigt die Budgets des angemeldeten Users
        } else {
            view.showError("Invalid credentials");
        }

    }

    public void createUser(String username, String vorname, String nachname, String password) {
        User newUser = userModel.addUser(username, vorname , nachname, password);
        if (newUser != null) {
            view.showSuccess("User created successfully");
            stage.close();
            budgetPresenter.showBudgets(newUser.getUserName());
        } else {
            if (userModel.isVornameEmpty(vorname) || userModel.isNachnameEmpty(nachname) || userModel.isUsernameEmpty(username) || userModel.isPasswordEmpty(password)) {
                view.showError("Please fill all fields");
            }else {
            if(!userModel.isUsernameAvailable(username)){
                view.showError("Username already exists");
            }else {
                view.showError("Error creating user");
            }
            }

        }
    }

    public void updateUser(String oldusername, String newUsername, String newPassword) {
        boolean success = userModel.updateUser(oldusername, newUsername, newPassword);
        if (success) {
            view.showSuccess("User updated successfully");
        } else {
            view.showError("Error updating user");
        }
    }
}
