package PAKITO;

public class Stone extends Fixed {
    public Stone() {
        setStr('\u25A0');
    }

    /* 
     * Si le chasseur possede un outil, 
     * il peut sauter par dessus le caillou
     * Sinon il est bloqué et doit 'glisser' 
     * le long du caillou 
     *
     * @param Hunter h  le chasseur
     */
    @Override
    public void process(Hunter h) {
        
    }
}
