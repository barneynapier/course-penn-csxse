package hw4;

public class Submarine extends Ship {

    public Submarine() {
        this.setLength(3);
    }

    @Override
    public String getShipType() {
        return "Submarine";
    }
}
