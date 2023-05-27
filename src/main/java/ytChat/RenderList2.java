package ytChat;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import rendimiento.ConvertirImage;
import rendimiento.ModeloRenderList;

public class RenderList2 extends DefaultListCellRenderer{
	  private JPanel panel;
      //private JLabel label;
      private ImageIcon icon ;
      private DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
      ConvertirImage ci = new ConvertirImage();
      ModeloRenderList mr = new ModeloRenderList();
      
      public void ImageAndTextListCellRenderer() {
         /* panel = new JPanel();
          panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));*/
         // label = new JLabel();
         // icon = new ImageIcon();
         /* panel.add(new JLabel(icon));
          panel.add(Box.createRigidArea(new Dimension(5, 0)));
          panel.add(label);*/
      }
      
      @Override
      public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    	  ImageAndTextListCellRenderer();
    	  
          	//Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
          
    	  	//JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	  	String text = value.toString();
    	    String imageUrl = text.split(":::")[0].trim(); // obtener la URL de la imagen
    	    String texto = text.split(":::")[1].trim(); // obtener el texto a mostrar
    	    JLabel lblModelo = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected,
                    cellHasFocus);
    	    
    	    //lblReglon = mr.modeloLabel(indx, text);
    	    //fondo de color segun si el reglon es par o impar
    		if (esPar(index)) {
    				 lblModelo.setBackground(Color.DARK_GRAY);
    	         } else {
    	        	 lblModelo.setBackground(Color.BLACK);
    	    }
             /// color de texto segun el tipo de usuario
    		 if(texto.contains("[M]"))
    				 lblModelo.setForeground(Color.green.brighter());
    			 else if(texto.contains("[MOD]")){
    				 lblModelo.setForeground(Color.blue.brighter());
    			 }else {
    				 lblModelo.setForeground(Color.WHITE);
    		}
          // Set the text label
    	  lblModelo.setIcon(ci.getImage(imageUrl));
          lblModelo.setText(texto);
         // panel.add(label);
         // System.out.println(index);
          // Return the label as the cell renderer
        
          return lblModelo;
      }

    //si index es par
  	public static boolean esPar(int numero) {
          return numero % 2 == 0;
      }
	 
}
