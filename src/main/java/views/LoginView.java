package views;



import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



import presenters.LoginPresenter;



public class LoginView {
    private LoginPresenter presenter;

    private TextField usernameField;
    private PasswordField passwordField;
    private TextField newUsernameField;
    private TextField newVornameField;
    private TextField newNachnameField;
    private PasswordField newPasswordField;
    private Label messageLabel;
    private Stage primaryStage;

    public LoginView(Stage stage) {
        this.primaryStage = stage;
    }

    public void setPresenter(LoginPresenter presenter) {
        this.presenter = presenter;
        this.presenter.setView(this);
    }


    public void start() {

        primaryStage.setTitle("User Management");

        // Layout for the login form
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setStyle("-fx-alignment: center;");

        // Login Section
        Label usernameLabel = new Label("Username:");
        usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        passwordField = new PasswordField();
        Button loginButton = new Button("Login");

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);

        // Create User Section
        Label newUsernameLabel = new Label("New Username:");
        newUsernameField = new TextField();
        Label newVornameLabel = new Label("Vorname:");
        newVornameField = new TextField();
        Label newNachnameLabel = new Label("Nachname:");
        newNachnameField = new TextField();
        Label newPasswordLabel = new Label("New Password:");
        newPasswordField = new PasswordField();
        Button createUserButton = new Button("Create User");

        grid.add(newUsernameLabel, 0, 3);
        grid.add(newUsernameField, 1, 3);
        grid.add(newVornameLabel, 0, 4);
        grid.add(newVornameField, 1, 4);
        grid.add(newNachnameLabel, 0, 5);
        grid.add(newNachnameField, 1, 5);
        grid.add(newPasswordLabel, 0, 6);
        grid.add(newPasswordField, 1, 6);
        grid.add(createUserButton, 1, 7);

        // Update User Section
        Button updateUserButton = new Button("Update User");
        grid.add(updateUserButton, 1, 8);

        // Message Label
        messageLabel = new Label();
        messageLabel.setVisible(true);
        grid.add(messageLabel, 0, 9, 2, 1);

        // Button Actions
        loginButton.setOnAction(e -> authenticateUser());
        createUserButton.setOnAction(e -> createUser());
        updateUserButton.setOnAction(e -> updateUser());

        // Set Scene

        Scene scene = new Scene(grid, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void authenticateUser() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        presenter.authenticateUser(username, password);
    }

    private void createUser() {
        String newUsername = newUsernameField.getText();
        String newPassword = newPasswordField.getText();
        String newVorname = newVornameField.getText();
        String newNachname = newNachnameField.getText();
        presenter.createUser(newUsername, newVorname, newNachname, newPassword);
    }

    private void updateUser() {
        String newUsername = newUsernameField.getText();
        String newPassword = newPasswordField.getText();
        presenter.updateUser("beispeil", newUsername, newPassword); // Example userId (1) for testing
    }

    public void showSuccess(String message) {
        messageLabel.setText(message);
        messageLabel.setStyle("-fx-text-fill: green;");
    }

    public void showError(String message) {
        messageLabel.setText(message);
        messageLabel.setStyle("-fx-text-fill: red;");
    }

    public void closeStage() {
        primaryStage.close(); // Schließt das aktuelle Fenster
    }
}