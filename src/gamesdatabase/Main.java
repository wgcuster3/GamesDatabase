/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamesdatabase;

import gamesdatabase.SQL.Games;
import gamesdatabase.SQL.SQLFunctions;
import gamesdatabase.UserInterface.UserInterface;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Wesley
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Test connecting to database
        //Need to look into prepared statements once this is running.
        
        //Variables to connect to database read in from file.
        String url = null;
        String username = null;
        String password = null;
        
        String filename = "C:\\Users\\Wesley\\Documents\\mysql-connectors\\mysql-parameters.txt";
        
        try{
            FileReader filereader = new FileReader(filename);
            
            // Wrap FileReader in bufferedReader for efficiency.
            BufferedReader bufferedReader = new BufferedReader(filereader);
            
            //txt file contains 1 parameter per line
            url = bufferedReader.readLine();
            username = bufferedReader.readLine();
            password = bufferedReader.readLine();
            
            bufferedReader.close();
            
        } catch (FileNotFoundException exception){
            System.out.println("Unable to open file: " + filename);
        } catch (IOException exception){
            System.out.println("Error reading file: " + filename);
        }
        
        SQLFunctions dbConn = new SQLFunctions(url, username, password);
        Games games = new Games(dbConn);
        
        UserInterface ui = new UserInterface(games); 
        ui.run();
    }
}
