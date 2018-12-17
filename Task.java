package sample;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Task {
    static String name;
    static Date start_date;
    static Date finish_date;
    static boolean important;

    Task(ResultSet rs) {
        try {
            name = rs.getString(2);
            start_date = rs.getDate(3);
            finish_date = rs.getDate(4);
            important = rs.getBoolean(5);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    Task(String name, Date start_date, Date finish_date, boolean important) {
        this.name = name;
        this.start_date = start_date;
        this.finish_date = finish_date;
        this.important = important;
    }

    static void addToDB() {
        String queryAdd = "INSERT INTO tasks (name,start_date,finish_date," +
                "important) VALUES (" + "'" + name + "','" + String.format(start_date.toString(), "yyyy.MM.dd") + "','" + String.format(finish_date.toString(), "yyyy.MM.dd")
                + "'," + important + ");";
        try {
            sample.JavaToMySQL.stmt.executeUpdate(queryAdd);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

    }
}







