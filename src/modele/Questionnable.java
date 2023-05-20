package modele;



public interface Questionnable { // Modele
    public void process(Hunter h);
    // la case modifie le personnage h
    // un message est affiché à la console pour expliciter
    // la (les) modification(s) du personnage
}
