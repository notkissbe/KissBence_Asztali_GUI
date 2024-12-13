package hu.notkissbe.countdown;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.time.Duration;
//import javafx.util.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.Period;


public class HelloController {
    @FXML
    private Label eredmenyL;

    @FXML
    private TextField datumInput;

    private Timeline timeline;




    @FXML
    private void startCountdown() {
        String input = datumInput.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

        LocalDateTime targetDateTime;
        try {
            targetDateTime = LocalDateTime.parse(input, formatter);
            if (targetDateTime.isBefore(LocalDateTime.now())) {
                showAlert(Alert.AlertType.ERROR, "Hiba", "A megadott időpontnak a jövőben kell lennie.");
                return;
            }
        } catch (DateTimeParseException e) {
            showAlert(Alert.AlertType.ERROR, "Hiba", "Érvénytelen dátum formátum.");
            return;
        }

        if (timeline != null) {
            timeline.stop();
        }

        LocalDateTime finalTargetDateTime = targetDateTime;
        timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), event -> {
            LocalDateTime now = LocalDateTime.now();
            if (!now.isBefore(finalTargetDateTime)) {
                timeline.stop();
                eredmenyL.setText("Az idő lejárt.");
                showAlert(Alert.AlertType.INFORMATION, "Idő lejárt", "Az idő lejárt!");
                return;
            }

            updateCountdownDisplay(now, finalTargetDateTime);
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateCountdownDisplay(LocalDateTime now, LocalDateTime targetDateTime) {
        Period period = Period.between(now.toLocalDate(), targetDateTime.toLocalDate());
        Duration duration = Duration.between(now, targetDateTime);

        long hours = duration.toHoursPart();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();

        eredmenyL.setText(String.format("%d év %d hó %d nap %02d:%02d:%02d",
                period.getYears(), period.getMonths(), period.getDays(), hours, minutes, seconds));
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}