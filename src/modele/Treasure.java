package modele;



public class Treasure extends Fixed { // Modele
    public Treasure() {
        setStr('$');
    }

    @Override
    public void process(Hunter h) {
        moveHunter(h);
        h.setTreasure_found(true);
    }
}
