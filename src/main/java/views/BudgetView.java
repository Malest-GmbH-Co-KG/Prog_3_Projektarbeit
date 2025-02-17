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
    private  BudgetPresenter presenter;
    private  TransactionPresenter transactionPresenter;
    private ListView<String> budgetList;

    private Label messageLabel;
    private Label budgetNameLabel;
    private Label budgetAmountLabel;
    private Label addUsertoBudgetLabel;

    public BudgetView(BudgetPresenter presenter) {
        this.presenter = presenter;

    }
    public void setTransactionPresenter(TransactionPresenter transactionPresenter) {
        this.transactionPresenter = transactionPresenter;
    }



    public void show(Stage stage) {
        // Layout für die Budgets
        budgetList = new ListView<>();
        budgetList.setCellFactory(param -> new CustomListCell(presenter));
        budgetList.setItems(presenter.getBudgetList());


        TextField budgetNameField = new TextField();
        TextField budgetAmountField = new TextField();
        TextField addUsertoBudgetField = new TextField();


        Button addBudgetButton = new Button("Add Budget");
        Button backButton = new Button("Logout");
        Button viewMovementsButton = new Button("View Transactions");
        Button deleteBudgetButton = new Button("Delete Budget");
        Button addUsertoBudgetButton = new Button("Add User to Budget");
        Button addUsertoBudgetButton1 = new Button("Add");

        messageLabel = new Label();
        budgetNameLabel = new Label("Budget Name:");
        budgetAmountLabel = new Label("Budget Amount:");
        addUsertoBudgetLabel = new Label("Username:");
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
            if (budgetList.getSelectionModel().getSelectedItem() != null) {
                int budgetId = getBudgetIdfromList();
                //Aufruf der Methode um die Bewegungen anzuzeigen
                transactionPresenter.showTransactions(budgetId, presenter.getUsername());
            } else {
                showError("Please select a budget to view transactions.");
            }
        }

        );

        deleteBudgetButton.setOnAction(e -> {
            if (budgetList.getSelectionModel().getSelectedItem() != null) {
                int budgetId = getBudgetIdfromList();
                presenter.deleteBudget(budgetId);
            } else {
                showError("Please select a budget to delete.");
            }
        });
        addUsertoBudgetButton.setOnAction(e -> {
            if (budgetList.getSelectionModel().getSelectedItem() != null) {
                showMessage("Please enter the username of the user you want to add to the budget.");
                addUsertoBudgetButton1.setVisible(true);
                addUsertoBudgetField.setVisible(true);
                addUsertoBudgetLabel.setVisible(true);
                addUsertoBudgetButton.setVisible(false);


            }
            else {
                showError("Please select a budget to add a user.");
            }
        });

        addUsertoBudgetButton1.setOnAction(e -> {
            if (budgetList.getSelectionModel().getSelectedItem() != null) {
                int budgetId = getBudgetIdfromList();
                String username = addUsertoBudgetField.getText();
                if (presenter.addUserToBudget(username, budgetId) == false) {
                    showError("User does not exist or is already added to the budget.");
                } else {
                    showMessage("User added to budget.");
                }
                addUsertoBudgetButton.setVisible(true);
                addUsertoBudgetButton1.setVisible(false);
                addUsertoBudgetField.setVisible(false);
                addUsertoBudgetLabel.setVisible(false);
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
        grid.add(addUsertoBudgetButton, 0, 6);
        grid.add(addUsertoBudgetLabel, 0, 7);
        grid.add(addUsertoBudgetField, 1, 7);
        grid.add(addUsertoBudgetButton1, 0, 8);
        addUsertoBudgetButton1.setVisible(false);
        addUsertoBudgetField.setVisible(false);
        addUsertoBudgetLabel.setVisible(false);








        Scene scene = new Scene(grid, 800, 600);
        stage.setScene(scene);
        stage.setTitle("User Budgets");
        stage.show();

    }


    public void showError(String message) {
        messageLabel.setText(message);
        messageLabel.setStyle("-fx-text-fill: red;");
    }

    public void showMessage(String message) {
        messageLabel.setText(message);
        messageLabel.setStyle("-fx-text-fill: green;");
    }

    public int getBudgetIdfromList() {
        String selectedBudget = budgetList.getSelectionModel().getSelectedItem();
        if (selectedBudget != null) {
            String[] parts = selectedBudget.split(" - ");
            String nameAndId = parts[0];
            int budgetId = Integer.parseInt(nameAndId.substring(nameAndId.lastIndexOf('(') + 1, nameAndId.lastIndexOf(')')));
            return budgetId;
        } else {

            return -1;
        }
    }



}

