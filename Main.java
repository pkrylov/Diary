package sample;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class Main extends Application {

    private static Stage primaryStage; //главная сцена, используется в каждой процедуре
    static DatePicker DatePicker; //дата, получаемая при нажатии кнопки в тайле календаря

    static Stage getPrimaryStage(){ // получение главной сцены (для .fxml кода)
        return primaryStage;
    }
    static LocalDate getDate(){
        return DatePicker.getValue();
    }

    @Override
    public void start(Stage primaryStage) { //начало, запуск тайла календаря
        Main.primaryStage = primaryStage;
        Main.primaryStage.setTitle("Diary");

        initMainLayout(primaryStage);

    }
    public void initMainLayout(Stage primaryStage) { // запуск тайла календаря
        try {


            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(Main.class.getResource("calendar.fxml"));
            BorderPane calendarLayout = loader2.load();

            DatePicker = new DatePicker(LocalDate.now());
            DatePickerSkin datePickerSkin = new DatePickerSkin(DatePicker);
            Node popupContent = datePickerSkin.getPopupContent();
            calendarLayout.setBottom(popupContent);


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("sample.fxml"));
            TabPane mainLayout = loader.load();


            mainLayout.getTabs().get(0).setContent(calendarLayout);
            Scene scene2 = new Scene(mainLayout);
            primaryStage.setScene(scene2);
            primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void initDateLayout(Stage primaryStage) {// запуск тайла по дням
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("day.fxml"));
            Pane dayLayout = loader.load();

            Scene scene = new Scene(dayLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
