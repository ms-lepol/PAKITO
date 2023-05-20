package modele;

public class Glue extends Fixed { // Modele
    public Glue() {
        setStr('~');
    }

    @Override
    public void process(Hunter h) {
        moveHunter(h);
        h.setWait_time(1);
    }
}
