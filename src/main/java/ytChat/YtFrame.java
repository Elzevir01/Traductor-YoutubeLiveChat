package ytChat;


import javax.swing.JFrame;
import javax.swing.JPanel;

import servicios.Aws;
import servicios.YoutubeChat;
import ytChat.RenderList;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JTextPane;
import javax.swing.ListModel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.SwingConstants;
import javax.swing.JList;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class YtFrame extends JFrame {
	public JPanel contentPane;
	public JTextField txtLink ;
	YoutubeChat ytc = new YoutubeChat();
	public Thread ytcT= null;
	Aws aws = new Aws();
	public RenderList rl = new RenderList();
	UiIdioma uii = new UiIdioma();
	Thread awsT =null;
	public String[] items;
	private ButtonGroup bg = new ButtonGroup();
	public static JTextPane txtpnTxptraduccion;
	public static JRadioButton rdbTraducirSeleccion, rdbTraducirTodo;
	public static JButton btnIniciar, btnDetener;
	public static JComboBox cmbIdiomaOrigen, cmbIdiomaSalida;
	public static volatile boolean estadoHilo;
	public boolean autoScroll = false;
	public static JScrollBar vertical;
	
	public static JComboBox cmbUIIdioma, cmbServidor;
	public static JLabel lblUIIdioma ,lblYoutubeLink, lblServidor, lblIdiomaOrigen, lblIdiomaTraduccion ;
	//public static DefaultListModel modeloLista = new DefaultListModel();
	
	public static JList<String> listLiveChat;
	public static DefaultListModel<String> modeloLista = new DefaultListModel<String>();;
	
	
	
	///////////////////////////getSelectedItem()   //// getSelectedIndex()///	
	public YtFrame() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		setMinimumSize(new Dimension(30, 30));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 739, 394);
		JPanel panelSuperior = new JPanel();
		getContentPane().add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new BorderLayout(0, 0));
		
		JPanel panelServidor = new JPanel();
		panelSuperior.add(panelServidor, BorderLayout.NORTH);
		
		lblServidor = new JLabel("Servidor:");
		panelServidor.add(lblServidor);
		
		cmbServidor = new JComboBox();
		cmbServidor.setModel(new DefaultComboBoxModel(new String[] {"Norte America", "Europa", "Sur America", "Asia"}));
		panelServidor.add(cmbServidor);
		
		lblUIIdioma = new JLabel("UI Idioma");
		panelServidor.add(lblUIIdioma);
		
		cmbUIIdioma = new JComboBox();
		//////////////////////////////////////////////////////////////////////
		cmbUIIdioma.addItemListener(new ItemListener() {
			  public void itemStateChanged(ItemEvent itemEvent) {
				  uii.seleccionIdioma();
				  }
				});
		cmbUIIdioma.setModel(new DefaultComboBoxModel(new String[] {"Español", "Ingles", "Japones", "Chino"}));
		panelServidor.add(cmbUIIdioma);
		
		JPanel panelLink = new JPanel();
		panelSuperior.add(panelLink, BorderLayout.CENTER);
		panelLink.setLayout(new BoxLayout(panelLink, BoxLayout.X_AXIS));
		
		lblYoutubeLink = new JLabel("Youtube Link:");
		panelLink.add(lblYoutubeLink);
		
		txtLink = new JTextField();
		txtLink.setHorizontalAlignment(SwingConstants.LEFT);
		txtLink.setText("https://www.youtube.com/watch?v=gcHA2v3uanA");/////////////////////////////////////////////////////////////////////
		panelLink.add(txtLink);
		txtLink.setColumns(10);
		
		JPanel panelOpciones = new JPanel();
		panelSuperior.add(panelOpciones, BorderLayout.SOUTH);
		panelOpciones.setLayout(new BoxLayout(panelOpciones, BoxLayout.X_AXIS));
		
		JPanel panelModo = new JPanel();
		panelOpciones.add(panelModo);
		
		rdbTraducirSeleccion = new JRadioButton("Chat Original");
		rdbTraducirSeleccion.setSelected(true);
		panelModo.add(rdbTraducirSeleccion);
		
		rdbTraducirTodo = new JRadioButton("Chat Traducido(lento)");
		panelModo.add(rdbTraducirTodo);
		
		bg.add(rdbTraducirSeleccion);
		bg.add(rdbTraducirTodo);
		
		JPanel panelIdioma = new JPanel();
		panelOpciones.add(panelIdioma);
		panelIdioma.setLayout(new BoxLayout(panelIdioma, BoxLayout.X_AXIS));
		
		lblIdiomaOrigen = new JLabel("Idioma Origen:");
		panelIdioma.add(lblIdiomaOrigen);
		
		cmbIdiomaOrigen = new JComboBox();
		cmbIdiomaOrigen.setModel(new DefaultComboBoxModel(new String[] {"Auto"}));
		panelIdioma.add(cmbIdiomaOrigen);
		
		lblIdiomaTraduccion = new JLabel("Idioma Traduccion:");
		panelIdioma.add(lblIdiomaTraduccion);
		
		cmbIdiomaSalida = new JComboBox();
		cmbIdiomaSalida.setModel(new DefaultComboBoxModel(new String[] {"Español", "Ingles", "Japones", "Chino"}));
		panelIdioma.add(cmbIdiomaSalida);
		
		JPanel panelInferior = new JPanel();
		getContentPane().add(panelInferior, BorderLayout.SOUTH);
		panelInferior.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBotones = new JPanel();
		panelInferior.add(panelBotones, BorderLayout.SOUTH);
		panelBotones.setLayout(new GridLayout(0, 2, 0, 0));
		
		btnIniciar = new JButton("Iniciar");
		panelBotones.add(btnIniciar);
		
		btnDetener = new JButton("Detener");
		
		panelBotones.add(btnDetener);
		
		JPanel panelTraduccion = new JPanel();
		panelInferior.add(panelTraduccion, BorderLayout.NORTH);
		panelTraduccion.setLayout(new BoxLayout(panelTraduccion, BoxLayout.X_AXIS));
		
		txtpnTxptraduccion = new JTextPane();
		txtpnTxptraduccion.setDisabledTextColor(Color.BLACK);
		panelTraduccion.add(txtpnTxptraduccion);
		
		JPanel panelCentral = new JPanel();
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.X_AXIS));
		
		JPanel panelChat = new JPanel();
		panelCentral.add(panelChat);
		panelChat.setLayout(new CardLayout(0, 0));
		
		listLiveChat = new JList<String>(modeloLista);
		panelChat.add(listLiveChat, "name_40278394319370");
		listLiveChat.setModel(modeloLista);
		//////////////////////////////RENDER LIST///////////////////////
		listLiveChat.setCellRenderer(new RenderList2());
		
		JScrollPane scrollJlistC = new JScrollPane(listLiveChat);
		panelChat.add(scrollJlistC, "name_38499319947502");	
		vertical = scrollJlistC.getVerticalScrollBar();
		

		scrollJlistC.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
	        public void adjustmentValueChanged(AdjustmentEvent e) {  
	        	if(autoScroll() ==true) {
	        	
	            e.getAdjustable().setValue(e.getAdjustable().getMaximum()); 
	        	}
	        	
	        	
	        }
	    });
		
		////////////////////////////////////
		//// METODOS/////
		
		/// Click Iniciar ///
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					 habilitar(false);
					 estadoHilo=true;
					 modeloLista.removeAllElements();
					 //modeloLista;
					 Runnable ytc = new YoutubeChat(txtLink.getText().replace("https://www.youtube.com/watch?v=", ""));
					 ytcT = new Thread(ytc);
					 ytcT.start();
			}
		});
		
		/// Click Detener ///
		btnDetener.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					estadoHilo=false;
					habilitar(true);
					ytcT.stop();
					modeloLista.clear();
					txtpnTxptraduccion.setText("");
			}
		});
		/// Click en item de Lista ///
		listLiveChat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String tt = listLiveChat.getSelectedValue().toString();
				aws = new Aws(tt);
				Thread awsT = new Thread(aws);		 
				awsT.start();

			}
		});
		
		
	}
	public void habilitar(boolean x){
		txtLink.setEnabled(x);
		txtpnTxptraduccion.setEnabled(x);
		rdbTraducirSeleccion.setEnabled(x);
		rdbTraducirSeleccion.setEnabled(x);
		btnIniciar.setEnabled(x);
		btnDetener.setEnabled(!x);
	}
	
	public boolean autoScroll() {
		int lastIndex = listLiveChat.getModel().getSize() - 1;
		int index = listLiveChat.getLastVisibleIndex();
		if ( index == lastIndex-1 | index == lastIndex-2 | index == lastIndex-3 | index == lastIndex-4 | index == -1) {
			
			autoScroll = true;
	    }else {
	    	autoScroll=false;
	    }
		//System.out.println(listLiveChat.getLastVisibleIndex()+" "+lastIndex);
		return autoScroll;
	}
	 /*public void addNewListItem(URL imageUrl, String text) {
	        modeloLista.addElement(text + ", " + imageUrl.toString());
	        listLiveChat.setModel(modeloLista);
	    }*/
	
}


