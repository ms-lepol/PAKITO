package PAKITO;

abstract class Piece implements Questionnable{
    char str;

    public void setStr(char str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return ""+str;
    }
}
