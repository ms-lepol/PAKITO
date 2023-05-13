package PAKITO;

public class RoadMap extends Fixed{
    public RoadMap(){
        setStr('M');
    }
    
    public int getTreasureDirection(Grid g) {
    	int dir = 0;
    	Position roadMapPos = g.getPos(this);
    	
    	
    	return dir;
    }
    
    @Override
    public void process(Hunter h) {
        // Besoin de la direction du trésor
    	
    }
}
