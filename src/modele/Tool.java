package modele;



public class Tool extends Fixed { // Modele
    public Tool() {
        setStr('=');
    }

    @Override
    public void process(Hunter h) {
        if(!h.getHave_Tool()){
            h.setHave_Tool(true);
        }

        moveHunter(h);
    }
}
