package ytChat;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class RenderList implements ListCellRenderer<String>{
    private DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
    
	@Override
	public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
            boolean isSelected, boolean cellHasFocus) {
		JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected,
                cellHasFocus);
		 if (esPar(index)) {
			 renderer.setBackground(Color.DARK_GRAY);
	        } else {
	        	renderer.setBackground(Color.BLACK);
	        }
		renderer.setForeground(Color.WHITE);
		return renderer;
	}
	 public static boolean esPar(int numero) {
	        return numero % 2 == 0;
	    }
}
