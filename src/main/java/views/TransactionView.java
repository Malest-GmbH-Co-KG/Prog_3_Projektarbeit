package views;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.jooq.Transaction;
import presenters.TransactionPresenter;
import views.CustomListCell;

import java.math.BigDecimal;

public class TransactionView {
    private final TransactionPresenter presenter;
    private ListView<String> transactionList;

    private Label messageLabel;
    private Label transactionNameLabel;
    private Label transactionAmountLabel;
    private Label transactionDescriptionLabel;

    public TransactionView(TransactionPresenter presenter) {
        this.presenter = presenter;
    }

    public void show(Stage stage) {
        transactionList = new ListView<>();
        transactionList.setCellFactory(param -> new CustomListCell());
        transactionList.setItems(presenter.getTransactionList());

        TextField transactionNameField = new TextField();
        TextField transactionAmountField = new TextField();
        TextField transactionDescriptionField = new TextField();

        Button addTransactButton = new Button("Add Transaction");
        Button backButton = new Button("Back");
        Button deleteTransactionButton = new Button("Delete Transaction");

        messageLabel = new Label();
        transactionNameLabel = new Label("Transaction Name:");
        transactionAmountLabel = new Label("Transaction Amount:");
        transactionDescriptionLabel = new Label("Transaction Description:");

        addTransactButton.setOnAction(e -> {
            String transactionName = transactionNameField.getText();
            BigDecimal transactionAmount;
            String transactionDescription = transactionDescriptionField.getText();
            try {
                transactionAmount = new BigDecimal(transactionAmountField.getText());
            } catch (NumberFormatException ex) {
                showError("Transaction Amount is not a valid number");
                return;
            }
            presenter.addTransaction(transactionName,transactionAmount, transactionDescription);
        });

        backButton.setOnAction(e -> {
            presenter.back();
        });

        deleteTransactionButton.setOnAction(e -> {
            String selectedTransaction = transactionList.getSelectionModel().getSelectedItem();
            if (selectedTransaction != null) {
                String[] parts = selectedTransaction.split(" - ");
                String nameAndId = parts[0];
                int transactionId = Integer.parseInt(nameAndId.substring(nameAndId.lastIndexOf('(') + 1, nameAndId.lastIndexOf(')')));
                presenter.deleteTransaction(transactionId, presenter.getBudgetId());
            } else {
                showError("Select a transaction to delete");
            }
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setStyle("-fx-alignment: center;");

        grid.add(transactionList, 0, 0, 2, 1);

        grid.add(transactionNameField, 0, 2);
        grid.add(transactionAmountField, 1, 2);
        grid.add(transactionDescriptionField, 2, 2);
        grid.add(transactionNameLabel, 0, 1);
        grid.add(transactionAmountLabel, 1, 1);
        grid.add(transactionDescriptionLabel, 2, 1);
        grid.add(addTransactButton, 0, 3);
        grid.add(backButton, 0, 4);
        grid.add(deleteTransactionButton, 1, 4);
        grid.add(messageLabel, 0, 5, 2, 1);

        Scene scene = new Scene(grid, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Transaction View");
        stage.show();

        presenter.loadTransactions();
    }

    public void showError(String message) {
        messageLabel.setText(message);
        messageLabel.setStyle("-fx-text-fill: red;");
    }
}
