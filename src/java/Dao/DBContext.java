/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;



import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author Admin
 */
public class DBContext {
      public Connection getConnection()throws Exception {
        String url = "jdbc:sqlserver://"+serverName+":"+portNumber + "\\" + instance +";databaseName="+dbName+";encrypt=true;trustServerCertificate=true";
        if(instance == null || instance.trim().isEmpty())
            url = "jdbc:sqlserver://"+serverName+":"+portNumber +";databaseName="+dbName+";encrypt=true;trustServerCertificate=true";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }   
   
  
    private final String serverName = "localhost";
    private final String dbName = "ws1";
    private final String portNumber = "1433";
    private final String instance="";
    private final String userID = "nghia";
    private final String password = "Nghia08@"; 
    
    public static void main(String[] args) throws Exception {
        System.out.println(new DBContext().getConnection());
    }
   
}

