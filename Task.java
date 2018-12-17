package sample;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;

public class Task {
    static int id;
    static String name;
    static Date start_date;
    static Date finish_date;
    static boolean important;

    Task (ResultSet rs){
        try {
            this.id = rs.getInt(1);
            this.name = rs.getString(2);
            this.start_date = rs.getDate(3);
            this.finish_date = rs.getDate(4);
            this.important = rs.getBoolean(5);
        } catch (Exception e){
            System.out.println(e);
        }
    }

    Task(String name, Date start_date, Date finish_date, boolean important){
        this.id = 0;
        this.name = name;
        this.start_date = start_date;
        this.finish_date = finish_date;
        this.important = important;
    }

    static void AddToDB (){
        String queryAdd = "INSERT INTO tasks (name,start_date,finish_date," +
                "important) VAlUES (" + name +",'"+String.format(finish_date.toString(),"yyyy.MM.dd") +"','"+ String.format(start_date.toString(),"yyyy.MM.dd")
                +"',"+ important +");";

    }

    public String ToString (){
        String res = (this.id + " " + this.name);
        return res;
    }






}
