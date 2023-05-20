package modele;

import controleur.*;
import java.util.*;

public class Grid { // Modele

	/* Attributs */
	private Map<Position,LinkedList<Piece>> Grid;

	private Controleur c;
	
	static private int NB_WALLS_MIN = 2;
	static private int NB_WALLS_MAX = 4;
	static private int LEN_WALL_MIN =  2;
	static private int LEN_WALL_MAX =  6;
	static private int NB_ROADMAP = 3;
	static private int NB_TOOL = 3;
	static private int NB_GLUE = 3;
	private int NB_HUNTER = 3;
	private LinkedList<Mobile> liHunter;
	
	/* Constructeur */
	public Grid(Controleur c){
		this.c = c;
		calculateNBPiece(Position.getArea());
		Grid = new HashMap<Position,LinkedList<Piece>>();
		initEmptyGrid();
		fillGrid();
	}

	/* Getter & Setter */
	public Map<Position, LinkedList<Piece>> getGrid() {
		return Grid;
	}

	public LinkedList<Mobile> getLiHunter() {
		return liHunter;
	}

	public void setNB_HUNTER(int nB_HUNTER) {
		NB_HUNTER = nB_HUNTER;
	}

	/* Methodes */
	public void calculateNBPiece(int area) {
		NB_WALLS_MIN = area/100;
		NB_WALLS_MAX = 2*NB_WALLS_MIN;
		LEN_WALL_MAX = (Position.getMAX_HEIGHT()<Position.getMAX_WIDTH()) ? (int)(Position.getMAX_HEIGHT()/2):(int)(Position.getMAX_WIDTH()/2);
		NB_ROADMAP = area/50;
		NB_TOOL = area/50;
		NB_GLUE = area/40;
		
	}
	public void initEmptyGrid(){

		for(int row = 0; row < Position.getMAX_HEIGHT(); row++){
			for(int col = 0; col < Position.getMAX_WIDTH(); col++){

				LinkedList<Piece> li = new LinkedList<Piece>();
				if(row == 0 && col == 0){ 
					// Haut Gauche
					li.add(new Border('\u250C'));
					Grid.put((new Position(row, col)), li);
				}else if(row == 0 && col == Position.getMAX_WIDTH() - 1){ 
					// Haut Droite
					li.add(new Border('\u2510'));
					Grid.put((new Position(row, col)), li);
				}else if(row == Position.getMAX_HEIGHT() - 1 && col == 0){ 
					// Bas Gauche
					li.add(new Border('\u2514'));
					Grid.put((new Position(row, col)), li);
				}else if(row == Position.getMAX_HEIGHT() - 1 && col == Position.getMAX_WIDTH() - 1){ 
					// Bas Droite
					li.add(new Border('\u2518'));
					Grid.put((new Position(row, col)), li);
				}else if(row == 0 || row == Position.getMAX_HEIGHT() - 1){
					// Bas ou Haut
					li.add(new Border('\u2500'));
					Grid.put((new Position(row, col)), li);
				}else if(col == 0 || col == Position.getMAX_WIDTH() - 1){
					// Gauche ou Droite
					li.add(new Border('\u2502'));
					Grid.put((new Position(row, col)), li);
				}
				
			}
		} 
	}

	public void initPiece(Piece p){
		boolean isPlaced = false;
		while(!isPlaced){
			Position rPos = Position.randPos();
			if(isEmpty(rPos)){
				// Case libre
				LinkedList<Piece> li = new LinkedList<Piece>();
				li.addLast(p);
				Grid.put(rPos, li);
				isPlaced = true;
			}
		}
	}

	public void initPieces(List<Piece> li){
		for(Piece p : li){
			initPiece(p);
		}
	}

	public void initHunters(){
		LinkedList<Mobile> li = new LinkedList<>();

		for(int i = 0; i<NB_HUNTER; i++){
			Hunter x = new Hunter(c);
			li.add(x);
			initPiece(x);
		}

		liHunter = li;
	}

	public void fillGrid(){
		List<Piece> li = new ArrayList<>();
		initWalls();
		initHunters();
		for (int i=0;i<NB_TOOL;i++) {
			li.add(new Tool());
		}
		for (int i=0;i<NB_GLUE;i++) {
			li.add(new Glue());
		}
		li.add(new Treasure());
		initRoadMap();
		initPieces(li);
	}
	
