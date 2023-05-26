package vue;

import controleur.Controleur;
import java.io.InputStream;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class Fenetre {
    private Controleur c;
    private JFrame screen;
    private GridPanel imageFrame;
    private JButton start,quit,nextTurn,pause,reset;
    private JTextArea feed;
    private int sizeTile = 32;
    private Font digitalDisco;
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
	public JButton getReset() {
		return reset;
	}
	public JButton getNextTurn() {
		return nextTurn;
	}

	public JButton getPause() {
		return pause;
	}

	public JTextArea getFeed() {
		return feed;
	}
    
    public Fenetre(Controleur c) {
        this.c = c;
        try {
        	InputStream digitalDiscoFile =getClass().getResourceAsStream("/assets/DigitalDisco.ttf");
        	digitalDisco = Font.createFont(Font.TRUETYPE_FONT, digitalDiscoFile);
        	digitalDisco = digitalDisco.deriveFont((float) 16.0);
        } catch(Exception e) {
        	System.out.println("Font not found");
        	e.printStackTrace();
        };
        initTitleScreen();
    }
    
    public void initTitleScreen() {
        this.screen = new JFrame();
        screen.setSize(840, 840);
        flush();
        Container ctn = screen.getContentPane();
        ctn.setLayout(new BoxLayout(ctn,BoxLayout.Y_AXIS));
        
        ctn.setSize(new Dimension(840,840));
        TitlePanel topFrame = new TitlePanel();
        topFrame.setLayout(new FlowLayout());
        topFrame.setBackground(Color.black);
        topFrame.setPreferredSize(new Dimension(840,100));
        
        JPanel midFrame = new JPanel();
        this.start = new JButton("Jouer");
        this.start.addActionListener(c);
        JPanel botFrame = new JPanel();
        this.quit = new JButton("Quitter");
        this.quit.addActionListener(c);
        midFrame.add(this.start);
        botFrame.add(this.quit);
        
        ctn.add(topFrame);
        ctn.add(midFrame);
        ctn.add(botFrame);
        //topFrame.setSize(840, 100);
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
    	for (Component c : ctn.getComponents()) {
    		((Container) c).removeAll();
    	}
    }
    
    public void initGameScreen() {
    	 screen.setSize(840, 840);
    	 flush();
    	 screen.setBackground(Color.black);
    	 Container ctn = screen.getContentPane();
    	 
    	 //Context
         ctn.setLayout(new BoxLayout(ctn,BoxLayout.Y_AXIS));
         ctn.setSize(840,840);
         
         //TopFrame - logo
         TitlePanel topFrame = new TitlePanel();
         topFrame.setLayout(new FlowLayout());
         topFrame.setBackground(Color.black);
         topFrame.setPreferredSize(new Dimension(840,100));
         //MidFrame - Game & Feed
         JPanel midFrame = new JPanel();
         midFrame.setPreferredSize(new Dimension(200,640));
         //Game
         this.imageFrame = new GridPanel(c);
         midFrame.add(imageFrame);
         //TextArea for the feed - we put it in a scrollpane to have a scrollable area
         this.feed = new JTextArea();
         JScrollPane feedPane = new JScrollPane();
         feedPane.setPreferredSize(new Dimension(200,640));
         feed.setBounds(0,0,100,640);
         feed.setBorder(new LineBorder(Color.yellow));
         feed.setBackground(Color.black);
         //FONT
         feed.setForeground(Color.yellow);
         feed.setFont(digitalDisco);
         feedPane.getViewport().add(feed);
         feedPane.setBounds(0, 0, 100, 640);
         midFrame.add(feedPane);
         
         //feedPane.setBackground(Color.black);
         midFrame.setBackground(Color.black);
         midFrame.setSize(840, 640);
         
         //BotFrame - Buttons 
         JPanel botFrame = new JPanel();
         this.quit = new JButton("Quitter");
         this.pause = new JButton("Go");
         this.reset = new JButton("Reset");
         this.pause.addActionListener(c);
         this.quit.addActionListener(c);
         this.reset.addActionListener(c);
         
         botFrame.setBackground(Color.black);
         botFrame.add(quit);
         botFrame.add(pause);
         botFrame.add(reset);
         botFrame.setPreferredSize(new Dimension(840,100));
         
         ctn.add(topFrame);
         ctn.add(midFrame);
         ctn.add(botFrame);
         
         screen.pack();
    }
    
    public static void music() {


    }
  
}