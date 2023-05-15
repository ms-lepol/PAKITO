package PAKITO;

public class Game { // Vue
    private Grid g;
    private Controleur c;

    private boolean finish = false;
    private int round; 

    public Game(Controleur c){
        this.c = c;
    }

    public Controleur getCtrl() {
        return c;
    }

    public Grid getGrid() {
        return g;
    }

    public int getRound() {
        return round;
    }

    public void startGame(){
        round = 0;
        g = new Grid(c);
        
        Hunter winner = null;
        printGrid(round);

        while(!finish){
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

    public void printGrid(int round){
        System.out.print("\033[H\033[2J"); // Clear l'invite de commande
        System.out.flush(); 

        System.out.println("TOUR N°"+(int)(round+1)+" -----------");
        System.out.println(g.toString());

        wait(0.5);
    }

    /*
     * Permet de mettre le fil actuel en pause 
     * pendant le nombre de secondes indiquées
     */
    public void wait(double sec){
        try {
            Thread.sleep((long)(sec*1000.0));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
