package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.awt.*;
import java.time.ZoneId;
import java.sql.Date;

import static sample.Task.addToDB;

/**
 * Created by pasho on 17.12.2018.
 */
public class AddTaskController {
    @FXML
    public TextField name;
    @FXML
    public DatePicker startDate;
    @FXML
    public DatePicker finishDate;
    @FXML
    public CheckBox important;
    @FXML
    public void addTask(){
    sample.Task task = new sample.Task(name.getText(), java.sql.Date.valueOf(startDate.getValue()),java.sql.Date.valueOf(finishDate.getValue()),important.isSelected());
    task.addToDB();
    Main.initDateLayout(Main.getPrimaryStage());
    Main.dialog.close();
    }
}
