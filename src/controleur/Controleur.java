package controleur;

import vue.*;
import modele.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Controleur implements ActionListener{
    /* Attribut */
    private Game game;
	private Fenetre window;
	
    /* Constructeur */
    public Controleur(){
        game = new Game(this);
        this.window = new Fenetre(this);
        window.open();
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
    
    public Fenetre getWindow() {
    	return window;
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

        for(Mobile piece : actionList){
            boolean b = updatePiece(piece);
           
            if(b){
                clearEmpty();
                window.getImageFrame().repaint();
                wait(0.25);
            }
            if(((Hunter) piece).getHave_Tool()){
                game.addInfo(((Hunter) piece).getCol()+ " possède un outil\n");
                
            }
            if(((Hunter) piece).getWait_Time() > 0){
                game.addInfo(((Hunter) piece).getCol()+ " est bloqué !\n");
                
            }
            if(((Hunter) piece).getTreasure_found()){
                return;
            }
        }
    }
    
    // reset l'objet actuel
    public void reset() {
    	game.reset();
        window.reset();
        window.open();
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==this.window.getStart()) {
			window.initGameScreen();
		}
		if (e.getSource()==this.window.getQuit()) {
			System.exit(0);
		}
		if (e.getSource()==this.window.getPause()) {
			game.pause();
			String txtButton = (game.isPaused()) ? "Continue" : "Pause";
			this.window.getPause().setText(txtButton);
		}
		if(e.getSource()==this.window.getReset()) {
			game.setPause(true);
			wait(0.5);
			reset();
		}
	}
	
	public void wait(double sec){
        try {
            Thread.sleep((long)(sec*1000.0));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
