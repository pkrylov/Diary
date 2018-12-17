package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import sample.Main;

public class DayController {
    @FXML
    public Label day;
    @FXML
    public Label month;
    @FXML
    public Label year;
    @FXML
    public ListView<String> list = new ListView <String>();

    public static final ObservableList <String> names =
            FXCollections.observableArrayList();

    public void initialize() {
        day.setText(String.valueOf(Main.DatePicker.getValue().getDayOfMonth()));
        month.setText(String.valueOf(Main.DatePicker.getValue().getMonth()));
        year.setText(String.valueOf(Main.DatePicker.getValue().getYear()));
        list.setVisible(false);
        names.addAll("123","345");
        names.addAll("567", "789");
        System.out.println(names);
        list.setItems(names);
        System.out.println(list);
    }



}
