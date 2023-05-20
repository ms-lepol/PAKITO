package modele;


abstract public class Mobile extends Piece implements Moveable{ // Modele
    int dir;
    int wait_time;

    /**
     * Donne une direction aleatoire
     * 
     * @return int  la direction 
     * 
     */
    public int getRandDir(){
        return (int)(1+Math.random()*7);
    }

    public void setDir(int direction) {
    	dir = direction;
    }

    /**
     * Calcule la nouvelle position d'un déplacement
     * dans une direction donnée 
     * 
     * @param Position curr  la position de base
     * @param int dir       la direction voulue
     * 
     * @return Position     la position hypothetyque de la piece
     */
    public Position getNextPos(Position curr, int dir){
        int row = 0; 
        int col = 0;
        switch (dir){
            case 1:
                col =  1;
                break;
            case 2:
                col =  1;
                row = -1;
                break;
            case 3:
                row = -1;
                break;
            case 4:
                col = -1;
                row = -1;
                break;
            case 5:
                col = -1;
                break;
            case 6:
                col = -1;
                row =  1;
                break;
            case 7:
                row =  1;
                break;
            case 8:
                row =  1;
                col =  1;
                break;
            default:
                // Erreur dans la direction donnée
                return new Position(-1, -1);
        }
        return new Position(curr.getRow()+row, curr.getCol()+col);
    }
}
