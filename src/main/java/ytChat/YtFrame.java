package ytChat;


import javax.swing.JFrame;
import javax.swing.JPanel;

import servicios.Aws;
import servicios.YoutubeChat;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JTextPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.JList;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class YtFrame extends JFrame {
	public JPanel contentPane;
	public JTextField txtLink;
	YoutubeChat ytc = new YoutubeChat();
	public Thread ytcT= null;
	Aws aws = new Aws();
	Thread awsT =null;
	private ButtonGroup bg = new ButtonGroup();
	public static DefaultListModel modeloLista = new DefaultListModel();
	public static JTextPane txtpnTxptraduccion;
	public static JRadioButton rdbTraducirSeleccion;
	public static JRadioButton rdbTraducirTodo;
	public static JButton btnIniciar;
	public static JButton btnDetener;
	public static JComboBox cmbIdiomaOrigen;
	public static JComboBox cmbIdiomaSalida;
	public static volatile boolean estadoHilo;
	
	///////////////////////////getSelectedItem()   //// getSelectedIndex()///
	String url = "WFkprwV7m4Q";
	
	public YtFrame() {
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		setMinimumSize(new Dimension(30, 30));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 656, 394);
		JPanel panelSuperior = new JPanel();
		getContentPane().add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new BorderLayout(0, 0));
		
		JPanel panelLink = new JPanel();
		panelSuperior.add(panelLink, BorderLayout.NORTH);
		panelLink.setLayout(new BoxLayout(panelLink, BoxLayout.X_AXIS));
		
		JLabel lblYoutubeLink = new JLabel("Youtube Link:");
		panelLink.add(lblYoutubeLink);
		
		txtLink = new JTextField();
		txtLink.setHorizontalAlignment(SwingConstants.LEFT);
		panelLink.add(txtLink);
		txtLink.setColumns(10);
		//txtLink.setText(url);
		
		JPanel panelOpciones = new JPanel();
		panelSuperior.add(panelOpciones);
		panelOpciones.setLayout(new BoxLayout(panelOpciones, BoxLayout.X_AXIS));
		
		JPanel panelModo = new JPanel();
		panelOpciones.add(panelModo);
		
		rdbTraducirSeleccion = new JRadioButton("Traducir Seleccion");
		rdbTraducirSeleccion.setSelected(true);
		panelModo.add(rdbTraducirSeleccion);
		
		rdbTraducirTodo = new JRadioButton("Traducir Todo");
		panelModo.add(rdbTraducirTodo);
		
		bg.add(rdbTraducirSeleccion);
		bg.add(rdbTraducirTodo);
		
		JPanel panelIdioma = new JPanel();
		panelOpciones.add(panelIdioma);
		panelIdioma.setLayout(new BoxLayout(panelIdioma, BoxLayout.X_AXIS));
		
		JLabel lblIdiomaOrigen = new JLabel("Idioma Origen");
		panelIdioma.add(lblIdiomaOrigen);
		
		cmbIdiomaOrigen = new JComboBox();
		cmbIdiomaOrigen.setModel(new DefaultComboBoxModel(new String[] {"Ingles", "Japones", "Español"}));
		panelIdioma.add(cmbIdiomaOrigen);
		
		JLabel lblNewLabel = new JLabel("Idioma Salida");
		panelIdioma.add(lblNewLabel);
		
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
		//txtpnTxptraduccion.setEditable(false);
		panelTraduccion.add(txtpnTxptraduccion);
		
		JPanel panelCentral = new JPanel();
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.X_AXIS));
		
		JPanel panelChat = new JPanel();
		panelCentral.add(panelChat);
		panelChat.setLayout(new CardLayout(0, 0));
		
		JList listLiveChat = new JList();
		panelChat.add(listLiveChat, "name_40278394319370");
		listLiveChat.setModel(modeloLista);
		
		JScrollPane scrollJlistC = new JScrollPane(listLiveChat);
		panelChat.add(scrollJlistC, "name_38499319947502");
		//contentPane.add(panelChat, BorderLayout.CENTER);
		
		//// METODOS/////
		
		/// Click Iniciar ///
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					 habilitar(false);
					 estadoHilo=true;
					 modeloLista.removeAllElements();
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
	


}
