package PAKITO;

class Position implements Comparable{
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
    public int compareTo(Position other){
        if (this.equals(other)) return 0;
        if (this.row<other.getRow()) return -1;
        if (this.row==other.getRow()&&this.col<other.getCol()) return -1;
        return 1;
    }
    
    @Override
    public String toString() {
        return "row : "+row+", col : "+col;
    }
}