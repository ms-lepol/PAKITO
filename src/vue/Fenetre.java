package vue;

import controleur.Controleur;

import java.awt.*;
import javax.swing.*;

public class Fenetre {
    private Controleur c;
    private JFrame screen;
    private GridPanel imageFrame;
    private JButton start,quit,nextTurn,pause;
    
    private int sizeTile = 32;
    
    public JFrame getScreen() {
		return screen;
	}

	public GridPanel getImageFrame() {
		return imageFrame;
	}

	public JButton getStart() {
		return start;
	}
	public JButton getQuit() {
		return quit;
	}
	public JButton getNextTurn() {
		return nextTurn;
	}

	public JButton getPause() {
		return pause;
	}

	
    
    public Fenetre(Controleur c) {
        this.c = c;
        
        initTitleScreen();
    }
    
    public void initTitleScreen() {
        this.screen = new JFrame();
        screen.setSize(840, 840);
        
        Container ctn = screen.getContentPane();
        ctn.setLayout(new GridLayout(3,1));
        
        JPanel topFrame = new JPanel();
        JLabel title = new JLabel("PAKITO");
        JPanel midFrame = new JPanel();
        this.start = new JButton("Jouer");
        this.start.addActionListener(c);
        JPanel botFrame = new JPanel();
        this.quit = new JButton("Quitter");
        this.quit.addActionListener(c);
        topFrame.add(title);
        midFrame.add(this.start);
        botFrame.add(this.quit);
        
        ctn.add(topFrame);
        ctn.add(midFrame);
        ctn.add(botFrame);
        topFrame.setSize(840, 100);
        topFrame.setBackground(Color.black);
        midFrame.setBackground(Color.black);
        botFrame.setBackground(Color.black);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void open() {
    	screen.setVisible(true);
    }
    public void flush() {
    	Container ctn = screen.getContentPane();
    	ctn.removeAll();
    }
    
    public void initGameScreen() {
    	 flush();
    	 screen.setBackground(Color.black);
    	 Container ctn = screen.getContentPane();
         ctn.setLayout(new GridLayout(3,1));
         ctn.setSize(840,840);
         
         JPanel topFrame = new JPanel();
         JLabel title = new JLabel("PAKITO");
         
         JPanel midFrame = new JPanel();
         this.imageFrame = new GridPanel(c);
         JTextArea feed = new JTextArea();
         
         JPanel botFrame = new JPanel();
         this.quit = new JButton("Quitter");
         this.pause = new JButton("Pause");
         this.pause.addActionListener(c);
         this.quit.addActionListener(c);
         topFrame.add(title);
         
         midFrame.add(imageFrame);
         midFrame.add(feed);
         
         botFrame.add(quit);
         botFrame.add(pause);
         
         topFrame.setBackground(Color.red);
         midFrame.setBackground(Color.black);
         botFrame.setBackground(Color.black);
         topFrame.setPreferredSize(new Dimension(840,100));
         midFrame.setSize(840, 640);
         botFrame.setPreferredSize(new Dimension(840,100));
         ctn.add(topFrame);
         ctn.add(midFrame);
         ctn.add(botFrame);
         
         screen.pack();
    }
  
}