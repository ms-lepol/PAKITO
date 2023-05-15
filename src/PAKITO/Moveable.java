package PAKITO;

public interface Moveable {

    /*
     * Fonction définissant la façon de 
     * bouger d'un personnage mobile 
     */
    void move(Controleur c);
    Position getNextPos(Position curr,int dir);
}
