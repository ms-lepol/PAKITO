package PAKITO;
import java.util.*;

import javax.swing.text.Position;

public class Grid {
	Map<Position,List<Piece>> Grid;

	public Grid(){
		Grid = new LinkedHashMap<Position,List<Piece>>();
		initEmptyGrid();

		List<Piece> li = new LinkedList<>();
		for(int i = 0; i<5; i++){
			li.add(new Wall());
		}
		li.add(new Hunter());
		li.add(new Hunter());
		li.add(new Tool());
		li.add(new Stone());
		li.add(new Treasure());
		li.add(new Glue());

		initPieces(li);
	}

	/*
    * Initialise une grille vide avec des bordures 
    * 
    */
	public void initEmptyGrid(){
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

	/*
    * Place une piece au hasard dans la grille
	*
	* @param Piece p  La piece a placer
    * 
    */
	public void initPiece(Piece p){
		boolean isPlaced = false;
		while(!isPlaced){
			Position rPos = Position.randPos();
			for (Position key : Grid.keySet()){
				if(key.equals(rPos)){
					if(Grid.get(key).get(0) instanceof Free){
						// La case est libre
						Grid.get(key).clear();
						Grid.get(key).addLast(p);
						isPlaced = true;
					}
				}
			}
		}
	}

	/*
    * Place plusieurs pieces au hasard dans la grille
	*
	* @param Piece p  La piece a placer
    * 
    */
	public void initPieces(List<Piece> li){
		for(Piece p : li){
			initPiece(p);
		}
	}

	public void movePiece(Position pos){
		Piece currPiece = getPiece(pos);
		if (currPiece!=null){
			
		}
	}

	public Piece getPiece(Position pos){
		if (this.Grid.containsKey(pos)){
			return this.Grid.get(pos).getLast();
		}
	}
	public void update(){

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
