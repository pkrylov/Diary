package sample;

import javafx.util.converter.DateStringConverter;

import java.sql.*;
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

    public static void main() {
        String queryCreate = "create table if not exists tasks (id int(11) NOT NULL auto_increment, name varchar(50) NOT NULL, \n" +
                " start_date date, finish_date date, important boolean, PRIMARY KEY (id));\n";
        String queryHurImp = "Select * from tasks where CURDATE() between start_date and finish_date AND important;\n";
        String queryHurNImp = "Select * from tasks where CURDATE() between start_date and finish_date AND not important ;\n";
        String queryNHurImp = "Select * from tasks where ((CURDATE() < start_date) OR (start_date is null and finish_date is null)) AND important ;\n";
        String queryNHurNImp = "Select * from tasks where ((CURDATE() < start_date) OR (start_date is null and finish_date is null)) AND not important ;\n";



        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();


                // executing SELECT query
            stmt.executeUpdate("use diary");
            stmt.executeUpdate(queryCreate);

            rs = stmt.executeQuery(queryHurImp);
            while (rs.next()) {
                //создание объекта из БД
                Task task = new Task(rs);

//добавление 1 дня
                String count = rs.getString(2);
                Date ds = rs.getDate(3) ;
                Calendar cal = Calendar.getInstance();
                cal.setTime(ds);
                cal.add(Calendar.DATE, 1); //minus number would decrement the days
                ds.setTime(cal.getTime().getTime());
                String Strds = ds.toString();

                System.out.println(Strds);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

}
