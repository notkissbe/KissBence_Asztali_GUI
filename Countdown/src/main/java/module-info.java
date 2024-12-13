module hu.notkissbe.countdown {
    requires javafx.controls;
    requires javafx.fxml;


    opens hu.notkissbe.countdown to javafx.fxml;
    exports hu.notkissbe.countdown;
}