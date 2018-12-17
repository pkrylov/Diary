package sample;

import java.sql.Date;
import java.sql.ResultSet;

public class Task {
    static String name;
    static Date start_date;
    static Date finish_date;
    static boolean important;

    Task (ResultSet rs){
        try {
            name = rs.getString(2);
            start_date = rs.getDate(3);
            finish_date = rs.getDate(4);
            important = rs.getBoolean(5);
        } catch (Exception e){
            System.out.println(e);
        }
    }







}
