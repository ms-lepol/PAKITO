package PAKITO;

class Position{
    static final int MAX_WIDTH = 200;
    static final int MAX_HEIGHT = 200;
    private int row;
    private int col;

    public Position getNextPos(int dir){
        switch (dir){
            case 1:
                break;
        }
    } 

    public Boolean isValid(){
        if(row < 0 || row > MAX_HEIGHT || col < 0 || col > MAX_WIDTH){
            return false;
        }
        return true;
    }
}