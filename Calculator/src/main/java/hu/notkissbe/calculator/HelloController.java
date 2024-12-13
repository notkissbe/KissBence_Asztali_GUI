package hu.notkissbe.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class HelloController {
    @FXML
    private Button plusB;
    @FXML
    private Button minusB;
    @FXML
    private Button timesB;
    @FXML
    private Button slashB;
    @FXML
    private Button modB;

    //spinner :C
    @FXML
    private TextField elsoSzam;
    @FXML
    private TextField masodikSzam;


    @FXML
    Label eredmenyL;

    @FXML
    protected void initialize() {
        setNumberOnly(elsoSzam);
        setNumberOnly(masodikSzam);
    }

    private void setNumberOnly(TextField textField) {
        StringConverter<Number> converter = new NumberStringConverter();

        textField.setTextFormatter(new javafx.scene.control.TextFormatter<>(converter, null, c -> {
            if (c.getControlNewText().matches("-?\\d*(\\.\\d*)?")) {
                return c;
            }
            return null;
        }));
    }



    protected boolean CheckInputs() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Hiba a bemeneti értékeknél!");
        if (elsoSzam.getText().isEmpty() || masodikSzam.getText().isEmpty()) {
            alert.setContentText("Üres a bemeneti mező!");
            alert.showAndWait();
            return false;

        } else if (!elsoSzam.getText().matches("[+-]?([0-9]*[.])?[0-9]+") || !(masodikSzam.getText().matches("[0-9]+"))) {
            alert.setContentText("Nem számot adtál meg!");
            alert.showAndWait();
            return false;
        } else return true;

    }

    @FXML
    protected void onPlusButtonClick() {
        if (CheckInputs()) {
            Float eredmeny = Float.parseFloat(elsoSzam.getText())+Float.parseFloat(masodikSzam.getText());
            eredmenyL.setText(eredmeny.toString());
        }

    }

    @FXML
    protected void onMinusButtonClick(){
        if (CheckInputs()) {
            Double eredmeny = Double.parseDouble(elsoSzam.getText())-Double.parseDouble(masodikSzam.getText());
            eredmenyL.setText(eredmeny.toString());
        }
    }

    @FXML
    protected void onTimesButtonClick() {
        if (CheckInputs()) {
            Float eredmeny = Float.parseFloat(elsoSzam.getText()) * Float.parseFloat(masodikSzam.getText());
            eredmenyL.setText(eredmeny.toString());
        }
    }

    @FXML
    protected void onSlashButtonClick() {
        if (CheckInputs()) {
            Float eredmeny = Float.parseFloat(elsoSzam.getText()) / Float.parseFloat(masodikSzam.getText());
            eredmenyL.setText(eredmeny.toString());
        }
    }

    @FXML
    protected void onModButtonClick() {
        if (CheckInputs()) {
            Float eredmeny = Float.parseFloat(elsoSzam.getText()) % Float.parseFloat(masodikSzam.getText());
            eredmenyL.setText(eredmeny.toString());
        }
    }


}