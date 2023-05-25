package vue;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class TitlePanel extends JPanel{
	@Override
    public void paintComponent(Graphics g) {
       super.paintComponent(g);
       
       try {
    	   BufferedImage img = ImageIO.read(getClass().getResourceAsStream("/assets/logo.png"));
    	   g.drawImage(img, 0,0,840,100,null);
       } catch (IOException e) {
    	   System.out.println("Ressource logo non");
       }
      
 
       
    }
}
