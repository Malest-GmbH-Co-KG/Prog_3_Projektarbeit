package views;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import org.jooq.Transaction;
import presenters.BudgetPresenter;
import presenters.TransactionPresenter;
import views.CustomListCell;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionView {
    private final TransactionPresenter presenter;
    private BudgetPresenter budgetPresenter;
    private ListView<String> transactionList;

    private TextArea showTransacDescrArea;
    private Label transactionDescriptionLabel;

    private Label messageLabel;

    private Label transactionNameLabel;

    private Label transactionAmountLabel;
    private Label allTransactionAmmountLabel;
    private Label allTransactionAmmountLabel1;

    private Label BudgetAmmount;
    private Label BudgetAmmount1;

    private Label restAmmount;
    private Label restAmmount1;
    private Label allUsersLabel;
    private Label allUsersLabel1;

    public TransactionView(TransactionPresenter presenter, BudgetPresenter budgetPresenter) {
        this.presenter = presenter;
        this.budgetPresenter = budgetPresenter;
    }

    public void show(Stage stage) {

        // Liste der ausgeführten Transaktionen
        transactionList = new ListView<>();
        transactionList.setCellFactory(param -> new CustomListCell(budgetPresenter));
        ObservableList<String> transactions = presenter.getTransactionList();
        transactionList.setItems(transactions);
        transactionList.refresh();

        //TextArea um Descriptions anzuzeigen
        showTransacDescrArea = new TextArea();
        showTransacDescrArea.setEditable(false);
        showTransacDescrArea.setWrapText(true);
        showTransacDescrArea.setMaxWidth(Double.MAX_VALUE);

        //Textfelder für Name, Betrag und Beschreibung der Transaktion
        TextField transactionNameField = new TextField();
        TextField transactionAmountField = new TextField();
        TextField transactionDescriptionField = new TextField();

        /* Buttons für,
         * 1. Hinzufügen einer Transaktion <br>
         * 2. Anzeigen der Beschreibung dür eine Transaktion <br>
         * 3. Löschen einer Transaktion
         * 4. Zurückgehen zum vorherigen Fenster
         */


        Button addTransactButton = new Button("Add Transaction");
        Button showDescriptionButton = new Button("Show Description");
        Button deleteTransactionButton = new Button("Delete Transaction");
        Button backButton = new Button("Back");

        //Buttons, Textfelder und Labels für Änderung der Beschreibung

        Button changeDescriptionButton = new Button("Change Description");
        Button changeDescriptionButton1 = new Button("Change");
        TextField changeDescriptionField = new TextField();
        Label changeDescriptionLabel = new Label("New Description:");

        // Zeigt die Error-Message an
        messageLabel = new Label();

        //Labels für das Hinzufügen einer Transaktion
        transactionNameLabel = new Label("Transaction Name:");
        transactionAmountLabel = new Label("Transaction Amount:");
        transactionDescriptionLabel = new Label("Transaction Description:");

        //Label zum Anzeigen des Budgets
        BudgetAmmount = new Label("Budget Ammount:");
        BudgetAmmount1 = new Label((String.valueOf(presenter.getBudgetAmmount())));

        //Label zum Anzeigen der Gesamttransaktionen
        allTransactionAmmountLabel = new Label("All Transaction Ammount:");
        allTransactionAmmountLabel1 = new Label((String.valueOf(presenter.getAllTransactions())));

        //Label zum Anzeigen des Restbetrags
        restAmmount = new Label("Rest Ammount:");
        restAmmount1 = new Label((String.valueOf(getrestAmmount())));

        //Logik implementation für den Button zum Hinzufügen einer Transaktion
        allUsersLabel = new Label("All Users:");
        allUsersLabel1 = new Label((String.valueOf(presenter.getAllUsersforBudget())));

        //Logik implementation für den Button zum Hinzufügen einer Transaktion
        addTransactButton.setOnAction(e -> {
            String transactionName = transactionNameField.getText();
            LocalDate transactiondate = LocalDate.now();
            BigDecimal transactionAmount;
            String transactionDescription = transactionDescriptionField.getText();
            try {
                transactionAmount = new BigDecimal(transactionAmountField.getText());
            } catch (NumberFormatException ex) {
                showError("Transaction Amount is not a valid number");
                return;
            }
            presenter.addTransaction(transactionName,transactionAmount, transactionDescription, transactiondate);
        });

        //Logik implementation für den Button zum Anzeigen einer Beschreibung
        showDescriptionButton.setOnAction(e -> {
            showTransactionDescription();
        });

        //Logik implementation für den Button zum Zurückgehen ins vorherige Fenster
        backButton.setOnAction(e -> {
            presenter.back();
        });

        //Logik implementation für den Button zum Löschen einer Transaktion
        deleteTransactionButton.setOnAction(e -> {
            if (transactionList.getSelectionModel().getSelectedItem() != null) {
                int transactionId = getTransactionId();
                presenter.deleteTransaction(transactionId, presenter.getBudgetId());
            } else {
                showError("Select a transaction to delete");
            }
        });

        //Logik implementation für den Button zum Ändern der Beschreibung
        changeDescriptionButton.setOnAction(e -> {
            if (transactionList.getSelectionModel().getSelectedItem() != null) {
                changeDescriptionButton1.setVisible(true);
                changeDescriptionField.setVisible(true);
                changeDescriptionLabel.setVisible(true);
                changeDescriptionButton.setVisible(false);
            }
            else{
                showError("Select a transaction to change");
            }
        });

        changeDescriptionButton1.setOnAction(e -> {
            if(transactionList.getSelectionModel().getSelectedItem() != null) {
                String description = changeDescriptionField.getText();
                int transactionId = getTransactionId();
                if (transactionId != -1) {
                    presenter.changeDescription(transactionId,description);
                    showTransactionDescription();
                    changeDescriptionButton1.setVisible(false);
                    changeDescriptionField.setVisible(false);
                    changeDescriptionLabel.setVisible(false);
                    changeDescriptionButton.setVisible(true);
                } else {
                    showError("Could not determine transaction ID.");
                }
            } else {
                showError("Select a transaction first!");
            }
        });

        //Ersteller des GUI's
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setStyle("-fx-alignment: center;");

        grid.add(transactionList, 0, 0, 2, 2);

        grid.add(transactionNameField, 0, 3);
        grid.add(transactionAmountField, 1, 3);
        grid.add(transactionDescriptionField, 2, 3);
        grid.add(transactionNameLabel, 0, 2);
        grid.add(transactionAmountLabel, 1, 2);
        grid.add(transactionDescriptionLabel, 2, 2);
        grid.add(addTransactButton, 0, 4);
        grid.add(backButton, 0, 5);
        grid.add(deleteTransactionButton, 1, 5);
        grid.add(messageLabel, 0, 6, 2, 1);
        grid.add(BudgetAmmount, 0, 7);
        grid.add(BudgetAmmount1, 1, 7);
        grid.add(allTransactionAmmountLabel, 0, 8);
        grid.add(allTransactionAmmountLabel1, 1, 8);
        grid.add(restAmmount, 0, 9);
        grid.add(restAmmount1, 1, 9);
        grid.add(allUsersLabel, 2, 0);
        grid.add(allUsersLabel1, 3, 0);
        grid.add(showTransacDescrArea, 0, 10, 4, 1);
        grid.add(showDescriptionButton, 1, 4);
        grid.add(changeDescriptionButton, 2, 4);
        grid.add(changeDescriptionButton1, 3, 5);
        changeDescriptionButton1.setVisible(false);
        grid.add(changeDescriptionField, 2, 5);
        changeDescriptionField.setVisible(false);
        grid.add(changeDescriptionLabel, 2, 4);
        changeDescriptionLabel.setVisible(false);

        Scene scene = new Scene(grid, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Transaction View");
        stage.show();

    }

    public void showError(String message) {
        messageLabel.setText(message);
        messageLabel.setStyle("-fx-text-fill: red;");
    }

    public Float getrestAmmount() {
        return presenter.getBudgetAmmount() + (Float.parseFloat(String.valueOf(presenter.getAllTransactions())));
    }

    private void showTransactionDescription() {
        String selectedTransaction = transactionList.getSelectionModel().getSelectedItem();
        if (selectedTransaction != null) {
            int transactionId = getTransactionId();
            if (transactionId != -1) {
                String description = presenter.getTransactionDescription(transactionId);
                showTransacDescrArea.setText(description);
            } else {
                showError("Could not determine transaction ID.");
            }
        } else {
            showError("Select a transaction first!");
        }

    }

    public int getTransactionId(){
        String selectedTransaction = transactionList.getSelectionModel().getSelectedItem();
        if (selectedTransaction != null) {
            String[] parts = selectedTransaction.split(" - ");
            String nameAndId = parts[0];
            int transactionId = Integer.parseInt(nameAndId.substring(nameAndId.lastIndexOf('(') + 1, nameAndId.lastIndexOf(')')));
            return transactionId;
        } else {
            return -1;
        }

    }

    public void lowBudgetWarning(){
        final Stage popup = new Stage();

        //Button und Felder erstellung
        Label warning = new Label("!Budget Low!");
        Button okButton = new Button("OK");

        //Logik des Buttons
        okButton.setOnAction(e -> {
            popup.close();
        });

        //Ersteller des GUI
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setStyle("-fx-alignment: center;");

        grid.add(warning, 0, 0);
        grid.add(okButton, 0, 1);

        Scene scene = new Scene(grid, 200, 100);
        popup.setScene(scene);
        popup.setTitle("Low Budget Warning");
        popup.show();

    }
}
