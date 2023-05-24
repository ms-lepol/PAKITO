package modele;


public abstract class Piece implements Questionnable{ // Modele
    char str;

    public void setStr(char str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return ""+str;
    }
}
