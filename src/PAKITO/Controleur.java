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

    public int getRound(){
        return game.getRound();
    }
    
    public boolean updatePiece(List<Mobile> li, int n){
        return li.get(n).move();
    }
    
    public void clearEmpty(){
        List<Position> removeList = new ArrayList<Position>();
		// Parcours enlever toutes les positions
		// contenant une liste vide
		for (Position key : game.getGrid().getGrid().keySet()) {
			if(game.getGrid().getGrid().get(key).isEmpty()){
				removeList.add(key);
			}
		}
		for(Position pos : removeList){
			game.getGrid().getGrid().remove(pos);
		}
    }

    public void printGrid(){
        game.printGrid(getRound());
    }

    public void gridUpdate(){
        LinkedList<Mobile> actionList = game.getGrid().getLiHunter();

        for(int i = 0; i<actionList.size(); i++){
            boolean b = updatePiece(actionList, i);
            clearEmpty();
            if(b){
                printGrid();
            }
            if(((Hunter) actionList.get(i)).getTreasure_found()){
                return;
            }
        }

    }

}
