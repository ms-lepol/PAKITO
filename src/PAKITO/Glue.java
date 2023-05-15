package PAKITO;

public class Glue extends Fixed { // Modele
    public Glue() {
        setStr('G');
    }

    @Override
    public void process(Hunter h) {
        moveHunter(h);
        h.setWait_time(1);
    }
}
