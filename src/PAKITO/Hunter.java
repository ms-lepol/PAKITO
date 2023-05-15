package PAKITO;

import java.util.LinkedList;

public class Hunter extends Mobile { // Modele
    private Controleur c;

    static private int nb = 0;
    private boolean treasure_found = false;
    private boolean have_tool = false;

    public Hunter(Controleur c) {
        this.c = c;

        setStr((char)(65+nb));
        nb++;

        wait_time = 0;
        dir = getRandDir();
    }

    public int getDir(){
        return dir;
    }

    public Controleur getCtrl() {
        return c;
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

    /** Commence par regarder si le joueur est bloqué
     * si non, on calcule la position suivante et on regarde
     * si c'est une case vide, une case fixe ou un autre chasseur
     * 
     * On agit en conséquences
     *  
     * @return boolean    true si le deplacement s'est bien terminé,
     *                    false sinon
     */
    @Override
    public boolean move(){
        if(wait_time > 0){
            wait_time--;
            return true;
        }

        Position startPos = c.getGame().getGrid().getPos(this);
		Position endPos = getNextPos(startPos,dir);

        if(!(endPos.isValid())){
            return false;
        }

        Piece target = c.getGame().getGrid().getPiece(endPos);

        if(target == null){
            // Position libre
            // Placement sur la nouvelle position
            LinkedList<Piece> li = new LinkedList<Piece>();
            li.add(this);
            c.getGame().getGrid().getGrid().put(endPos, li);

            // Suppression de l'ancien emplacement
            c.getGame().getGrid().removeLast(startPos);

            return true;
        }

        if(target instanceof Piece){
		    ((Piece) target).process(this);
            return true;
        }
        return false;
	}

    /** Change de direction a la rencontre d'un autre chasseur
     * 
     * Provisoire
     * 
     * @param h     le chasseur croisé
     */
	@Override
	public void process(Hunter h) {
		h.dir = h.getRandDir();
		h.move();
	}
}
