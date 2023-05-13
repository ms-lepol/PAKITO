package PAKITO;

import java.util.LinkedList;

public class Hunter extends Mobile {
    static private int nb = 0;
    private Grid g;
    private boolean treasure_found = false;
    private boolean have_tool = false;

    public Hunter() {
        setStr((char)(65+nb));
        nb++;

        wait_time = 0;
        dir = getRandDir();
    }

    public int getDir(){
        return dir;
    }

    public Grid getGrid(){
        return g;
    }

    public boolean getHave_Tool(){
        return have_tool;
    }

    public boolean getTreasure_found() {
        return treasure_found;
    }

    public void setHave_Tool(boolean have_tool) {
        this.have_tool = have_tool;
    }

    public void setTreasure_found(boolean treasure_found) {
        this.treasure_found = treasure_found;
    }

    public void setWait_time(int wait_time){
        this.wait_time = wait_time;
    }

    @Override
    public void move(Grid g){
        this.g = g;

        if(wait_time > 0){
            wait_time--;
            return;
        }

        Position startPos = g.getPos(this);
		Position endPos = this.getNextPos(startPos,dir);

        if(!(endPos.isValid())){
            System.out.println("Position "+ endPos.toString() +" non valide");
            return;
        }

        Piece target = g.getPiece(endPos);

        if(target == null){
            // Position libre
            // Placement sur la nouvelle position
            LinkedList<Piece> li = new LinkedList<Piece>();
            li.add(this);
            g.Grid.put(endPos, li);

            // Suppression de l'ancien emplacement
            g.removeLast(startPos);
            
            return;
        }

        if(target instanceof Piece){
		    ((Piece) target).process(this);
            return;
        }
	}

	@Override
	public void process(Hunter h) {
		// TODO Auto-generated method stub
		h.dir = h.getRandDir();
		h.move(h.getGrid());
	}
}
