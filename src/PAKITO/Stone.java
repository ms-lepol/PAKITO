package PAKITO;

public class Stone extends Fixed {
    public Stone() {
        setStr('\u25A0');
    }

    /* 
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
        	this.moveHunter(h);
        	return;
        }
        int direction= 0;
        Position stonePos = h.getGrid().getPos(this);
        boolean isVertical = h.getGrid().getPiece(new Position(stonePos.getRow(),stonePos.getCol()+1)) instanceof Stone ||
        					 h.getGrid().getPiece(new Position(stonePos.getRow(),stonePos.getCol()-1)) instanceof Stone;
        if (isVertical) {
        	Position TopEdge = this.findEdge(stonePos,h.getGrid(),0,-1);
        	Position BottomEdge = this.findEdge(stonePos,h.getGrid(),0,1);
        	direction = (stonePos.distanceTo(TopEdge)<stonePos.distanceTo(BottomEdge)) ? 3 : 7;
        } else {
        	Position LeftEdge = this.findEdge(stonePos,h.getGrid(),-1,0);
        	Position RightEdge = this.findEdge(stonePos,h.getGrid(),1,0);
        	direction = (stonePos.distanceTo(LeftEdge)<stonePos.distanceTo(RightEdge)) ? 5 : 1;
        }
        int tmpdir = h.dir;
        h.dir = direction;
        h.move(h.getGrid());
        h.dir = tmpdir;
    }
    
    public Position findEdge(Position origin, Grid grid,int rowDir,int colDir) {
    	Position curr = origin;
    	Position next = new Position(curr.getRow()+rowDir,curr.getCol()+colDir);
    	while(grid.getPiece(next) instanceof Stone) {
    		curr=next;
    		next = new Position(curr.getRow(),curr.getCol()-1);
    	}
    	return curr;
    }
}
