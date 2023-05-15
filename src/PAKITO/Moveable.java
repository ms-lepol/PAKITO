package PAKITO;

public interface Moveable { // Modele

    /*
     * Fonction définissant la façon de 
     * bouger d'un personnage mobile 
     */
    boolean move();
    Position getNextPos(Position curr,int dir);
}
