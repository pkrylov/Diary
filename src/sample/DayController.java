package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;

public class DayController {
    @FXML
    public Label day;
    @FXML
    public Label month;
    @FXML
    public Label year;
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

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("addTask.fxml"));

            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(Main.getPrimaryStage());
            VBox dialogVbox = loader.load();
            dialogVbox.getChildren().add(new Text("This is a Dialog"));
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        }catch (Exception e ) {
            System.out.println(e);
        }
    }



}
