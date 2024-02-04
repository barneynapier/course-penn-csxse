package hw4;

public abstract class Ship {

    private int bowRow; // – the row (0 to 19) which contains the bow (front) of the ship.
    private int bowColumn; // - the column which contains the bow (front) of the ship.
    private int length; // – the number of squares occupied by the ship. An ”empty sea” location has length 1.
    private boolean horizontal; // – true if the ship occupies a single row, false otherwise. Ships will either be placed vertically or horizontally in the ocean.
    private boolean[] hit; // - this is a boolean array of size 8 that record hits. Only battleships use all the locations. The others will use fewer.

    public int getBowRow() {
        return bowRow;
    }

    public void setBowRow(int bowRow) {
        this.bowRow = bowRow;
    }

    public int getBowColumn() {
        return bowColumn;
    }

    public void setBowColumn(int bowColumn) {
        this.bowColumn = bowColumn;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public boolean[] getHit() {
        return hit;
    }

    public void setHits(boolean[] hit) {
        this.hit = hit;
    }

    public abstract String getShipType();

    public boolean okToPlaceShipAt(int row, int col, boolean horizontal, Ocean ocean) {

        // Loop through each square that would be occupied
        // Check square is
        // (a) Valid - All parts of the ship are in the grid
        // (b) Empty - There is no overlap with other ships

        // Variables to multiply by to go to next ship square
        int incRow = 0;
        int incCol = 0;
        if (horizontal) {incRow = 1;}
        else {incCol = 1;}

        // Check validity
        // Valid if the end square is within the box
        boolean isValid;
        if (bowRow + (length * incRow) > 19 || bowColumn + (length * incCol) > 19) {
            isValid = false;
        }
        else { isValid=true; }

        // Check all squares are empty
        boolean isEmpty = true;
        for (int i = 0; i < length; i++) {
            int rowLoc = bowRow + (incRow * i);
            int colLoc = bowColumn + (incCol * i);

            // Check square emptiness
            if (ocean.isOccupied(rowLoc, colLoc)) {
                isEmpty = false;
            }
        }

        if (isValid && isEmpty) {
            return true;
        }
        else {
            return false;
        }

    }

    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {

        // This involves giving values to the bowRow, bowColumn, and horizontal instance variables in the ship
        bowRow = row;
        bowColumn = column;

        // and it also involves putting a reference to the ship in each of
        // 1 or more locations (up to 8) in the ships array in the Ocean object
        Ship[][] oldShips = ocean.getShipArray();
        oldShips[row][column] = this;

    }

    public boolean shootAt(int row, int column) {

        
        // If a part of the ship occupies the given row and column

        boolean isHit = false;

        if (horizontal) {
            if ((row == bowRow) && bowColumn <= column && column <= bowColumn + length) {
                hit[column - bowColumn] = true;
            }
        else {
            if ((column == bowColumn) && bowRow <= row && row <= bowRow + length) {
                hit[row - bowRow] = true;
            }
        }
        }

        // Return true if the ship is hit but not sunk
        // and the ship hasn’t been sunk
        // mark that part of the ship as ”hit”
        // (in the hit array, 0 indicates the bow)
        // and return true, otherwise return false.
        if (isHit && !this.isSunk()) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isSunk() {
        // Return true if sunk else false
        // Get number of hits
        int nHits = 0;
        for (boolean h : hit) {
            if (h) {nHits++;}
        }
        // Ship is sunk if number of hits == length of ship
        if (nHits == length) {return true;}
        else {return false;}
    }

    @Override
    public String toString() {
        if (isSunk()) {
            return "x";
        }
        else {
            return "S";
        }
    }
}
