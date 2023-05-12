package PAKITO;

public class Game {
    private boolean finish = false;
    private final int N_ROUND = 5; 

    public Game(){
        Grid g = new Grid();
        //while(!finish){
        for(int round = 0; round < N_ROUND; round++){
            System.out.println(g.toString());
            g.update();
            wait(1);
        }
        //}
    }

    public void wait(int sec){
        try {
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
