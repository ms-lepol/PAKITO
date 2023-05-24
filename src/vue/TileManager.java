package vue;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import controleur.Controleur;
import modele.Border;
import modele.Glue;
import modele.Hunter;
import modele.Piece;
import modele.Position;
import modele.RoadMap;
import modele.Stone;
import modele.Tool;
import modele.Treasure;

public class TileManager {
	GridPanel gp;
	Tile[] tile;
	Controleur c;
	
	public TileManager(GridPanel gp,Controleur c) {
		this.gp = gp;
		this.c = c;
		tile = new Tile[16];
		getTileImage();
		
	}
	
	public void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].img = ImageIO.read(getClass().getResourceAsStream("/assets/stone.png"));
	
			tile[1] = new Tile();
			tile[1].img = ImageIO.read(getClass().getResourceAsStream("/assets/glue.png"));
			
			tile[2] = new Tile();
			tile[2].img = ImageIO.read(getClass().getResourceAsStream("/assets/roadmap.png"));
			
			tile[3] = new Tile();
			tile[3].img = ImageIO.read(getClass().getResourceAsStream("/assets/treasure.png"));
			
			tile[4] = new Tile();
			tile[4].img = ImageIO.read(getClass().getResourceAsStream("/assets/tool.png"));
			
			tile[5] = new Tile();
			tile[5].img = ImageIO.read(getClass().getResourceAsStream("/assets/hunterA.png"));
			
			tile[6] = new Tile();
			tile[6].img = ImageIO.read(getClass().getResourceAsStream("/assets/hunterB.png"));
			
			tile[7] = new Tile();
			tile[7].img = ImageIO.read(getClass().getResourceAsStream("/assets/hunterC.png"));
			
			tile[8] = new Tile();
			tile[8].img = ImageIO.read(getClass().getResourceAsStream("/assets/hunterD.png"));
			
			tile[9] = new Tile();
			tile[9].img = ImageIO.read(getClass().getResourceAsStream("/assets/hunterE.png"));
			
			tile[10] = new Tile();
			tile[10].img = ImageIO.read(getClass().getResourceAsStream("/assets/borderV.png"));
			
			tile[11] = new Tile();
			tile[11].img = ImageIO.read(getClass().getResourceAsStream("/assets/borderH.png"));
			
			tile[12] = new Tile();
			tile[12].img = ImageIO.read(getClass().getResourceAsStream("/assets/borderHG.png"));
			
			tile[13] = new Tile();
			tile[13].img = ImageIO.read(getClass().getResourceAsStream("/assets/borderHD.png"));
			
			tile[14] = new Tile();
			tile[14].img = ImageIO.read(getClass().getResourceAsStream("/assets/borderBD.png"));
			
			tile[15] = new Tile();
			tile[15].img = ImageIO.read(getClass().getResourceAsStream("/assets/borderBG.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g2) {
		g2.setColor(Color.red);
		Map<Position, LinkedList<Piece>> grid = c.getGame().getGrid().getGrid();
		for(Map.Entry<Position, LinkedList<Piece>> entry : grid.entrySet()){
			LinkedList<Piece> li= entry.getValue();
			if (!li.isEmpty()) {
				for (Piece curr : li) {
					int x = entry.getKey().getCol();
					int y = entry.getKey().getRow();
					BufferedImage img = null;
					
					if (curr instanceof Border) {
						if (x==0) {
							if (y==0) {
								//HG
								img=tile[12].img;
							} else if (y==Position.getMAX_HEIGHT()-1) {
								img=tile[15].img;
							} else {
								img=tile[10].img;
							}
						} else if (x==Position.getMAX_WIDTH()-1) {
							if (y==0) {
								img=tile[13].img;
							} else if (y==Position.getMAX_HEIGHT()-1) {
								img=tile[14].img;
							} else {
								img=tile[10].img;
							}
						} else {
							img=tile[11].img;
						}
					}
					if (curr instanceof RoadMap) {
						img=tile[2].img;
					}
					if (curr instanceof Treasure) {
						img=tile[3].img;
					}
					if (curr instanceof Tool) {
						img=tile[4].img;
					}
					if (curr instanceof Stone) {
						img=tile[0].img;
					}
					if (curr instanceof Glue) {
						img=tile[1].img;
					}
					if (curr instanceof Hunter) {
						int i = 5;
						switch(curr.toString()) {
							case "A":
								i=5;
								break;
							case "B":
								i=6;
								break;
							case "C":
								i=7;
								break;
							case "D":
								i=8;
								break;
							case "E":
								i=9;
								break;
						}
						img=tile[i].img;
					}
					g2.drawImage(img, x*gp.getTileSize(),y*gp.getTileSize(),gp.getTileSize(),gp.getTileSize(),null);
				}

			}
		}
	}
}

