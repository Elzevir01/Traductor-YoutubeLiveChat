package rendimiento;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ConvertirImage {
	 BufferedImage img = null;
	 
	public ImageIcon getImage(String imageURL) {
		
		 try {
             URL url = new URL(imageURL);
             img = ImageIO.read(url);
         } catch (IOException e) {
             e.printStackTrace();
         }
		  Image scaledImage = img.getScaledInstance(25, 25, Image.SCALE_DEFAULT);//Image.SCALE_SMOOTH
          // Crear un ImageIcon a partir de la imagen escalada
          ImageIcon icon = new ImageIcon(scaledImage);
          return icon;
	}
}
