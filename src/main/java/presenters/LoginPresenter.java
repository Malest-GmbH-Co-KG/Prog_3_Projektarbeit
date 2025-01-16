package presenters;
import javafx.stage.Stage;
import model.UserModel;
import views.BudgetView;
import views.LoginView;
import com.Prog_3_Projektarbeit.generated.tables.pojos.User;

public class LoginPresenter {
    private final UserModel userModel;
    private LoginView view;
    private BudgetView budgetView;

    public LoginPresenter(UserModel userModel) {
        this.userModel = userModel;

    }
    public void setView(LoginView view) {
        this.view = view;
    }
    public void setBudgetView(BudgetView budgetView) {
        this.budgetView = budgetView;
    }

    public void authenticateUser(String username, String password) {
        User user = userModel.authenticateUser(username, password);
        if (user !=null) {
            view.showSuccess("User authenticated successfully");
            view.navigatetoBudgetView();
        } else {
            view.showError("Invalid credentials");
        }

    }

    public void createUser(String username, String vorname, String nachname, String password) {
        boolean success = userModel.addUser(username, vorname , nachname, password);
        if (success) {
            view.showSuccess("User created successfully");
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
