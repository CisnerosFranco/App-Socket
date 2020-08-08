package server;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainForm {

	private JFrame frmServidor;
	private Servidor servidor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					window.frmServidor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainForm() {
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
			
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmServidor = new JFrame();
		frmServidor.setTitle("Servidor");
		frmServidor.setBounds(100, 100, 348, 419);
		frmServidor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServidor.getContentPane().setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(0, 0, 332, 325);
		frmServidor.getContentPane().add(textPane);
		
		JButton btnNewButton = new JButton("Iniciar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				servidor = new Servidor(textPane, 1234);
				servidor.start();
				textPane.setText(textPane.getText() + "\n\nServidor iniciado.");
			}
		});
		btnNewButton.setBounds(62, 336, 89, 34);
		frmServidor.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Detener");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				servidor.detener();
				servidor = null;
				textPane.setText(textPane.getText() + "\n\nServidor detenido.");
			}
		});
		btnNewButton_1.setBounds(190, 336, 89, 34);
		frmServidor.getContentPane().add(btnNewButton_1);
	}

}
