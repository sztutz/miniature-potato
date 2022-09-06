package studentscoredb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Stuart Anderson, 30056472, 30/08/2022
// OOP2, AT1-Activity 3
// Student Score Database
// A Java program that manages students’ scores in database is required. The program will read and
// update records in the database, and show them on the screen. Do the task below:
// o Design a database table that contains two columns, subject and score.
// o Your program, when it begins, will create the database/table and records in MySQL.
//   Name your database as student_management and name your table as
//    student_score.
//   If a database with the same name already exists in MySQL, it must be deleted before
//    database creation.
//   Add a few records in the table, e.g. English:95, Math:98, Science:89
//   Provide your sql of database/table creation in a text file. Name this file as
//    student_management.sql.
// o Demonstrate reading the records from the table and show them on the screen.
// o Demonstrate updating the records of the table and show them on the screen.
// o Use the commit() method for database transaction integrity.
// o Save your main class file as StudentScoreDB.java.
        
public class StudentScoreDB {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/student_management"; // 3306 is the default port
        String user = "root";
        String password = "";
        
        Connection con = null; // JDBC connection
        Statement stmt = null; // SQL statement object
        String query; // SQL query string
        ResultSet result = null; // results after SQL execution
        
        try {
            con = DriverManager.getConnection(url, user, password); // connect to MYSQL
            stmt = con.createStatement();
            
            query = "select * from student_score";
            result = stmt.executeQuery(query); // execute the SQL query
            
            System.out.printf("%-10s%-35s%-12s %-9s%-7s%-7s\n", "Subject", "Score");
            
            while(result.next()) { // loop until the end of the results
                String subject = result.getString("Subject");
                int score = result.getInt("Score");
                
                System.out.printf("%8d %-35s%10d %7.2f %7d%7d\n", subject, score);
            }
        } catch (Exception ex) {
            System.out.println("SQLException caught: " + ex.getMessage());
        } finally {
            // Close all database objects
            try {
                if(result != null) {
                    result.close();
                }
                
                if(stmt != null) {
                    stmt.close();
                }
                
                if(con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQLException caught: " + ex.getMessage());
            }
        }
    }
}
