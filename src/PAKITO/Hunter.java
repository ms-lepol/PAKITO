package PAKITO;

import java.util.LinkedList;

public class Hunter extends Mobile {
    static private int nb = 0;
    private boolean treasure_found = false;

    public Hunter() {
        setStr((char)(65+nb));
        nb++;

        wait_time = 0;
        dir = getRandDir();
    }

    public int getDir(){
        return dir;
    }

    public void setTreasure_found(boolean found) {
        this.treasure_found = found;
    }

    public void setWait_time(int n){
        this.wait_time = n;
    }

    @Override
    public void move(Grid g){
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

            // Supression de l'ancien emplacement
            g.Grid.keySet().removeIf(key -> key == startPos);
            
            return;
        }

		return;
	}
}
