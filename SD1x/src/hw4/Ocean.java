package hw4;

import java.util.Arrays;
import java.util.Random;

public class Ocean {

    // Ocean dictionary:
        // S = hits
        // x = sunken ship
        // - = misses
        // . = location you have not fired upon
        // * = Unseen ship
        // / = Nothing

    private Ship[][] ships = new Ship[20][20];
    private int shotsFired;
    private int hitCount;
    private int shipsSunk;


    public Ocean() {
        // First, make the whole grid empty sea
        for (int i = 0; i < ships[0].length; i++) {
            for (int j = 0; j < ships[0].length; j++) {
                ships[i][j] = new EmptySea();

            }
        }
        shotsFired = 0;
        hitCount = 0;
        shipsSunk = 0;
    }

    public void setShips(Ship[][] newShips) {
        ships = newShips;
    }

    public void placeAllShipsRandomly() {
        Random random = new Random();

        // Create all the ships and put into an array
        Ship[] shipsToPlace = {
                new BattleShip(),
                new BattleCruiser(),
                new Cruiser(),
                new Cruiser(),
                new LightCruiser(),
                new LightCruiser(),
                new Destroyer(),
                new Destroyer(),
                new Destroyer(),
                new Submarine(),
                new Submarine(),
                new Submarine(),
                new Submarine(),
        };

        // Loop through the array and add the ships randomly to the sea
        for (int i = 0; i < shipsToPlace.length; i++) {
            // While loop
            // Randomly choose placement of ship
            // Validate its OK to place ship here
            // Place ship here if it is, otherwise try again
            boolean isOk = false;

            while (!isOk) {
                Ship shipToPlace = shipsToPlace[i];
                int randRow = random.nextInt(ships[0].length);
                int randCol = random.nextInt(ships[0].length);
                boolean randHorizontal = random.nextBoolean();
                if (shipToPlace.okToPlaceShipAt(randRow, randCol, randHorizontal, this)) {
                    shipToPlace.placeShipAt(randRow, randCol, randHorizontal, this);
                    isOk = true;
                }
            }
        }
    }

    public boolean isOccupied(int row, int column) {

        if (ships[row][column].getShipType().equals("empty")) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean shootAt(int row, int column) {

        // Update shots fired
        shotsFired += 1;
        // Returns true if the given location contains a ”real” ship,
        // still afloat, (not an EmptySea), false if it does not
        if (ships[row][column].shootAt(row, column)) {
            // Update number of hits
            hitCount += 1;
            // Return true if there is a hit
            return true;
        }
        else {
            return false;
        }
    }

    public int getShotsFired() {
        return this.shotsFired;
    }

    public int getHitCount() {
        return this.hitCount;
    }

    public int getShipsSunk() {
        return this.shipsSunk;
    }

    public boolean isGameOver() {
        if (shotsFired == 50 || shipsSunk == 13) {
            return true;
        }
        else {
            return false;
        }
    }

    public Ship[][] getShipArray() {
        return ships;
    }

    public void print() {
        // Prints the ocean with row and col numbers on the edges
        // S = hits
        // x = sunken ship
        // - = misses
        // . = location you have not fired upon
        String[] indices = {
            "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", 
            "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"
        };
        // Print indices, with tab at start to line up properly
        System.out.println("   " + String.join(" ", Arrays.asList(indices)));

        // Print each line, including the row index at the start
        for (int i = 0; i < ships[0].length; i++) {
            String strLine = String.valueOf(ships[i]);
            // Add two spaces between values to line up correctly with index
            strLine = strLine.replace("", "  ").trim();
            System.out.println(indices[i] + " " + strLine);
        }
    }

}
