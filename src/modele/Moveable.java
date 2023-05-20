package modele;


public interface Moveable { // Modele
    /**
     * Fonction définissant la façon de 
     * bouger d'un personnage mobile 
     */
    boolean move();

    /**
     * Sert a connaitre la prochaine position
     * en fonction de la position et de la direction 
     * actuelle
     * 
     * @param curr  Position actuel
     * @param dir   direction actuelle
     * 
     * @return Position si on se deplace
     */
    Position getNextPos(Position curr,int dir);
}
