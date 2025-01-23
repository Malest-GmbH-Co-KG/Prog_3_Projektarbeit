package views;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import presenters.BudgetPresenter;
import presenters.TransactionPresenter;
import views.CustomListCell;

public class BudgetView {
    private final BudgetPresenter presenter;
    private  TransactionPresenter transactionPresenter;
    private ListView<String> budgetList;

    private Label messageLabel;
    private Label budgetNameLabel;
    private Label budgetAmountLabel;

    public BudgetView(BudgetPresenter presenter) {
        this.presenter = presenter;

    }
    public void setTransactionPresenter(TransactionPresenter transactionPresenter) {
        this.transactionPresenter = transactionPresenter;
    }

    public void show(Stage stage) {
        // Layout für die Budgets
        budgetList = new ListView<>();
        budgetList.setCellFactory(param -> new CustomListCell());
        budgetList.setItems(presenter.getBudgetList());


        TextField budgetNameField = new TextField();
        TextField budgetAmountField = new TextField();

        Button addBudgetButton = new Button("Add Budget");
        Button backButton = new Button("Back");
        Button viewMovementsButton = new Button("View Transactions");
        Button deleteBudgetButton = new Button("Delete Budget");

        messageLabel = new Label();
        budgetNameLabel = new Label("Budget Name:");
        budgetAmountLabel = new Label("Budget Amount:");
        //Aktionen für die Buttons
        addBudgetButton.setOnAction(e -> {
            String budgetName = budgetNameField.getText();
            float budgetAmount;
            try {
                budgetAmount = Float.parseFloat(budgetAmountField.getText());
            } catch (NumberFormatException ex) {
                showError("Amount must be a valid number.");
                return;
            }
            presenter.addBudget(budgetName, budgetAmount);
        });

        backButton.setOnAction(e -> {
            presenter.back();
        });

        viewMovementsButton.setOnAction(e -> {
            String selectedBudget = budgetList.getSelectionModel().getSelectedItem();
            if (selectedBudget != null) {
                String[] parts = selectedBudget.split(" - ");
                String nameAndId = parts[0];
                //BudgetID wird aus dem String extrahiert und kann somit an Transaction Presenter weiter gegeben werden
                int budgetId = Integer.parseInt(nameAndId.substring(nameAndId.lastIndexOf('(') + 1, nameAndId.lastIndexOf(')')));
                //Aufruf der Methode um die Bewegungen anzuzeigen

                transactionPresenter.showTransactions(budgetId, presenter.getUsername());
            } else {
                showError("Please select a budget to view transactions.");
            }

        });

        deleteBudgetButton.setOnAction(e -> {
            String selectedBudget = budgetList.getSelectionModel().getSelectedItem();
            if (selectedBudget != null) {
                String[] parts = selectedBudget.split(" - ");
                String nameAndId = parts[0];
                int budgetId = Integer.parseInt(nameAndId.substring(nameAndId.lastIndexOf('(') + 1, nameAndId.lastIndexOf(')')));
                presenter.deleteBudget(budgetId);
            } else {
                showError("Please select a budget to delete.");
            }
        });
        //Layout für die Budgets
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setStyle("-fx-alignment: center;");

        grid.add(budgetList, 0, 0, 2, 1);

        grid.add(budgetNameField, 0, 2);
        grid.add(budgetAmountField, 1, 2);
        grid.add(budgetNameLabel, 0, 1);
        grid.add(budgetAmountLabel, 1, 1);
        grid.add(addBudgetButton, 1, 3);
        grid.add(viewMovementsButton, 0, 3);
        grid.add(backButton, 0, 4);
        grid.add(messageLabel, 0, 5, 2, 1);
        grid.add(deleteBudgetButton, 1, 4);


        Scene scene = new Scene(grid, 600, 400);
        stage.setScene(scene);
        stage.setTitle("User Budgets");
        stage.show();

        presenter.loadBudgets();
    }


    public void showError(String message) {
        messageLabel.setText(message);
        messageLabel.setStyle("-fx-text-fill: red;");
    }

    public void showMessage(String message) {
        messageLabel.setText(message);
        messageLabel.setStyle("-fx-text-fill: green;");
    }


}

