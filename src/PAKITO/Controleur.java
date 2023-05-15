package PAKITO;

import java.util.*;

public class Controleur {
    private Game game;

    public Controleur(){
        game = new Game(this);
    }

    public Game getGame(){
        return game;
    }

    public void startGame(){
        game.startGame();
    }

    /*
	 * Retourne le chasseur ayant trouvé le trésor
	 * si il existe, retourne null sinon
	 * 
	 * @return Hunter	le chasseur ayant trouve le tresor
	 */
    public Hunter foundTreasure(){
        return game.getGrid().foundTreasure();
    }

    /*
	 * Fonction d'update de la grille qui va
	 * bouger tous les personnages Mobiles
	 */
    public void gridUpdate(){
        List<Mobile> actionList = new ArrayList<Mobile>();
		List<Position> removeList = new ArrayList<Position>();

        // Liste des pieces mobiles a bouger
		for (Position key : game.getGrid().Grid.keySet()) {
			Piece p = game.getGrid().getPiece(key);
			if(p == null || !(p instanceof Mobile)){
				continue;
			}
			actionList.add((Mobile)p);
		}
		for(Mobile piece : actionList){
			piece.move();
		}

		// Parcours enlever toutes les positions
		// contenant une liste vide
		for (Position key : game.getGrid().Grid.keySet()) {
			if(game.getGrid().Grid.get(key).isEmpty()){
				removeList.add(key);
			}
		}
		for(Position pos : removeList){
			game.getGrid().Grid.remove(pos);
		}
    }

}
