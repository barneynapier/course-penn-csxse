package hw4;

import java.util.Scanner;

public class BattleshipGame {

    public static void main(String[] args) {
        // All input/output is done here (although some of it is done by calling a print() method in the Ocean class.)
        // All computation will be done in the Ocean class and the various Ship classes.

        // In this class you will set up the game
        BattleshipGame game = new BattleshipGame();
        Ocean ocean = new Ocean();
        ocean.placeAllShipsRandomly();
        
        // Accept ”shots” from the user
        // Note that you want to accept 5 shots from the user
        // For example you can provide an instruction to the user as follows
        // The input format should look be: 1, 1; 0, 3; 7, 3; 9, 11; 12, 17
        Scanner scnr = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Input your shots in the form '1, 1; 0, 3; 7, 3; 9, 11; 12, 17'");
    
        String input = scnr.nextLine();  // Read user input
        System.out.println("Guess shots: " + input);  // Output user input
        scnr.close();

        // Split the 5 shot guesses out
        String[] shots = input.split("; ", 0);
        for (int i = 0; i < shots.length; i++) {
            // Take the shot
            int shootRow = Integer.parseInt(shots[i].split(", ")[0]);
            int shootCol = Integer.parseInt(shots[i].split(", ")[1]);
            
            ocean.shootAt(shootRow, shootCol);
        }

        // Print the results / show the ocean
        ocean.print();
        
        // print final scores
        
        
        // and ask the user if he/she wants to play again
        System.out.println("Do you want to play again?");


    }
}
