package modele;


public class RoadMap extends Fixed{ // Modele
    public RoadMap(){
        setStr('%');
    }
    
    public int getTreasureDirection(Grid g) {
    	Position roadMapPos = g.getPos(this);
    	Position treasurePos = g.getTreasurePosition();
    	
    	return roadMapPos.directionTo(treasurePos);
    }
    
    @Override
    public void process(Hunter h) {
        // Besoin de la direction du trésor
    	h.setDir(getTreasureDirection(h.getCtrl().getGame().getGrid()));
    	moveHunter(h);
    }
}
