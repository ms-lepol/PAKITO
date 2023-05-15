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
        Grid hgrid = h.getGrid();
        Position stonePos = hgrid.getPos(this);
        System.out.println(stonePos.toString());
        boolean isVertical = (hgrid.getPiece(new Position(stonePos.getRow()+1,stonePos.getCol())) instanceof Stone) ||
        					 (hgrid.getPiece(new Position(stonePos.getRow()-1,stonePos.getCol())) instanceof Stone);
        if (isVertical) {
        	Position TopEdge = this.findEdge(stonePos,hgrid,-1,0);
        	Position BottomEdge = this.findEdge(stonePos,hgrid,1,0);
        	direction = (stonePos.distanceTo(TopEdge)<stonePos.distanceTo(BottomEdge)) ? 3 : 7;
        } else {
        	Position LeftEdge = this.findEdge(stonePos,hgrid,0,-1);
        	Position RightEdge = this.findEdge(stonePos,hgrid,0,1);
        	direction = (stonePos.distanceTo(LeftEdge)<stonePos.distanceTo(RightEdge)) ? 5 : 1;
        }
        int tmpdir = h.getDir();
        h.setDir(direction);
        h.move(h.getGrid());
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
