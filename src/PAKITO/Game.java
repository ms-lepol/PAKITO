package PAKITO;

public class Game {
    private boolean finish = false;
    private final int N_ROUND = 5; 

    public Game(){
        Grid g = new Grid();
        Hunter h;
        System.out.println(g.toString());
        wait(1);
        //while(!finish){
        for(int round = 0; round < N_ROUND; round++){
            g.update();
            System.out.println(g.toString());
            h = g.foundTreasure();
            if(h != null){
                finish = true;
                break;
            }
            wait(1);
        }
        System.out.println("------------ FIN DU JEU ------------");
        //System.out.println("------------ "+h.toString()+" Gagne la partie ------------");
        //}
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
