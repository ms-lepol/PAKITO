package vue;

import java.util.*;

import controleur.*;
import modele.*;

public class Game { // Vue

    /* Attributs */
    private Grid g;
    private Controleur c;

    private boolean finish = false;
    private int round; 
    private List<String> info;
    private boolean pause = true;
    
    /* Constructeur */
    public Game(Controleur c){
        this.c = c;
        this.info = new LinkedList<String>();    
        g = new Grid(c);
    }

    /* Getter & Setter */
    public Controleur getCtrl() {
        return c;
    }

    public Grid getGrid() {
        return g;
    }

    public int getRound() {
        return round;
    }

    /* Methodes */
    public void pause() {
    	this.pause= !pause;
    }
    
    public boolean isPaused() {
    	return pause;
    }
    public void startGame(){
        round = 1;
        pause=true;
        finish=false; // double verification
        Hunter winner = null;
        //printGrid(round);
        while(!finish){
        	while(pause) {
        		c.wait(0.5);
        	}

            c.gridUpdate();
            
            winner = c.foundTreasure();
            if(winner != null){
                finish = true;
                break;
            }
            printInfo();
            this.info.clear();
            round++;
        }
        //printGrid(round);
        c.getWindow().getFeed().append("-- FIN DU JEU --\n");
        c.getWindow().getFeed().append(winner.getCol()+" gagne la partie --\n\n");
        for(int i = 3; i>0; i--) {
        	c.wait(1.0);
        	c.getWindow().getFeed().append("Fermeture dans "+i+" seconde(s)\n");
        }
        c.getWindow().getScreen().dispose();
    }

    /** Vide d'abord le terminal puis affiche la grille
     * avec le numero de tour
     * enfin marque une pause d'une demi seconde  
     * 
     * @param round     le nombre de tours
     */
    public void printGrid(int round){
        System.out.print("\033[H\033[2J"); // Clear l'invite de commande
        System.out.flush();                // (ne fonctionne pas sur tous les systemes)

        System.out.println("TOUR N°"+(int)(round)+" -----------");
        System.out.println(g.toString());
        printInfo();

        c.wait(0.5);
    }

    /** Notifie dans le terminal que l'un des chasseur
     * possede un outil
     * 
     * @param p     le chasseur ayant trouvé l'outil
     */
    public void notifTool(Mobile p){
        System.out.println(p.toString()+" a ramassé un Outil !");
    }

    /** Met le fil actuel en pause 
     * pendant le nombre de secondes indiquées
     * 
     * @param sec   le nombre de seconde a attendre
     */

    public void addInfo(String i) {
    	this.info.add(i);
    }
    
    public void printInfo() {
    	for(String i : info) {
    		c.getWindow().getFeed().append(i);;
    	}
    }

	public void reset() {
	    round = 1; 
		info = new LinkedList<String>();
		finish = false;
		pause = true;
		g.reset();
	}

	public void setPause(boolean b) {
		this.pause = b;
	}
}
