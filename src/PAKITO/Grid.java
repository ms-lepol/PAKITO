package PAKITO;
import java.util.*;

public class Grid {
	Map<Position,LinkedList<Piece>> Grid;

	public Grid(){
		Grid = new HashMap<Position,LinkedList<Piece>>();
		initEmptyGrid();

		List<Piece> li = new ArrayList<>();
		for(int i = 0; i<10; i++){
			li.add(new Tool());
		}
		li.add(new Hunter());
		li.add(new Hunter());
		li.add(new Tool());
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

				LinkedList<Piece> li = new LinkedList<Piece>();
				if(row == 0 && col == 0){ 
					// Haut Gauche
					li.add(new Border('\u250C'));
					Grid.put((new Position(row, col)), li);
				}else if(row == 0 && col == Position.MAX_WIDTH - 1){ 
					// Haut Droite
					li.add(new Border('\u2510'));
					Grid.put((new Position(row, col)), li);
				}else if(row == Position.MAX_HEIGHT - 1 && col == 0){ 
					// Bas Gauche
					li.add(new Border('\u2514'));
					Grid.put((new Position(row, col)), li);
				}else if(row == Position.MAX_HEIGHT - 1 && col == Position.MAX_WIDTH - 1){ 
					// Bas Droite
					li.add(new Border('\u2518'));
					Grid.put((new Position(row, col)), li);
				}else if(row == 0 || row == Position.MAX_HEIGHT - 1){
					// Bas ou Haut
					li.add(new Border('\u2500'));
					Grid.put((new Position(row, col)), li);
				}else if(col == 0 || col == Position.MAX_WIDTH - 1){
					// Gauche ou Droite
					li.add(new Border('\u2502'));
					Grid.put((new Position(row, col)), li);
				}
				
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
			Piece currP = getPiece(rPos);
			if(currP == null){
				// Case libre
				LinkedList<Piece> li = new LinkedList<Piece>();
				li.addLast(p);
				Grid.put(rPos, li);
				isPlaced = true;
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

	/*
	 * Position quelconque
	*/
	public Piece getPiece(Position pos){
		for(Position key : Grid.keySet()){
			if(key.equals(pos)){
				return this.Grid.get(key).getLast();
			}
		}
		return null;
	}

	/*
	 * Piece connue !!! (car on utilise son adresse mémoire)
	*/
	public Position getPos(Piece p){
		for (Map.Entry<Position, LinkedList<Piece>> entry : Grid.entrySet()) {
			if (!(entry.getValue().isEmpty()) && p.equals(entry.getValue().getLast())){
				return entry.getKey();
			}
		}
		return null;
	}

	/*
	 * Ajouter une piece a une position deja instanciée
	 */
	public void addPiece(Piece piece, Position pos){
		for(Position key : Grid.keySet()){
			if(key.equals(pos)){
				Grid.get(key).addLast(piece);
			}
		}
	}

	/*
	 * Enlever La derniere piece de la liste
	 * a la position indiquée et déjà instanciée
	 */
	public void removeLast(Position pos){
		for(Position key : Grid.keySet()){
			if(key.equals(pos)){
				Grid.get(key).removeLast();
			}
		}
	}
	
	/*
	 * Fonction d'update de la grille qui va
	 * bouger tous les personnages Mobiles
	 */
	public void update(){
		List<Mobile> actionList = new ArrayList<Mobile>();
		List<Position> removeList = new ArrayList<Position>();

		for (Position key : Grid.keySet()) {
			Piece p = getPiece(key);
			if(p == null || !(p instanceof Mobile)){
				continue;
			}
			actionList.add((Mobile)p);
		}
		for(Mobile piece : actionList){
			piece.move(this);
		}

		// Parcours enlever toutes les positions
		// contenant une liste vide
		for (Position key : Grid.keySet()) {
			if(Grid.get(key).isEmpty()){
				removeList.add(key);
			}
		}
		for(Position pos : removeList){
			Grid.remove(pos);
		}


	}

	/*
	 * Retourne le chasseur ayant trouvé le trésor
	 * si il existe, retourne null sinon
	 */
	public Hunter foundTreasure(){
		List<Hunter> hunterList = new ArrayList<Hunter>();

		for (Position key : Grid.keySet()) {
			Piece p = getPiece(key);
			if(p == null || !(p instanceof Hunter)){
				continue;
			}
			hunterList.add((Hunter)p);
		}
		for(Hunter h : hunterList){
			if(h.getTreasure_found()){
				return h;
			}
		}

		return null;
	}

	@Override
	public String toString() {
		String str = "";
		for (int row = 0; row < Position.MAX_HEIGHT; row++){
			for (int col = 0; col < Position.MAX_WIDTH; col++){
				Position pos = new Position(row, col);
				Piece piece = getPiece(pos);
				if(piece != null){
					str += piece.toString();
				}else{
					str += "\u00B7";
				}
				
				if(pos.getCol() == Position.MAX_WIDTH - 1){
					str += "\n";
				}
				
			}
		}
		return str;
	}
}
