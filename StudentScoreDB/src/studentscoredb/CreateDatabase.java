package studentscoredb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase {
    public static void createAStudent_ManagementDB() {
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
            
            System.out.println("\n#############################");
            System.out.println(" Database is being created...");
            System.out.println("#############################\n");
            query = "drop database if exists student_management;";
            stmt.executeUpdate(query);
            query = "create database student_management;";
            stmt.executeUpdate(query);
            query = "use student_management;";
            stmt.executeUpdate(query);
            query = """
                create table student_score(
                Subject varchar(32),
                Score integer;
                """;
            stmt.executeUpdate(query);
            query = """
                insert into student_score
                (Subject, Score)
                values
                ("Math", 76),
                ("English", 50),
                ("Chemistry", 83),
                ("Physics", 92);        
                """;
            stmt.executeUpdate(query);
                        
            query = "select * from student_score";
            result = stmt.executeQuery(query); // execute the SQL query
            
            System.out.printf("%-15s %-10s\n", "Subject", "Score");
            while(result.next()) { // loop until the end of the results
                String subject = result.getString("Subject");
                int score = result.getInt("Score");
                
                System.out.printf("%-15s %-10d\n", subject, score);
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
