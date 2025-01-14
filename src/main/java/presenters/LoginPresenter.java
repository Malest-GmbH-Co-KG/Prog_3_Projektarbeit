package presenters;
import model.UserModel;
import views.LoginView;
import com.Prog_3_Projektarbeit.generated.tables.pojos.User;

public class LoginPresenter {
    private final UserModel userModel;
    private LoginView view;

    public LoginPresenter(UserModel userModel) {
        this.userModel = userModel;

    }
    public void setView(LoginView view) {
        this.view = view;
    }

    public void authenticateUser(String username, String password) {
        User user = userModel.authenticateUser(username, password);
        if (user !=null) {
            view.showSuccess("User authenticated successfully");
        } else {
            view.showError("Invalid credentials");
        }

    }

    public void createUser(String username, String vorname, String nachname, String password) {
        boolean success = userModel.addUser(username, vorname , nachname, password);
        if (success) {
            view.showSuccess("User created successfully");
        } else {
            view.showError("Error creating user");
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
