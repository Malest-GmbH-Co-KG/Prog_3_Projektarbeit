package views;

import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import presenters.BudgetPresenter;

/**
 * <h2><b>Anzeige der Budgets in der ListView <br>
 * Name, Betrag und Datum in jeweils einer Spalte</b></h2>
 */
public class CustomListCell extends ListCell<String> {
    private HBox content;
    private Text leftText;
    private Text rightText;
    private Text dateText;
    private Region spacer;
    private BudgetPresenter presenter;

    public CustomListCell(BudgetPresenter presenter) {
        super();
        leftText = new Text();
        rightText = new Text();
        dateText = new Text();
        spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        content = new HBox(10); // spacing between texts
        content.getChildren().addAll(leftText, spacer, rightText, dateText);
        this.presenter = presenter;
    }


    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (item != null && !empty) {
            String[] parts = item.split(" - ");

            if (parts.length == 3) {
                // Transaktion: Name - Betrag - Datum
                leftText.setText(parts[0]);    // Name
                rightText.setText(parts[1].trim());  // Betrag
                dateText.setText(parts[2]);    // Datum
            } else if (parts.length == 2) {
                // Budget: Name - Betrag
                leftText.setText(parts[0]);    // Name
                rightText.setText(parts[1].trim());  // Betrag
                dateText.setText(""); // Kein Datum für Budgets
            } else {
                // Falls die Struktur unerwartet ist
                leftText.setText(item);
                rightText.setText("");
                dateText.setText("");
            }

            setGraphic(content);

            try {
                float restamount = Float.parseFloat(rightText.getText());

                if (restamount < 0) {
                    rightText.setStyle("-fx-fill: red;"); // Negativ = Rot
                } else if (restamount == 0) {
                    rightText.setStyle("-fx-fill: blue;"); // 0 = Blau
                }else if(parts.length == 2) { // Nur im Budget Screen
                    float amount = presenter.getBudgetAmmount(getBudgetIdfromList());
                    if(restamount < (amount * 0.2) && restamount > 0){
                        rightText.setStyle("-fx-fill: yellow;"); //Zwischen 0 und 20% vom Budget = Gelb
                    }
                    else {
                        rightText.setStyle("-fx-fill: green;"); // Positiv = Grün für den Budget Screen
                    }
                } else {
                    rightText.setStyle("-fx-fill: green;"); // Positiv = Grün
                }
            } catch (NumberFormatException e) {
                rightText.setStyle("-fx-fill: black;"); // Falls fehlerhaft, schwarz
            }

        } else {
            setGraphic(null);
        }
    }

    public int getBudgetIdfromList() {
        String selectedBudget = leftText.getText();
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