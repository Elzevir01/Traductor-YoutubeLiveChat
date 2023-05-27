package rendimiento;

import java.awt.Color;

import javax.swing.JLabel;

public class ModeloRenderList {
	
	
	public JLabel modeloLabel(int index, String text) {
		JLabel lblModelo = new JLabel();
		System.out.println(index);
		// formato de color label
  	  	//fondo de color segun si el reglon es par o impar
		if (esPar(index)) {
				 lblModelo.setBackground(Color.DARK_GRAY);
	         } else {
	        	 lblModelo.setBackground(Color.BLACK);
	    }
         /// color de texto segun el tipo de usuario
		 if(text.contains("[M]"))
				 lblModelo.setForeground(Color.green.brighter());
			 else if(text.contains("[MOD]")){
				 lblModelo.setForeground(Color.blue.brighter());
			 }else {
				 lblModelo.setForeground(Color.WHITE);
		}
		 
		return lblModelo;
	}
	//si index es par
	public static boolean esPar(int numero) {
        return numero % 2 == 0;
    }
}
