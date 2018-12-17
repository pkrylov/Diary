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
    

    @FXML
    public void previous(){
        Main.DatePicker.setValue(Main.DatePicker.getValue().minusDays(1));
        Main.initDateLayout(Main.getPrimaryStage());
    }
    public void next(){
        Main.DatePicker.setValue(Main.DatePicker.getValue().plusDays(1));
        Main.initDateLayout(Main.getPrimaryStage());
    }

    public void addTask(){
        sample.Main.initDialogBox();
    }