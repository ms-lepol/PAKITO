package vue;

import controleur.Controleur;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class Fenetre {
    private Controleur c;
    private JFrame screen;
    private Map<String,String> srcAssets;
    
    private JButton start,quit,nextTurn,pause;
    
    public Fenetre(Controleur c) {
        this.c = c;
        initRessources();
        initTitleScreen();
    }
    
    public void initTitleScreen() {
        this.screen = new JFrame();
        screen.setSize(200, 200);
        
        Container ctn = screen.getContentPane();
        ctn.setLayout(new GridLayout(3,1));
        
        JPanel topFrame = new JPanel();
        JLabel title = new JLabel("PAKITO");
        JPanel midFrame = new JPanel();
        this.start = new JButton("Jouer");
        JPanel botFrame = new JPanel();
        this.quit = new JButton("Quitter");
        
        topFrame.add(title);
        midFrame.add(this.start);
        botFrame.add(this.quit);
        
        ctn.add(topFrame);
        ctn.add(midFrame);
        ctn.add(botFrame);
        
        
        screen.setVisible(true);
    }
    
    public void flush() {
    	Container ctn = screen.getContentPane();
    	ctn.removeAll();
    }
    
    public void initGameScreen() {
    	 Container ctn = screen.getContentPane();
         ctn.setLayout(new GridLayout(3,1));
         
         JPanel topFrame = new JPanel();
         JLabel title = new JLabel("PAKITO");
         
         JPanel midFrame = new JPanel();
         JPanel imageFrame = new JPanel();
         JTextArea feed = new JTextArea();
         
         JPanel botFrame = new JPanel();
         this.nextTurn = new JButton("Tour Suivant");
         this.pause = new JButton("Pause");
         
         topFrame.add(title);
         
         midFrame.add(imageFrame);
         midFrame.add(feed);
         
         botFrame.add(nextTurn);
         botFrame.add(pause);
         
         ctn.add(topFrame);
         ctn.add(midFrame);
         ctn.add(botFrame);
         
         screen.setVisible(true);
    }
    public void initRessources(){
    	this.srcAssets = new HashMap<String,String>();
    	srcAssets.put("stone", "../../assets/stone.png");
    	srcAssets.put("glue", "../../assets/glue.png");
    	srcAssets.put("roadmap", "../../assets/roadmap.png");
    	srcAssets.put("treasure", "../../assets/treasure.png");
    	srcAssets.put("tool", "../../assets/tool.png");
    	srcAssets.put("hunterA", "../../assets/hunterA.png");
    	srcAssets.put("hunterB", "../../assets/hunterB.png");
    	srcAssets.put("hunterC", "../../assets/hunterC.png");
    	srcAssets.put("hunterD", "../../assets/hunterD.png");
    	srcAssets.put("hunterE", "../../assets/hunterE.png");
    	srcAssets.put("borderV", "../../assets/borderV.png");
    	srcAssets.put("borderH", "../../assets/borderH.png");
    	srcAssets.put("borderHG", "../../assets/borderHG.png");
    	srcAssets.put("borderHD", "../../assets/borderHD.png");
    	srcAssets.put("borderBD", "../../assets/borderBD.png");
    	srcAssets.put("borderBG", "../../assets/borderBG.png");
    }
}