package ytChat;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import servicios.YoutubeChat;
import servicios.Aws;


import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class YtFrame extends JFrame {

	public JPanel contentPane;
	public JTextField txtLink;
	public static JTextPane txtpnTxptraduccion;
	//Array array =new Array;
	public static DefaultListModel modeloLista = new DefaultListModel();
	//String[][] listae;
	
	YoutubeChat ytc = new YoutubeChat();
	Thread ytcT= null;
	Aws aws = new Aws();
	Thread awsT =null;
	
	public YtFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(20, 10));
		contentPane.setMinimumSize(new Dimension(20, 10));
		contentPane.setMaximumSize(new Dimension(60000, 32767));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JLabel lblYoutubelink = new JLabel("YoutubeLInk");
		panel.add(lblYoutubelink);
		
		txtLink = new JTextField();
		txtLink.setText("H7zdmrNuFh0");
		
		txtLink.setColumns(10);
		panel.add(txtLink);
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		
		JList listC = new JList();
		listC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String tt = listC.getSelectedValue().toString();
				//System.out.println(tt);
				aws = new Aws(tt);
				Thread awsT = new Thread(aws);		 
				awsT.start();
				//txtpnTxptraduccion.setText(tt);
				//System.out.println("texto");
			}
		});
		listC.setVisibleRowCount(50);
		panel_1.add(listC);
		listC.setModel(modeloLista);
		JScrollPane scrollJlistC = new JScrollPane(listC);
		panel_1.add(scrollJlistC);
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.EAST);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.WEST);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4, BorderLayout.SOUTH);
		
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					 modeloLista.removeAllElements();
					 Runnable ytc = new YoutubeChat(txtLink.getText());
					 Thread ytcT = new Thread(ytc);
					 ytcT.start();
			}
		});
		
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		panel_4.add(btnIniciar);
		
		txtpnTxptraduccion = new JTextPane();
		panel_4.add(txtpnTxptraduccion);
		
		
		
	}}
		
	
