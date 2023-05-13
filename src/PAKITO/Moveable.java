package PAKITO;

public interface Moveable {

    /*
     * Fonction définissant la façon de 
     * bouger d'un personnage mobile 
     */
    void move(Grid g);
    Position getNextPos(Position curr,int dir);
}
