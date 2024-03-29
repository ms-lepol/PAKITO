package modele;




public class Stone extends Fixed { // Modele
    public Stone() {
        setStr('\u2588');
    }

    /**
     * Si le chasseur possede un outil, 
     * il peut sauter par dessus le caillou
     * Sinon il est bloqué et doit 'glisser' 
     * le long du caillou 
     *
     * @param Hunter h  le chasseur
     */
    @Override
    public void process(Hunter h) {
        if (h.getHave_Tool()) {
        	moveHunter(h);
        	h.setHave_Tool(false);
        	return;
        }
        int direction= 0;
        Grid hgrid = h.getCtrl().getGame().getGrid();
        int tmpdir = h.getDir();
        Position stonePos = hgrid.getPos(this);
        boolean isVertical = (hgrid.getPiece(new Position(stonePos.getRow()+1,stonePos.getCol())) instanceof Stone) ||
        					 (hgrid.getPiece(new Position(stonePos.getRow()-1,stonePos.getCol())) instanceof Stone);
        if (isVertical) {
        	Position TopEdge = this.findEdge(stonePos,hgrid,-1,0);
        	Position BottomEdge = this.findEdge(stonePos,hgrid,1,0);
        	if ((stonePos.distanceTo(TopEdge)==0 && (tmpdir >= 6 && tmpdir <= 8)) || 
        		(stonePos.distanceTo(BottomEdge)==0 && (tmpdir >= 2 && tmpdir <= 4))){
        		direction = 1;
       		} else {
       			direction = (stonePos.distanceTo(TopEdge)<stonePos.distanceTo(BottomEdge)) ? 3 : 7;
       		}
        } else {
        	Position LeftEdge = this.findEdge(stonePos,hgrid,0,-1);
        	Position RightEdge = this.findEdge(stonePos,hgrid,0,1);
        	if ((stonePos.distanceTo(LeftEdge)==0 && (tmpdir == 1 || tmpdir == 8 || tmpdir == 2)) || 
        			(stonePos.distanceTo(RightEdge)==0 && (tmpdir >= 4 && tmpdir <= 6))){
        		direction = 3;
       		} else {
       			direction = (stonePos.distanceTo(LeftEdge)<stonePos.distanceTo(RightEdge)) ? 5 : 1;
       		}
        	
        }
        
        h.setDir(direction);
        h.move();
        h.setDir(tmpdir);
    }
    
    public Position findEdge(Position origin, Grid grid,int rowDir,int colDir) {
    	Position curr = origin;
    	Position next = new Position(curr.getRow()+rowDir,curr.getCol()+colDir);
    	while(grid.getPiece(next) instanceof Stone) {
    		curr=next;
    		next = new Position(curr.getRow()+rowDir,curr.getCol()+colDir);
    	}
    	return curr;
    }
}
