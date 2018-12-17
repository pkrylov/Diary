package sample;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import sample.JavaToMySQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main extends Application {

    private static Stage primaryStage; //главная сцена, используется в каждой процедуре
    static DatePicker DatePicker; //дата, получаемая при нажатии кнопки в тайле календаря
    static TabPane mainLayout;
    static BorderPane calendarLayout;
    //public static final ObservableList<String> names = FXCollections.observableArrayList();

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
        JavaToMySQL.main();
        initMainLayout(primaryStage);

    }
    public void initMainLayout(Stage primaryStage) { // запуск тайла календаря
        try {


            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(Main.class.getResource("calendar.fxml"));
            calendarLayout = loader2.load();

            DatePicker = new DatePicker(LocalDate.now());
            DatePickerSkin datePickerSkin = new DatePickerSkin(DatePicker);
            Node popupContent = datePickerSkin.getPopupContent();
            calendarLayout.setBottom(popupContent);


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("sample.fxml"));
            mainLayout = loader.load();


            mainLayout.getTabs().get(0).setContent(calendarLayout);
            Scene scene2 = new Scene(mainLayout);
            primaryStage.setScene(scene2);
            primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
        initDateLayout(getPrimaryStage());
        initMatrixLayout(getPrimaryStage());
    }




    public static void initDateLayout(Stage primaryStage) {// запуск тайла по дням
try {
    FXMLLoader loader2 = new FXMLLoader();
    loader2.setLocation(Main.class.getResource("day2.fxml"));
    GridPane dayLayout = loader2.load();

    ObservableList<String> lefts = FXCollections.observableArrayList();
    lefts = JavaToMySQL.TakeData("For Day");
    final ListView<String> leftListView = new ListView<String>(lefts);
    dayLayout.add(leftListView,1,0);
    mainLayout.getTabs().get(2).setContent(dayLayout);
    Scene scene = new Scene(mainLayout);
    primaryStage.setScene(scene);
    primaryStage.show();
} catch (Exception e ){
    System.out.println(e);
}
    }

    public static void initMatrixLayout(Stage primaryStage) {// запуск тайла матрицы
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("matrix.fxml"));
            GridPane matrixLayout = loader.load();

            ObservableList<String> ImpHur = FXCollections.observableArrayList();
            ObservableList<String> ImpNHur = FXCollections.observableArrayList();
            ObservableList<String> NImpHur = FXCollections.observableArrayList();
            ObservableList<String> NImpNHur = FXCollections.observableArrayList();

            ImpHur = JavaToMySQL.TakeData(JavaToMySQL.queryHurImp);
            //System.out.println(ImpHur.toArray());
            final ListView<String> HurImpListView = new ListView<String>(ImpHur);
            matrixLayout.add(HurImpListView,1,1);


            NImpHur = JavaToMySQL.TakeData(JavaToMySQL.queryHurNImp);
            final ListView<String> HurNImpListView = new ListView<String>(NImpHur);
            matrixLayout.add(HurNImpListView,2,1);

            ImpNHur = JavaToMySQL.TakeData(JavaToMySQL.queryNHurImp);
            final ListView<String> NHurImpListView = new ListView<String>(ImpNHur);
            matrixLayout.add(NHurImpListView,1,2);

            NImpNHur = JavaToMySQL.TakeData(JavaToMySQL.queryNHurNImp);
            //System.out.println(ImpHur.toArray());
            final ListView<String> NHurNImpListView = new ListView<String>(NImpNHur);
            matrixLayout.add(NHurNImpListView,2,2);


            mainLayout.getTabs().get(1).setContent(matrixLayout);
            Scene scene = new Scene(mainLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e ){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
