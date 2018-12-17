package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.converter.DateStringConverter;

import java.sql.*;
import java.time.format.DateTimeFormatter;

import java.util.Calendar;

public class JavaToMySQL {
    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String user = "root";
    private static final String password = "123123";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;


    static String queryCreate = "create table if not exists tasks (id int(11) NOT NULL auto_increment, name varchar(50) NOT NULL, \n" +
            " start_date date, finish_date date, important boolean, PRIMARY KEY (id));\n";
     static String queryHurImp = "Select * from tasks where CURDATE() between start_date and finish_date AND important;\n";
    static String queryHurNImp = "Select * from tasks where CURDATE() between start_date and finish_date AND not important ;\n";
    static String queryNHurImp = "Select * from tasks where ((CURDATE() < start_date) OR (start_date is null and finish_date is null)) AND important ;\n";
    static String queryNHurNImp = "Select * from tasks where ((CURDATE() < start_date) OR (start_date is null and finish_date is null)) AND not important ;\n";

    public static void main() {

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            stmt.executeUpdate("use diary");
            stmt.executeUpdate(queryCreate);
           // TakeData();

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        /*finally {
            //close connection ,stmt and resultset here
           // try { con.close(); } catch(SQLException se) { /*can't do anything */ //}
         //   try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
          //  try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        //}
    }
    public static ObservableList<String> TakeData() {
        String queryForDay = "Select * from tasks where '"+ Main.DatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")) +"' between start_date and finish_date;\n";
        System.out.println(queryForDay);
        ObservableList<String> list = FXCollections.observableArrayList() ;
        list.addAll();
        try {
            stmt.executeUpdate("use diary");
            rs = stmt.executeQuery(queryForDay);
            while (rs.next()) {
                Task task = new Task(rs);
                System.out.println(task.name);
                list.addAll(task.name);

                //создание объекта из БД
                //Task task = new Task(rs);
//добавление 1 дня
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(list);
        return list;

    }

}