	public void initWall() {
		boolean canBePlaced = true;
		
		boolean isPlaced = false;
		while(!isPlaced) {
			int lenWall = (int)(Math.random()*(LEN_WALL_MAX-LEN_WALL_MIN)+1+LEN_WALL_MIN);
			boolean isVertical = Math.random() < 0.5;
			canBePlaced = true;
			Position initPos = Position.randPos(2,Position.getMAX_HEIGHT()-3,2,Position.getMAX_WIDTH()-3);
			if(isStoneValid(initPos)) {
				for (int i=1;i<lenWall;i++) {
					if (isVertical) {
						if (!isStoneValid(new Position(initPos.getRow(),initPos.getCol()+i))) {
							canBePlaced = false;
							break;
						}
					} else {
						if (!isStoneValid(new Position(initPos.getRow()+i,initPos.getCol()))) {
							canBePlaced = false;
							break;
						}
					}
				}
				if (canBePlaced) {
					for (int i=0;i<lenWall;i++) {
						if (isVertical) {
							LinkedList<Piece> li = new LinkedList<Piece>();
							li.addLast(new Stone());
							Grid.put(new Position(initPos.getRow(),initPos.getCol()+i), li);
							isPlaced = true;
						} else {
							LinkedList<Piece> li = new LinkedList<Piece>();
							li.addLast(new Stone());
							Grid.put(new Position(initPos.getRow()+i,initPos.getCol()), li);
							isPlaced = true;
						}
					}
					isPlaced=true;
				}
			}
		}
	}
	
	public void initWalls(){
		int nbWalls = (int)(Math.random()*(NB_WALLS_MAX-NB_WALLS_MIN)+1+NB_WALLS_MIN);
		for (int i = 0;i<nbWalls;i++) {
			initWall();
		}
	}
	
	public boolean isStoneValid(Position p) {
		if (!(p.getCol()<Position.getMAX_WIDTH()-2&&p.getCol()>1&&
			  p.getRow()<Position.getMAX_HEIGHT()-2&&p.getRow()>1)) {
			return false;
		}
		
		if (!isEmpty(p)) {
			return false;
		}
		if (!isEmpty(new Position(p.getRow()+1,p.getCol()))) {
			return false;
		}
		if (!isEmpty(new Position(p.getRow()-1,p.getCol()))) {
			return false;
		}
		if (!isEmpty(new Position(p.getRow(),p.getCol()+1))) {
			return false;
		}
		if (!isEmpty(new Position(p.getRow(),p.getCol()-1))) {
			return false;
		}
		return true;
	}
	
	public void initRoadMap() {
		for (int i=0;i<NB_ROADMAP;i++) {
			//Get the treasure position
			Position treasurePos = getTreasurePosition();
			boolean isPlaced = false;
			while(!isPlaced){
				Position rPos = Position.randPos();
				if(isEmpty(rPos) && treasurePos.directionTo(rPos)!=0){
					// Case libre
					LinkedList<Piece> li = new LinkedList<Piece>();
					li.addLast(new RoadMap());
					Grid.put(rPos, li);
					isPlaced = true;
				}
			}
		}
		
	}
	
	public Position getTreasurePosition() {
		Position treasurePos = new Position();
		for (Map.Entry<Position, LinkedList<Piece>> entry : Grid.entrySet()) {
			if (!(entry.getValue().isEmpty()) && entry.getValue().getFirst() instanceof Treasure){
				treasurePos = entry.getKey();
			}
		}
		return treasurePos;
	}

	/** Retourne la piece associee a la position donnee
	 * 
	 * @param Position pos 
	 * 
	 * @return Piece  
	 */
	public Piece getPiece(Position pos){
		for(Position key : Grid.keySet()){
			if(key.equals(pos)){
				return this.Grid.get(key).getLast();
			}
		}
		return null;
	}

	/** Donne la position associee a la piece donnee
	 * Attention la piece est connue car on utilise son adresse mémoire
	 * 
	 * @param Piece p
	 * 
	 * @return Position 
	 */
	public Position getPos(Piece p){
		for (Map.Entry<Position, LinkedList<Piece>> entry : Grid.entrySet()) {
			if (!(entry.getValue().isEmpty()) && p.equals(entry.getValue().getLast())){
				return entry.getKey();
			}
		}
		return null;
	}

	public boolean isEmpty(Position pos) {
		return getPiece(pos)==null;
	}
	
	/** Ajouter une piece a une position deja instanciée */
	public void addPiece(Piece piece, Position pos){
		for(Position key : Grid.keySet()){
			if(key.equals(pos)){
				Grid.get(key).addLast(piece);
			}
		}
	}

	/** Enlever La derniere piece de la liste
	 * a la position indiquée et déjà instanciée
	 */
	public void removeLast(Position pos){
		for(Position key : Grid.keySet()){
			if(key.equals(pos)){
				Grid.get(key).removeLast();
			}
		}
	}

	/** Retourne le chasseur ayant trouvé le trésor
	 * si il existe, retourne null sinon
	 * 
	 * @return Hunter	le chasseur ayant trouve le tresor
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
		for (int row = 0; row < Position.getMAX_HEIGHT(); row++){
			for (int col = 0; col < Position.getMAX_WIDTH(); col++){
				Position pos = new Position(row, col);
				Piece piece = getPiece(pos);
				if(piece != null){
					str += piece.toString();
				}else{
					str += " "; // \u00B7
				}
				
				if(pos.getCol() == Position.getMAX_WIDTH() - 1){
					str += "\n";
				}
				
			}
		}
		return str;
	}
}
