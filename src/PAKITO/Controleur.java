package PAKITO;

import java.util.*;

public class Controleur {

    /* Attribut */
    private Game game;

    /* Constructeur */
    public Controleur(){
        game = new Game(this);
    }

    /* Getter & Setter */
    public Game getGame(){
        return game;
    }

    public int getRound(){
        return game.getRound();
    }

    public void setWidth(int w){
        Position.setMAX_WIDTH(w);
    }

    public void setHeight(int h){
        Position.setMAX_HEIGHT(h);
    }

    public void setNbHunter(int n){
        game.getGrid().setNB_HUNTER(n);
    }

    /* Methodes */

    public void startGame(){
        game.startGame();
    }

    /** Retourne le chasseur ayant trouvé le trésor
	 * si il existe, null sinon
	 * 
	 * @return Hunter	le chasseur ayant trouve le tresor
	 */
    public Hunter foundTreasure(){
        return game.getGrid().foundTreasure();
    }
    
    /** Met une piece mobile a jour
     * 
     * @param Mobile p  la piece a update
     * 
     * @return boolean  true si p.move() renvoie true,
     *                  false sinon
     */
    public boolean updatePiece(Mobile p){
        return p.move();
    }
    
    /** Libère les cases sans pièce toujours existantes */
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

    /** Affiche la grille */
    public void printGrid(){
        game.printGrid(getRound());
    }

    /** Met toute la grille a jour */
    public void gridUpdate(){
        LinkedList<Mobile> actionList = game.getGrid().getLiHunter();
        Mobile notif = null;

        for(Mobile piece : actionList){
            boolean b = updatePiece(piece);
            if(b){
                clearEmpty();
                printGrid();
            }
            if(((Hunter) piece).getTreasure_found()){
                notif = piece;
            }
            if(((Hunter) piece).getTreasure_found()){
                return;
            }
        }

        if(notif!=null){
            game.notifTool(notif); // Probleme de flush instantané
        }
    }

    /** Randomise la direction de toutes les pieces mobiles */
    public void randAllDir(){
        LinkedList<Mobile> actionList = game.getGrid().getLiHunter();

        for(Mobile piece : actionList){
            piece.setDir(piece.getRandDir());
        }
    }

}
