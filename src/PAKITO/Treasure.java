package PAKITO;

public class Treasure extends Fixed {
    public Treasure() {
        setStr('X');
    }

    @Override
    public void process(Hunter h) {
        moveHunter(h);
        h.setTreasure_found(true);
    }
}
