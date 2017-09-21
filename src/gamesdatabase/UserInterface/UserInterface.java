/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamesdatabase.UserInterface;

import gamesdatabase.SQL.Games;
import java.util.Scanner;

/**
 *
 * @author Wesley
 */
public class UserInterface {
    private Scanner reader;
    private Games games;
    
    public UserInterface(Games games){
        this.reader = new Scanner(System.in);
        this.games = games;
    }
    
    public void run(){
        String command = "";
        
        while(true){
            printMenu();
            command = this.reader.nextLine();
            
            if(command.equals("1")){
                inquireRoutine();
            } else if(command.equals("2")){
                addRoutine();
            } else if(command.equals("3")){
                updateRoutine();
            } else if(command.equals("4")){
                deleteRoutine();
            } else if(command.equals("5")){
                break;
            }
            
        }
    }
    
    private void printMenu(){
        System.out.println("");
        System.out.println("Games Database Main Menu ");
        System.out.println("  1. Inquire");
        System.out.println("  2. Add a game");
        System.out.println("  3. Update a game");
        System.out.println("  4. Delete a game");
        System.out.println("  5. Quit");
        System.out.println("");
        System.out.print("Select an Option: ");
    }
    
    private void inquireRoutine(){
        String command = "";
        String system = "";
        String title = "";
        String status = "";
        String storageMethod = "";
        
        while(true){
            printInquireMenu();
            command = this.reader.nextLine();
            
            if(command.equals("1")){
                this.games.inquireAllGames();
            } else if (command.equals("2")){
                System.out.print("System: ");
                system = reader.nextLine();
                System.out.print("Title: ");
                title = reader.nextLine();
                this.games.inquireSpecificGame(system, title);                
            } else if (command.equals("3")){
                System.out.print("System: ");
                system = reader.nextLine();
                this.games.inquireBySystem(system);
            } else if (command.equals("4")){
                System.out.print("Title: ");
                title = reader.nextLine();
                this.games.inquireByTitle(title);
            } else if (command.equals("5")){
                System.out.print("Status: ");
                status = reader.nextLine();
                this.games.inquireByStatus(status);
            } else if (command.equals("6")){
                System.out.print("Digital or Physical: ");
                storageMethod = reader.nextLine();
                this.games.inquireByStorageMethond(storageMethod);
            } else if (command.equals("7")){
                break;
            }
        }
  
    }
    
    private void printInquireMenu(){
        System.out.println("");
        System.out.println("Inquiry menu");
        System.out.println("  1. Inquire all games");
        System.out.println("  2. Inquire specific game");
        System.out.println("  3. Inquire by system");
        System.out.println("  4. Inquire by title");
        System.out.println("  5. Inquire by status");
        System.out.println("  6. Inquire by storage method");
        System.out.println("  7. Back to main menu");
        System.out.println("");
        System.out.print("Select an Option: "); 
    }
    
    private void addRoutine(){
        String system = "";
        String title = "";
        String status = "";
        String storageMethod = "";
        
        System.out.println("");
        System.out.println("Enter game information to add.");
        System.out.print("System: ");
        system = reader.nextLine();
        
        System.out.print("Title: ");
        title = reader.nextLine();
        
        System.out.print("Status: ");
        status = reader.nextLine();
        
        System.out.print("Storage Method(P or D): ");
        storageMethod = reader.nextLine();
        
        games.add(system, title, status, storageMethod);
    }
    
    private void updateRoutine(){
        String system = "";
        String title = "";
        String status = "";
        String storageMethod = "";
                
        System.out.println("");
        System.out.println("Which game to update?");
        System.out.print("System: ");
        system = reader.nextLine();
        
        System.out.print("Title: ");
        title = reader.nextLine();
        
        System.out.println("");
        System.out.println("Enter the updated fields.");
        System.out.print("Status: ");
        status = reader.nextLine();
        
        System.out.print("Storage Method(P or D): ");
        storageMethod = reader.nextLine();
        
        games.update(system, title, status, storageMethod);
    }
    
    private void deleteRoutine(){
        String system = "";
        String title = "";
        
        System.out.println("");
        System.out.println("Which game to delete?");
        System.out.print("System: ");
        system = reader.nextLine();
        
        System.out.print("Title: ");
        title = reader.nextLine();
        
        games.delete(system, title);
    }
}
