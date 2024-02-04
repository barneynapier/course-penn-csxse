package hw4;

public class Cruiser extends Ship {

    public Cruiser() {
        this.setLength(6);
    }

    @Override
    public String getShipType() {
        return "Cruiser";
    }
}
