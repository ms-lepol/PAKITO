package PAKITO;

class Position {
    static final int MAX_WIDTH = 200;
    static final int MAX_HEIGHT = 200;
    private int row;
    private int col;

    public Position(int col, int row){
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
}