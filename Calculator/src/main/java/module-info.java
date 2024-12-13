module hu.notkissbe.calculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens hu.notkissbe.calculator to javafx.fxml;
    exports hu.notkissbe.calculator;
}