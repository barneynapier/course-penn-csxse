package hw4;

public class Destroyer extends Ship {

    public Destroyer() {
        this.setLength(4);
    }

    @Override
    public String getShipType() {
        return "Cruiser";
    }
}
