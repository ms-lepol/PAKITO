package PAKITO;

public class Game { // Vue

    /* Attributs */
    private Grid g;
    private Controleur c;

    private boolean finish = false;
    private int round; 

    /* Constructeur */
    public Game(Controleur c){
        this.c = c;
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

    public void startGame(){
        round = 1;
        g = new Grid(c);
        
        Hunter winner = null;
        printGrid(round);

        while(!finish){
            if(round % 10 == 0){
                c.randAllDir();
            }

            c.gridUpdate();
            
            winner = c.foundTreasure();
            if(winner != null){
                finish = true;
                break;
            }

            round++;
        }
        printGrid(round);
        System.out.println("FIN DU JEU ---------");
        System.out.println(winner.toString()+" Gagne la partie --");
        System.out.print("\n");
    }

    /** Vide d'abord le terminal puis affiche la grille
     * avec le numero de tour
     * enfin marque une pause d'une demi seconde  
     * 
     * @param round     le nombre de tours
     */
    public void printGrid(int round){
        System.out.print("\033[H\033[2J"); // Clear l'invite de commande
        System.out.flush(); 

        System.out.println("TOUR N°"+(int)(round)+" -----------");
        System.out.println(g.toString());

        wait(0.5);
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
    public void wait(double sec){
        try {
            Thread.sleep((long)(sec*1000.0));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
