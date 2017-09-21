/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamesdatabase.SQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Wesley
 */
public class Games {
    //This whole thing can be set up to use Hibernate eventually
    private final SQLFunctions dbConn;
    private Connection connection;
    private Statement stmt;
    private ResultSet rs;
    
    public Games(SQLFunctions dbConn){
        //Object declarations for using the database
        this.dbConn = dbConn;
        this.connection = null;
        this.stmt = null;
        this.rs = null;
    }
    
    //This will pull all games from the database
    public void inquireAllGames(){
        inquire("SELECT * FROM games");
    }
    
    public void inquireSpecificGame(String system, String title){
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM games WHERE ");
        query.append("system = '");
        query.append(system);
        query.append("' AND title = '");
        query.append(title);
        query.append("'");
        
        inquire(query.toString());
    }
    
    public void inquireBySystem(String system){
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM games WHERE ");
        query.append("system = '");
        query.append(system);
        query.append("'");
                
        inquire(query.toString());
    }
    
    public void inquireByTitle(String title){
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM games WHERE ");
        query.append("title = '");
        query.append(title);
        query.append("'");
        
        inquire(query.toString());
    }
    
    public void inquireByStatus(String status){
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM games WHERE ");
        query.append("status = '");
        query.append(status);
        query.append("'");
        
        inquire(query.toString());
    }
    
    public void inquireByStorageMethond(String storageMethod){
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM games WHERE ");
        query.append("digital_physical = '");
        query.append(storageMethod);
        query.append("'");
        
        inquire(query.toString());
    }
    
    private void inquire(String query){
        prepSQL();
        
        //If prepSQL is successful we can form a query and get its ResultSet
        if(stmt != null){
            try{
                rs = stmt.executeQuery(query);
            } catch (SQLException exception){
                dbConn.printSQLException(exception);
                System.out.println("Error executing query!");
            }
        }
        
        //If the ResultSet is not empty we can iterate through it and print results
        if(rs != null){
            try{
                while(rs.next()){
                    //the getstring method can use ints or strings (column names)                    
                    System.out.println("Title: " + rs.getString("title"));
                    System.out.println("System: " + rs.getString("system"));
                    System.out.println("Status: " + rs.getString("status"));
                    
                    if(rs.getString("digital_physical").equals("P")){
                        System.out.println("Type: Physical");
                    } else {
                        System.out.println("Type: Digital");
                    }
                    
                    System.out.println("");
                }
            } catch (SQLException exception){
                dbConn.printSQLException(exception);
                System.out.println("Error reading through result set.");
            }
        }
        
        finishSQL();
    }
    
    public void add(String system, String title, String status, String storageMethod){
        prepSQL();
        
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO games VALUES (RTRIM('");
        query.append(system);
        query.append("'), RTRIM('");
        query.append(title);
        query.append("'), RTRIM('");
        query.append(status);
        query.append("'), '");
        query.append(storageMethod);
        query.append("');");
        
        //If prepSQL is successful we can form a query and get its ResultSet
        if(stmt != null){
            try{
                stmt.executeUpdate(query.toString());
            } catch (SQLException exception){
                dbConn.printSQLException(exception);
                System.out.println("Error executing query!");
            }
        }
        
        finishSQL();
    }
    
    public void update(String system, String title, String status, String storageMethod){
        prepSQL();
        
        StringBuilder query = new StringBuilder();
        query.append("UPDATE games SET status = RTRIM('");
        query.append(status);
        query.append("'), physical_digital = '");
        query.append(storageMethod);
        query.append("' WHERE system = '");
        query.append(system);
        query.append("' AND title = '");
        query.append(title);
        query.append("';");
          
        //If prepSQL is successful we can form a query and get its ResultSet
        if(stmt != null){
            try{
                stmt.executeUpdate(query.toString());
            } catch (SQLException exception){
                dbConn.printSQLException(exception);
                System.out.println("Error executing query!");
            }
        }
        
        finishSQL();
    }
    
    public void delete(String system, String title){
        prepSQL();
        
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM games WHERE system = '");
        query.append(system);
        query.append("' AND title = '");
        query.append(title);
        query.append("';");
        
        //If prepSQL is successful we can form a query and get its ResultSet
        if(stmt != null){
            try{
                stmt.executeUpdate(query.toString());
            } catch (SQLException exception){
                dbConn.printSQLException(exception);
                System.out.println("Error executing query!");
            }
        }
        
        finishSQL();
    }
    //This should be called before SQL transactions.  It will connect to the DB
    //and create the statement object.    
    private void prepSQL(){
        //This is a try-with-resource which closes the connection automatically after leaving
        //the try block.  I am not worried about this at the moment for testing purposes.
        /*
        try (Connection connection = DriverManager.getConnection(url, username, password)){
            System.out.println("Database connected!");
        } catch (SQLException ex){
            printSQLException(exception);
            System.out.println("Cannot connect to the database!");
        }
        */
                
        connection = dbConn.connect();
        
        //If the connection is successful we can create a statement object. 
        if(connection != null){
            try{
                stmt = connection.createStatement();
            } catch (SQLException exception){
                dbConn.printSQLException(exception);
                System.out.println("Cannot create a statement object");
            }
        }
    }
    
    //This should be called after SQL transactions or and exception has occurred.  It 
    //will disconnect from the DB and set variables to null
    private void finishSQL(){
        dbConn.disconnect();
        connection = null;
        
        try{
            if(rs != null){
                rs.close();
                rs = null;
            }
            if(stmt != null){
                stmt.close();
                stmt = null;
            }
        } catch (SQLException exception){
            dbConn.printSQLException(exception);
            System.out.println("Error closing connections!");
        }
    }
}
