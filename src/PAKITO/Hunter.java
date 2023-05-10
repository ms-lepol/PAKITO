package PAKITO;

public class Hunter extends Mobile {
    static private int nb = 0; 

    public Hunter() {
        setStr((char)(65+nb));
        nb++;

        dir = getRandDir();
    }
}
