package hw4;

public class BattleShip extends Ship {

    public BattleShip() {
        this.setLength(8);
    }

    @Override
    public String getShipType() {
        return "BattleShip";
    }
}
