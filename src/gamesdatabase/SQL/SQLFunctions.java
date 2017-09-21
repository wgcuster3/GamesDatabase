/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamesdatabase.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Wesley
 */
public class SQLFunctions {
    private final String dbDriver = "com.mysql.jdbc.Driver";
    private final String url;
    private final String user;
    private final String password;
    private Connection connection;
    
    public SQLFunctions(String url, String user, String password) {
        this.url = url;    
        this.user = user;
        this.password = password;
    }
    
    public Connection connect(){
        if(connection == null){
            try{
                Class.forName(dbDriver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException exception){
                exception.printStackTrace();
                System.out.println("Cannot find the driver in the classpath!");
            } catch (SQLException exception){
                exception.printStackTrace();
                System.out.println("Cannot connect to the database!");
            }
        }
        
        return this.connection;
    }
    
    public void disconnect(){
        if (connection != null){
            try{
                connection.close();
                connection = null;
            } catch (SQLException exception){
                exception.printStackTrace();
            }
        }
    }
    
    public static void printSQLException(SQLException exception){
        System.out.println("SQLException: " + exception.getMessage());
        System.out.println("SQLState: " + exception.getSQLState());
        System.out.println("VenderError: " + exception.getErrorCode());
    }
}
