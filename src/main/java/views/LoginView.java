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

    private TextField oldUsernameField;
    private TextField newUserNameField;

    public LoginView(Stage stage) {
        this.primaryStage = stage;
    }

    public void setPresenter(LoginPresenter presenter) {
        this.presenter = presenter;
        this.presenter.setView(this);
    }


    public void start() {

        primaryStage.setTitle("Nutzer Management");

        // Layout for the login form
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setStyle("-fx-alignment: center;");

        // Login Section
        Label usernameLabel = new Label("Nutzername:");
        usernameField = new TextField();
        Label passwordLabel = new Label("Passwort:");
        passwordField = new PasswordField();
        Button loginButton = new Button("Einloggen");

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);

        // Create User Section
        Label newUsernameLabel = new Label("Neuer Nutzername:");
        newUsernameField = new TextField();
        Label newVornameLabel = new Label("Vorname:");
        newVornameField = new TextField();
        Label newNachnameLabel = new Label("Nachname:");
        newNachnameField = new TextField();
        Label newPasswordLabel = new Label("Neues Passwort:");
        newPasswordField = new PasswordField();
        Button createUserButton = new Button("Nutzer erstellen");

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
        Button updateUserButton = new Button("Nutzer updaten");
        grid.add(updateUserButton, 1, 8);
        Button updateUserButton1 = new Button("Jetzt Nutzer updaten");
        Label oldUsernameLabel = new Label("Alter Nutzername:");
        Label newUserNameLabel = new Label("Neuer Nutzername:");
        oldUsernameField = new TextField();
        newUserNameField = new TextField();
        updateUserButton1.setVisible(false);


        grid.add(oldUsernameLabel, 0, 9);
        grid.add(oldUsernameField, 1, 9);
        grid.add(newUserNameLabel, 0, 10);
        grid.add(newUserNameField, 1, 10);
        grid.add(updateUserButton1, 1, 11);

        oldUsernameField.setVisible(false);
        newUserNameField.setVisible(false);
        oldUsernameLabel.setVisible(false);
        newUserNameLabel.setVisible(false);

        updateUserButton.setOnAction(e -> {
            oldUsernameField.setVisible(true);
            newUserNameField.setVisible(true);
            oldUsernameLabel.setVisible(true);
            newUserNameLabel.setVisible(true);
            updateUserButton.setVisible(false);
            updateUserButton1.setVisible(true);
        });

        updateUserButton1.setOnAction(e -> {
            String oldUsername = oldUsernameField.getText();
            String newUsername = newUserNameField.getText();
            presenter.updateUser(oldUsername, newUsername);
            oldUsernameField.setVisible(false);
            newUserNameField.setVisible(false);
            oldUsernameLabel.setVisible(false);
            newUserNameLabel.setVisible(false);
            updateUserButton1.setVisible(false);
            updateUserButton.setVisible(true);
        });




        // Message Label
        messageLabel = new Label();
        messageLabel.setVisible(true);
        grid.add(messageLabel, 0, 12, 2, 1);

        // Button Actions
        loginButton.setOnAction(e -> authenticateUser());
        createUserButton.setOnAction(e -> createUser());


        // Set Scene

        Scene scene = new Scene(grid, 800, 600);
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