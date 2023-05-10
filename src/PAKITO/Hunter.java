package PAKITO;

public class Hunter extends Mobile {
    static private int nb = 0;
    private int wait_time = 0;
    private boolean treasure_found = false;

    public Hunter() {
        setStr((char)(65+nb));
        nb++;

        dir = getRandDir();
    }

    public void setTreasure_found(boolean found) {
        this.treasure_found = found;
    }

    public void setWait_time(int n){
        this.wait_time = n;
    }
}
