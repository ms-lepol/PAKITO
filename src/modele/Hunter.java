package modele;

import controleur.*;

import java.util.LinkedList;

public class Hunter extends Mobile { // Modele
    private Controleur c;

    static private int nb = 0;
    private ColorHunter col;
    
    private boolean treasure_found = false;
    private boolean have_tool = false;

    public Hunter(Controleur c) {
        this.c = c;
        ColorHunter tab[] = ColorHunter.values();
        this.col = tab[nb];
        setStr((char)(65+nb));
        nb++;

        wait_time = 0;
        dir = getRandDir();
    }
    public int getDir(){
        return dir;
    }
    public static void resetNb() {
		Hunter.nb = 0;
	}
    public ColorHunter getCol() {
    	return col;
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

    public int getWait_Time(){
        return wait_time;
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

    /** Action lors de la rencontre de deux chasseurs
     * 
     * Si le chasseur actuel gagne, le chasseur qui bloque le passage
     * recule et celui qui a lancé process avance 
     * 
     * Sinon le chasseur actuel attend que l'autre passe
     * 
     * @param h     le chasseur croisé
     */
	@Override
	public void process(Hunter h) {
        c.getWindow().getFeed().append("------\n\n"+this.getCol()+" croise "+h.getCol()+"\n");
		boolean win = Math.random() >= 0.5;
		if(win) {
	        c.getWindow().getFeed().append(this.getCol()+" gagne la rencontre\n\n------\n");
			h.setDir(Position.getReverseDir(h.getDir()));
			h.move(); // h recule 
			c.clearEmpty(); // On evite d'avoir une case 'fantome'
			h.setDir(Position.getReverseDir(h.getDir()));
			h.setWait_time(h.getWait_Time()+1);
			this.move();
			c.clearEmpty();
		}else {
	        c.getWindow().getFeed().append(this.getCol()+" perd la rencontre\n\n------\n");
            this.setWait_time(this.getWait_Time()+1);
            h.move();
            c.clearEmpty();
		}
		c.wait(0.5);
	}
}
