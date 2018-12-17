package sample;
import javafx.fxml.FXML;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class CalendarController {

    @FXML
    public void CalendarClick()
    {
        Main.initDateLayout();
    }

}
