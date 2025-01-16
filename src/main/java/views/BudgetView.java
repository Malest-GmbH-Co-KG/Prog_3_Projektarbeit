package views;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import presenters.BudgetPresenter;
import com.Prog_3_Projektarbeit.generated.tables.pojos.Budget;

import java.util.List;

public class BudgetView {
    private final BudgetPresenter presenter;
    private ListView<String> budgetList;
    private Label messageLabel;

    public BudgetView(BudgetPresenter presenter) {
        this.presenter = presenter;
    }

    public void show(Stage stage) {
        // Layout für die Budgets
        budgetList = new ListView<>();
        TextField budgetNameField = new TextField();
        TextField budgetAmountField = new TextField();
        Button addBudgetButton = new Button("Add Budget");
        Button backButton = new Button("Back");
        Button viewMovementsButton = new Button("View Movements");
        messageLabel = new Label();
        //Aktionen für die Buttons
        addBudgetButton.setOnAction(e -> {
            String budgetName = budgetNameField.getText();
            double budgetAmount;
            try {
                budgetAmount = Double.parseDouble(budgetAmountField.getText());
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
                presenter.showMovementsForBudget(selectedBudget);
            } else {
                showError("Please select a budget to view movements.");
            }
        });
        //Layout für die Budgets
        VBox layout = new VBox(10, budgetList, budgetNameField, budgetAmountField, addBudgetButton, backButton,viewMovementsButton, messageLabel);
        Scene scene = new Scene(layout, 600, 400);
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

