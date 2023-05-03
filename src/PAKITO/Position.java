package PAKITO;

class Position{
    static final int MAX_WIDTH = 200;
    static final int MAX_HEIGHT = 200;
    private int row;
    private int col;

    public Position(int col, int row){
        this.col = col;
        this.row = row;
    }

    public Position getNextPos(int dir){
        Position res = new Position(0, 0);
        switch (dir){
            case 1:
                break;
        }
        return res;
    } 

    public Boolean isValid(){
        if(row < 0 || row > MAX_HEIGHT || col < 0 || col > MAX_WIDTH){
            return false;
        }
        return true;
    }
}