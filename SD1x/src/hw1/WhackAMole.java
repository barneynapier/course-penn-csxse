import java.util.Random;
import java.util.Scanner;
public class WhackAMole 
{
    int score;
    int molesLeft;
    int attemptsLeft;
    char[][] moleGrid;
    int gridDim;

    // Constructor function
    WhackAMole(int numAttempts, int gridDimension) 
    {
        gridDim = gridDimension;
        attemptsLeft = numAttempts;
        moleGrid = new char[gridDimension][gridDimension];
    }

    boolean place(int x, int y) 
    {
        if (moleGrid[x][y] != 'M') {
            moleGrid[x][y] = 'M';
            molesLeft++;
            return true;
        }
        else {
            return false;
        }
               
    }
    
    void whack(int x, int y) 
    {
        if (moleGrid[x][y] == 'M') {
            molesLeft--;
            score++;
            System.out.println("You got a mole!");
        }
        else{
            System.out.println("You missed, try again.");
        }
        moleGrid[x][y] = 'W';
        attemptsLeft--;

    }

    void printGridToUser() 
    {
        char[][] hiddenGrid = moleGrid.clone();

        // Replace M with *
        for (int i = 0; i < hiddenGrid.length; i++) 
        {
            for (int j = 0; j < hiddenGrid[i].length; j++) 
            {
                if (hiddenGrid[i][j] == 'M') 
                {
                    hiddenGrid[i][j] = '*';
                }
            }
        }
        for (int i = 0; i < gridDim; i++)
        {
            System.out.println(hiddenGrid[i]);
        }
    }

    void printGrid() 
    {
        System.out.println("Grid:");
        for (int i = 0; i < gridDim; i++)
        {
            System.out.println(moleGrid[i]);
        }
    }

    void buildGrid()
    {
        // Start with all *
        for (int i = 0; i < gridDim; i++) 
        {
            for (int j = 0; j < gridDim; j++) 
            {
                moleGrid[i][j] = '*';
            }
        }

        int molesPlaced = 0;

        // Randomly put in 10 moles
        while (molesPlaced < 10)
        {
            Random xrand = new Random();
            Random yrand = new Random();    
            int rx = xrand.nextInt(10);
            int ry = yrand.nextInt(10);

            this.place(rx, ry);
            molesPlaced++;
            
        }
    }

    int getCoordinate(String msg)
    {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println(msg);
        int val = Integer.parseInt(myObj.nextLine());  // Read user input
        return val;
    }

    public static void main(String[] args) {
        // Make 10 x 10 grid
        WhackAMole game = new WhackAMole(50, 10);
        game.buildGrid();
        boolean quit = false;
        while ((game.attemptsLeft > 0) && (game.molesLeft > 0) && (!quit))
        {
            System.out.println("Attempts remaining: " + game.attemptsLeft);
            System.out.println("Moles remaining: " + game.molesLeft);

            game.printGridToUser();

            int xGuess = game.getCoordinate("Input x:  ");
            int yGuess = game.getCoordinate("Input y:  ");
            
            if ((xGuess != -1) && (yGuess != -1))
            {
                game.whack(xGuess, yGuess);
            }
            else {
                quit = true;
                game.printGrid();

            }
        }
        System.out.println("Game Over.");
    }
}