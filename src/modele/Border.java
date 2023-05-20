package modele;



public class Border extends Fixed{ // Modele
    public Border(char c){
        setStr(c);
    }

    /**
     * Rediriger le personnage façon miroir
     */
    @Override
    public void process(Hunter h) {
    	boolean isVert = this.isVertical(h.getCtrl().getGame().getGrid().getPos(this));
        switch(h.getDir()) {
        	case 1:
        		h.setDir(5);
        		break;
        	case 2:
        		if (isVert) {
        			h.setDir(4);
        		} else {
        			h.setDir(8);
        		}
        		break;
        	case 3:
        		h.setDir(7);;
        		break;
        	case 4:
        		if (isVert) {
        			h.setDir(2);
        		} else {
        			h.setDir(6);
        		}
        		break;
        	case 5:
        		h.setDir(1);
        		break;
        	case 6:
        		if (isVert) {
        			h.setDir(8);
        		} else {
        			h.setDir(4);
        		}
        		break;
        	case 7:
        		h.setDir(3);
        		break;
        	case 8:
        		if (isVert) {
        			h.setDir(6);
        		} else {
        			h.setDir(2);
        		}
        		break;
        	default:
        		h.setDir(1);
        		break;
        }
        h.move();
    }
    
    public boolean isVertical(Position pos) {
    	return (pos.getCol()==0)||(pos.getCol()==Position.getMAX_WIDTH()-1);
    }
}

