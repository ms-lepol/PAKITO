package PAKITO;

abstract class Fixed extends Piece implements Questionnable {

    /*
     * Pour la piece fixe actuelle, avec l'interface Questionnable
     * cette fonction fait bouger le chasseur sur la case de 
     * la piece fixe et supprime le chasseur de sa case précédente
     * 
     * Ce bout de code est réutilisé par plusieurs pièces fixes 
     */
    public void moveHunter(Hunter h){
        h.getGrid().removeLast(h.getGrid().getPos(h));

        Position pos = h.getGrid().getPos(this);
        h.getGrid().addPiece(h, pos);
    }
}
