package modele;


abstract class Fixed extends Piece { // Modele

    /**
     * Pour la piece fixe actuelle, avec l'interface Questionnable
     * cette fonction fait bouger le chasseur sur la case de 
     * la piece fixe et supprime le chasseur de sa case précédente
     * 
     * Ce bout de code est réutilisé par plusieurs pièces fixes
     * dont la fonction ne réside pas tant dans le mouvement du 
     * personage mais le changement d'etat du personnage (exemple
     * l'Outil, la RoadMap ou la Glue) 
     */
    public void moveHunter(Hunter h){
        h.getCtrl().getGame().getGrid().removeLast(h.getCtrl().getGame().getGrid().getPos(h));

        Position pos = h.getCtrl().getGame().getGrid().getPos(this);
        System.out.println("Piece = " + pos.toString());
        h.getCtrl().getGame().getGrid().addPiece(h, pos);
    }
}
