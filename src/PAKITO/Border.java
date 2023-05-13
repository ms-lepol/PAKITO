package PAKITO;

import java.util.LinkedList;

public class Border extends Fixed{
    public Border(char c){
        setStr(c);
    }

    /*
     * Rediriger le personnage façon miroir
     */
    @Override
    public void process(Hunter h) {
    	Grid g = h.getGrid();
    	boolean isVert = this.isVertical(g.getPos(this));
        switch(h.dir) {
        	case 1:
        		h.dir=5;
        		break;
        	case 2:
        		if (isVert) {
        			h.dir=8;
        		} else {
        			h.dir=4;
        		}
        		break;
        	case 3:
        		h.dir=7;
        		break;
        	case 4:
        		if (isVert) {
        			h.dir=6;
        		} else {
        			h.dir=2;
        		}
        		break;
        	case 5:
        		h.dir=1;
        		break;
        	case 6:
        		if (isVert) {
        			h.dir=4;
        		} else {
        			h.dir=8;
        		}
        		break;
        	case 7:
        		h.dir=3;
        		break;
        	case 8:
        		if (isVert) {
        			h.dir=2;
        		} else {
        			h.dir=6;
        		}
        		break;
        	default:
        		h.dir=1;
        		break;
        }
        h.move(g);
    }
    
    public boolean isVertical(Position pos) {
    	return (pos.getCol()==0)||(pos.getCol()==Position.MAX_WIDTH-1);
    }
}

