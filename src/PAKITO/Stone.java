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
        	h.setHave_Tool(false);
        	return;
        }
        int direction= 0;
        Grid hgrid = h.getGrid();
        int tmpdir = h.getDir();
        Position stonePos = hgrid.getPos(this);
        System.out.println(stonePos.toString());
        boolean isVertical = (hgrid.getPiece(new Position(stonePos.getRow()+1,stonePos.getCol())) instanceof Stone) ||
        					 (hgrid.getPiece(new Position(stonePos.getRow()-1,stonePos.getCol())) instanceof Stone);
        if (isVertical) {
        	Position TopEdge = this.findEdge(stonePos,hgrid,-1,0);
        	Position BottomEdge = this.findEdge(stonePos,hgrid,1,0);
        	if ((stonePos.distanceTo(TopEdge)==0 && tmpdir == 7 ) || (stonePos.distanceTo(BottomEdge)==0 && tmpdir==3)){
        		direction = 1;
       		} else {
       			direction = (stonePos.distanceTo(TopEdge)<stonePos.distanceTo(BottomEdge)) ? 3 : 7;
       		}
        } else {
        	Position LeftEdge = this.findEdge(stonePos,hgrid,0,-1);
        	Position RightEdge = this.findEdge(stonePos,hgrid,0,1);
        	if ((stonePos.distanceTo(LeftEdge)==0 && tmpdir == 1 ) || (stonePos.distanceTo(RightEdge)==0 && tmpdir==5)){
        		direction = 3;
       		} else {
       			direction = (stonePos.distanceTo(LeftEdge)<stonePos.distanceTo(RightEdge)) ? 5 : 1;
       		}
        	
        }
        
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
