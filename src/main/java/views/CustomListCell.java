package views;

import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

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

    public CustomListCell() {
        super();
        leftText = new Text();
        dateText = new Text();
        rightText = new Text();
        spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        content = new HBox(10); // spacing between texts
        content.getChildren().addAll(leftText, spacer, rightText, dateText);
    }

    /**
     *  <h3>Spaltenaufteilung + Farbcodierung</h3>
     *  Rot = Abzüge<br>
     *  Grün = Einnahmen<br>
     *  Schwarz = Allgemeine Informationen (Bezeichnung, ID, Nutzer, Datum)
      */
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (item != null && !empty) {
            String[] parts = item.split(" - ");

            leftText.setText(parts[0]);
            dateText.setText(parts[1]);
            rightText.setText(parts.length > 2 ? parts[2] : "");
            setGraphic(content);
            try {
                float ammount = Float.parseFloat(rightText.getText());
                if (ammount < 0) {
                    rightText.setStyle("-fx-fill: red;");
                } else if (ammount == 0) {
                    rightText.setStyle("-fx-fill: blue;");

                }else {
                rightText.setStyle("-fx-fill: green;");
                }} catch (NumberFormatException e) {
                rightText.setStyle("-fx-fill: black;");
            }

        } else {
            setGraphic(null);
        }
    }
}