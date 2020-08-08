package cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClienteForm {

	private JFrame frmChatServersocket;
	private JTextField Servidor;
	private JTextField Mensaje;
	private ClienteChat Cliente;
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteForm window = new ClienteForm();
					window.frmChatServersocket.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClienteForm() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChatServersocket = new JFrame();
		frmChatServersocket.setTitle("Chat Server (Socket)");
		frmChatServersocket.setBounds(100, 100, 471, 397);
		frmChatServersocket.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChatServersocket.getContentPane().setLayout(null);
		
		JTextPane TextChat = new JTextPane();
		TextChat.setBounds(10, 41, 435, 242);
		frmChatServersocket.getContentPane().add(TextChat);
		
		Servidor = new JTextField();
		Servidor.setBounds(77, 11, 140, 20);
		frmChatServersocket.getContentPane().add(Servidor);
		Servidor.setColumns(10);
		
		JLabel nkjasdnfas = new JLabel("Servidor:");
		nkjasdnfas.setBounds(20, 14, 57, 14);
		frmChatServersocket.getContentPane().add(nkjasdnfas);
		
		JButton btnNewButton = new JButton("Conectar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//creo un Cliente nuevo de chat, parametros: Servidor, Puerto, Panel 
				Cliente = new ClienteChat(Servidor.getText(), 1234, TextChat);
			}
		});
		btnNewButton.setBounds(227, 10, 89, 23);
		frmChatServersocket.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Desconectar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Cliente.desconectar();
			}
		});
		btnNewButton_1.setBounds(326, 10, 119, 23);
		frmChatServersocket.getContentPane().add(btnNewButton_1);
		
		Mensaje = new JTextField();
		Mensaje.setBounds(10, 300, 226, 48);
		frmChatServersocket.getContentPane().add(Mensaje);
		Mensaje.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Enviar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// rescata el texto y lo envia
				String elTexto = Mensaje.getText();
				if (!elTexto.isEmpty())
				{	
					Cliente.enviar(elTexto);
					Mensaje.setText("");
				}
			}
		});
		btnNewButton_2.setBounds(264, 309, 89, 29);
		frmChatServersocket.getContentPane().add(btnNewButton_2);
	}
}
