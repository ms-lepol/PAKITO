package PAKITO;

public class Mobile extends Piece {

    /*
     * Calcule la position de la piece mobile actuelle 
     * si elle se deplace dans la direction donnée
     * 
     * @param int dir       la direction voulue
     * 
     * @return Position     la position hypothetyque de la piece
     */
    public Position getNextPos(int dir){
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
        return new Position(this.pos.getCol()+col, this.pos.getRow()+row);
    }

    /*
     * Déplace la pièce actuelle dans la direction voulue
     * 
     * @param int dir      la direction voulue
     * 
     * @return Boolean     la réussite ou l'échec du déplacement
     */
    public Boolean moveTo(int dir){
        Position res = getNextPos(dir);
        if(!res.isValid()){
            return false;
        }
        this.pos = res;
        return true;
    }
}
