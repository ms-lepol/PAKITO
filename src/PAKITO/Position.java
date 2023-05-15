package PAKITO;

class Position implements Comparable<Position>{ // Modele
    static final int MAX_WIDTH = 20;
    static final int MAX_HEIGHT = 10;
    private int row;
    private int col;

    public Position(int row, int col){
        this.col = col;
        this.row = row;
    }

    public Position(){
        this.col = 0;
        this.row = 0;
    }

    public int getCol(){
        return this.col;
    }

    public int getRow(){
        return this.row;
    }
    
    public void setCol(int col) {
    	this.col = col;
    }
    
    public void setRow(int row) {
    	this.row = row;
    }
    
    /*
     * Informe de la validité de la position dans la grille
     * 
     * @return Boolean     true si la piece est dans la grille
     *                     false sinon
     */
    public Boolean isValid(){
        if(row < 0 || row > MAX_HEIGHT || col < 0 || col > MAX_WIDTH){
            return false;
        }
        return true;
    }

    /*
    * Donne une position aléatoire qui ne soit pas un bord
    * 
    * @return Position     La position aleatoire
    * 
    */
    public static Position randPos(){
        return new Position((int)(1+Math.random()*(Position.MAX_HEIGHT - 2)), (int)(1+Math.random()*(Position.MAX_WIDTH - 2)));
    }
    /*
    * Donne une position aléatoire qui appartient rentre dans les coordonnées exigées (bornes comprises)
    * 
    * @return Position     La position aleatoire
    * 
    */
    public static Position randPos(int minH, int maxH, int minW, int maxW){
        return new Position((int)(1+Math.random()*(maxH-minH)+minH), (int)(1+Math.random()*(maxW-minW)+minW));
    }
    /*
    * Comparaison simplifiee de deux positions
    * 
    * @param Object o  une autre position
    *
    * @return boolean     true si egal
    *                     false sinon
    */
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof Position)) return false;
        Position oPos = (Position)o;
        if(this.row == oPos.row && this.col == oPos.col){
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Position o){
        if (this.equals(o)) return 0;
        if (this.row<o.getRow()) return -1;
        if (this.row==o.getRow()&&this.col<o.getCol()) return -1;
        return 1;
    }
    
    public int distanceTo(Position o) {
    	
    	if (this.equals(o)) {
    		return 0;
    	}
    	int rowDistance = Math.abs(o.getRow()-this.row);
    	int colDistance = Math.abs(o.getRow()-this.row);
    	int distance = rowDistance*rowDistance + colDistance * colDistance;
    	return distance;
    }
    
    public int directionTo(Position o) {
    	if (col-o.getCol()==0) {
    		return (row<o.getRow()) ? 7:3 ;
    	}
    	if (row-o.getRow()==0) {
    		return (col<o.getCol()) ? 1:5 ;
    	}
    	if (o.getRow()<row) {
    		return (o.getCol()<col) ? 4:2;
    	} else {
    		return (o.getCol()<col) ? 6:8;
    	}
   }
    
    @Override
    public String toString() {
        return "row : "+row+", col : "+col;
    }
}