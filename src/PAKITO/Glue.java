package PAKITO;

public class Glue extends Fixed {
    public Glue() {
        setStr('G');
    }

    @Override
    public void process(Hunter h) {
        h.setWait_time(2);
    }
}
