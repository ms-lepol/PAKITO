package PAKITO;

public class RoadMap extends Fixed{ // Modele
    public RoadMap(){
        setStr('M');
    }
    
    public int getTreasureDirection(Grid g) {
    	int dir = 0;
    	Position roadMapPos = g.getPos(this);
    	Position treasurePos = new Position();
    	for (Position key : g.Grid.keySet()) {
			Piece p = g.getPiece(key);
			if(p instanceof Treasure){
				treasurePos = key;
			}
		}
    	
    	return roadMapPos.directionTo(treasurePos);
    }
    
    @Override
    public void process(Hunter h) {
        // Besoin de la direction du trésor
    	h.dir = getTreasureDirection(h.getCtrl().getGame().getGrid());
    	moveHunter(h);
    }
}
