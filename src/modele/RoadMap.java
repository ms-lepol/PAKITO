package modele;


public class RoadMap extends Fixed{ // Modele
    private int dirToTreasure;

    public RoadMap(int n){
        setStr('%');
        dirToTreasure = n;
    }
    
    @Override
    public void process(Hunter h) {
        // Besoin de la direction du trésor
        System.out.println(h.getDir());
    	h.setDir(dirToTreasure);
        System.out.println(h.getDir());
        moveHunter(h);
    }
}
