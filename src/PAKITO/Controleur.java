package PAKITO;

import java.util.*;

public class Controleur {
    private Game game;

    public Controleur(){
        game = new Game();
    }

    public Game getGame(){
        return game;
    }

    public void gridUpdate(){
        List<Mobile> actionList = new ArrayList<Mobile>();
		List<Position> removeList = new ArrayList<Position>();

        // Liste des pieces mobiles a bouger
		for (Position key : grid.Grid.keySet()) {
			Piece p = grid.getPiece(key);
			if(p == null || !(p instanceof Mobile)){
				continue;
			}
			actionList.add((Mobile)p);
		}
		for(Mobile piece : actionList){
			grid.update(piece);
		}

		// Parcours enlever toutes les positions
		// contenant une liste vide
		for (Position key : grid.Grid.keySet()) {
			if(grid.Grid.get(key).isEmpty()){
				removeList.add(key);
			}
		}
		for(Position pos : removeList){
			grid.Grid.remove(pos);
		}
    }

}
