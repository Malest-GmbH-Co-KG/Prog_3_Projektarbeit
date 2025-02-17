package views;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
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


        Button addTransactButton = new Button("Transaktion hinzufügen");
        Button showDescriptionButton = new Button("Beschreibung anzeigen");
        Button deleteTransactionButton = new Button("Transaktion löschen");
        Button backButton = new Button("Zurück");

        //Buttons, Textfelder und Labels für Änderung der Beschreibung

        Button changeDescriptionButton = new Button("Beschreibung ändern");
        Button changeDescriptionButton1 = new Button("ändern");
        TextField changeDescriptionField = new TextField();
        Label changeDescriptionLabel = new Label("Neue Beschreibung:");

        // Zeigt die Error-Message an
        messageLabel = new Label();

        //Labels für das Hinzufügen einer Transaktion
        transactionNameLabel = new Label("Transaktionsname:");
        transactionAmountLabel = new Label("Transaktionsbetrag:");
        transactionDescriptionLabel = new Label("Transaktionsbeschreibung:");

        //Label zum Anzeigen des Budgets
        BudgetAmmount = new Label("Budgetsumme:");
        BudgetAmmount1 = new Label((String.valueOf(presenter.getBudgetAmmount())));

        //Label zum Anzeigen der Gesamttransaktionen
        allTransactionAmmountLabel = new Label("Summe aller Transaktionen:");
        allTransactionAmmountLabel1 = new Label((String.valueOf(presenter.getAllTransactions())));

        //Label zum Anzeigen des Restbetrags
        restAmmount = new Label("Restsumme:");
        restAmmount1 = new Label();
        restAmmount1.setText(restAmmount_Fun());


        //Logik implementation für den Button zum Hinzufügen einer Transaktion
        allUsersLabel = new Label("Alle Nutzer:");
        allUsersLabel1 = new Label((String.valueOf(presenter.getAllUsersforBudget())));

        //Logik implementation für den Button zum Hinzufügen einer Transaktion
        addTransactButton.setOnAction(e -> {
            String transactionName = transactionNameField.getText();
            LocalDate transactiondate = LocalDate.now();
            float transactionAmount;
            String transactionDescription = transactionDescriptionField.getText();
            try {
                transactionAmount = Float.parseFloat(transactionAmountField.getText());
            } catch (NumberFormatException ex) {
                showError("Transaktionsbetrag ist keine gültige Zahl");
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
                showError("Wähle eine Transaktion, die gelöscht werden soll");
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
                showError("Wähle eine Transaktion, die geändert werden soll");
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
                    showError("Die Transaktions Id konnte nicht gefunden werden");
                }
            } else {
                showError("Wähle zuerst eine Transaktion!");
            }
        });

        //Ersteller des GUI's
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setStyle("-fx-alignment: center;");

        grid.add(transactionList, 0, 0, 2, 3);

        grid.add(transactionNameField, 0, 4);
        grid.add(transactionAmountField, 1, 4);
        grid.add(transactionDescriptionField, 2, 4);
        grid.add(transactionNameLabel, 0, 3);
        grid.add(transactionAmountLabel, 1, 3);
        grid.add(transactionDescriptionLabel, 2, 3);
        grid.add(addTransactButton, 0, 5);
        grid.add(backButton, 0, 6);
        grid.add(deleteTransactionButton, 1, 6);
        grid.add(messageLabel, 0,7 , 2, 1);
        grid.add(BudgetAmmount, 0, 8);
        grid.add(BudgetAmmount1, 0, 9);
        grid.add(allTransactionAmmountLabel, 1, 8);
        grid.add(allTransactionAmmountLabel1, 1, 9);
        grid.add(restAmmount, 2, 8);
        grid.add(restAmmount1, 2, 9);
        grid.add(allUsersLabel, 2, 0);
        grid.add(allUsersLabel1, 3, 0);
        grid.add(showTransacDescrArea, 3, 8, 2, 1);
        grid.add(showDescriptionButton, 1, 5);
        grid.add(changeDescriptionButton, 2, 5);
        grid.add(changeDescriptionButton1, 3, 6);
        changeDescriptionButton1.setVisible(false);
        grid.add(changeDescriptionField, 2, 6);
        changeDescriptionField.setVisible(false);
        grid.add(changeDescriptionLabel, 2, 5);
        changeDescriptionLabel.setVisible(false);
        showPieChart(grid);

        Scene scene = new Scene(grid, 1200, 1000);
        stage.setScene(scene);
        stage.setTitle("Transaction View");
        stage.setMaximized(true);
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
                showError("Die Transaktions Id konnte nicht gefunden werden.");
            }
        } else {
            showError("Wähle zuerst eine Transaktion!");
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
        Label warning = new Label("!Budget Niedrig!");
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
        popup.setTitle("Niedriges Budget Warnung");
        popup.show();

    }
    public String restAmmount_Fun() {
        Float restAmmount = getrestAmmount();
        String restAmmountText = String.valueOf(restAmmount);
        restAmmount1.setText(restAmmountText);
        restAmmount1.setStyle(restAmmount_setStyle(restAmmount));
        return restAmmountText;
    }

    public String restAmmount_setStyle(Float restAmmount) {
        if (restAmmount < 0) {
            return "-fx-text-fill: red;";
        } else if (restAmmount == 0) {
            return "-fx-text-fill: blue;";
        } else if (restAmmount < (presenter.getBudgetAmmount() * 0.2) && restAmmount > 0) {
            return "-fx-text-fill: yellow;";
        } else {
            return "-fx-text-fill: green;";
        }
    }
    public void showPieChart(GridPane grid) {
        float restAmount = Float.parseFloat(restAmmount1.getText());
        float budgetAmount = Float.parseFloat(BudgetAmmount1.getText());

        // Erstelle die Daten für das Pie Chart
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Restsumme", restAmount),
                new PieChart.Data("Budgetsumme", budgetAmount - restAmount)
        );

        // erstelle das Pie Chart
        PieChart pieChart = new PieChart(pieChartData);

        // Farben für die Daten
        for (PieChart.Data data : pieChart.getData()) {
            if (data.getName().equals("Restsumme")) {
                data.getNode().setStyle("-fx-pie-color: green;");
            } else {
                data.getNode().setStyle("-fx-pie-color: red;");
            }

            // Anzeige der Prozentwerte
            data.nameProperty().bind(
                    Bindings.concat(
                            data.getName(), " ", String.format("%.1f%%", (data.getPieValue() / budgetAmount) * 100)
                    )
            );
        }

        // Legende anzeigen
        pieChart.setLegendVisible(true);


        pieChart.setLegendSide(Side.BOTTOM);


        grid.add(pieChart, 0, 10, 2, 1);
    }

    public void highBudgetWarning() {
        showError("Budget ist zu groß");



    }
    public void setStageMaximized(Stage stage) {
        stage.setMaximized(true);
    }
}

