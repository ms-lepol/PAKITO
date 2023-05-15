package PAKITO;

public class Game { // Vue
    private Grid g;
    private Controleur c;

    private boolean finish = false;
    private final int N_ROUND = 20; 

    public Game(Controleur c){
        this.c = c;
    }

    public void startGame(){
        g = new Grid(c);
        
        Hunter winner;
        //System.out.println(g.toString());
        wait(1);

        int round = 0;
        while(!finish){
        //for(int round = 0; round < N_ROUND; round++){
            System.out.flush();

            c.gridUpdate();

            System.out.println("------------------------------------ TOUR N°"+(int)(round+1)+" ------------------------------------");
            
            System.out.println(g.toString());
            
            winner = c.foundTreasure();
            if(winner != null){
                finish = true;
                break;
            }
            wait(1);
            round++;
        }
        System.out.println("------------ FIN DU JEU ------------");
        //System.out.println("------------ "+h.toString()+" Gagne la partie ------------");
        System.out.print("\n");
    }

    public Controleur getCtrl() {
        return c;
    }

    public Grid getGrid() {
        return g;
    }

    /*
     * Permet de mettre le fil actuel en pause 
     * pendant le nombre de secondes indiquées
     */
    public void wait(int sec){
        try {
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
