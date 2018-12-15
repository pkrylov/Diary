package sample;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.Main;

public class DayController {

    @FXML
    private Label day;

    @FXML
    private Label month;

    @FXML
    private Label year;


    @FXML
    public void initialize() {
        day.setText(String.valueOf(Main.DatePicker.getValue().getDayOfMonth()));
        month.setText(String.valueOf(Main.DatePicker.getValue().getMonth()));
        year.setText(String.valueOf(Main.DatePicker.getValue().getYear()));
    }
}
