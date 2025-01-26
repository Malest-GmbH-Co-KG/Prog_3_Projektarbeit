package presenters;


import com.Prog_3_Projektarbeit.generated.tables.daos.UserDao;
import javafx.stage.Stage;
import model.HaveModel;
import model.UserModel;
import views.LoginView;
import com.Prog_3_Projektarbeit.generated.tables.pojos.User;



public class LoginPresenter {
    private final UserModel userModel;
    private LoginView view;
    private BudgetPresenter budgetPresenter;
    private TransactionPresenter transactionPresenter;



    //Konstruktor
    public LoginPresenter(UserDao userDao,  Stage stage) {
        //UserModel wird erstellt
        this.userModel = new UserModel(userDao);
        //LoginView wird erstellt
        this.view = new LoginView(stage);
        view.setPresenter(this);
    }

    public void startBudget(BudgetPresenter budgetPresenter) {
        //BudgetPresenter wird übergeben
        this.budgetPresenter = budgetPresenter;
        view.start( );//startet die Anwendung bzw die GUI
    }
    public void startTransaction(TransactionPresenter transactionPresenter) {
        this.transactionPresenter = transactionPresenter;
        view.start( );
    }

    public void setView(LoginView view) {
        this.view = view;
    }


    public boolean authenticateUser(String username, String password) {
        //User wird authentifiziert
        User user = userModel.authenticateUser(username, password);
        if (user !=null) {
            view.showSuccess("User authenticated successfully");
            view.closeStage(); //schließt das Login Fenster
            budgetPresenter.showBudgets(user.getUserName());//zeigt die Budgets des angemeldeten Users
            return true;
        } else {
            view.showError("Invalid credentials");
            return false;
        }

    }

    public boolean createUser(String username, String vorname, String nachname, String password) {
        //User wird erstellt
        User newUser = userModel.addUser(username, vorname , nachname, password);
        if (newUser != null) {
            //wenn User erstellt wurde wird eine Erfolgsmeldung angezeigt
                view.showSuccess("User created successfully");
                view.closeStage();
                budgetPresenter.showBudgets(newUser.getUserName());
                return true;

        } else {
            //wenn User nicht erstellt wurde wird eine Fehlermeldung angezeigt
            if (userModel.isVornameEmpty(vorname) || userModel.isNachnameEmpty(nachname) || userModel.isUsernameEmpty(username) || userModel.isPasswordEmpty(password)) {
                //wenn nicht alle Felder ausgefüllt sind wird eine Fehlermeldung angezeigt
                view.showError("Please fill all fields");

            }else {

            if(!userModel.isUsernameAvailable(username)){
                //wenn der Username bereits existiert wird eine Fehlermeldung angezeigt
                 view.showError("Username already exists");

            }else {
                 view.showError("Error creating user");
                }
            }
        }
        return false;
    }

    public boolean updateUser(String oldusername, String newUsername, String newPassword) {
        boolean success = userModel.updateUser(oldusername, newUsername, newPassword);
        if (success) {
            view.showSuccess("User updated successfully");
            return true;
        } else {
            view.showError("Error updating user");
            return false;
        }
    }
}
