package PAKITO;
import java.util.*;

import PAKITO.Piece_Fixed.Border;

public class Grid {
	Map<Position,List<Piece>> Grid;

	public Grid(){
		Grid = new LinkedHashMap<Position,List<Piece>>();
		initGrid();
	}

	public void initGrid(){
		for(int row = 0; row < Position.MAX_HEIGHT; row++){
			for(int col = 0; col < Position.MAX_WIDTH; col++){
				List<Piece> li = new ArrayList<Piece>();
				if(row == 0 && col == 0){ 
					// Haut Gauche
					li.add(new Border('\u250C'));
				}else if(row == 0 && col == Position.MAX_WIDTH - 1){ 
					// Haut Droite
					li.add(new Border('\u2510'));
				}else if(row == Position.MAX_HEIGHT - 1 && col == 0){ 
					// Bas Gauche
					li.add(new Border('\u2514'));
				}else if(row == Position.MAX_HEIGHT - 1 && col == Position.MAX_WIDTH - 1){ 
					// Bas Droite
					li.add(new Border('\u2518'));
				}else if(row == 0 || row == Position.MAX_HEIGHT - 1){
					// Bas ou Haut
					li.add(new Border('\u2500'));
				}else if(col == 0 || col == Position.MAX_WIDTH - 1){
					// Gauche ou Droite
					li.add(new Border('\u2502'));
				}else{
					// Autre
					li.add(new Free());
				}
				Grid.put(new Position(row, col), li);
			}
		} 
	}

	@Override
	public String toString() {
		String str = "";
		for (Position key : Grid.keySet()){
			str += Grid.get(key).get(0).toString();
			if(key.getCol() == Position.MAX_WIDTH - 1){
				str += "\n";
			}
		}
		return str;
	}
}
