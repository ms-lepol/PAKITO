package vue;

import java.awt.*;

import javax.swing.*;

import controleur.Controleur;
import controleur.TileManager;
import modele.Position;

public class GridPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int width = Position.getMAX_WIDTH();
	private int height = Position.getMAX_HEIGHT();
	
	private int tileSize = 16;
	private int scale = 1;
	
	private Controleur c;
	private TileManager tileM;
	
	GridPanel(Controleur c){
		this.c = c;
		this.tileM = new TileManager(this,c);
		this.setPreferredSize(new Dimension(width*tileSize*scale,height*tileSize*scale));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
	}
	
	public int getTileSize() {
		return tileSize*scale;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		tileM.draw(g2);
		
		g2.dispose();
	}

}
