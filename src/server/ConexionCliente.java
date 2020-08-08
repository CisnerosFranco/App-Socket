package server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JTextPane;

public class ConexionCliente extends Thread{

	
	private Servidor servidor;
	private Socket socket;
	private DataOutputStream clienteOut;
	private DataInputStream clienteIn;
	private JTextPane memo;
	
	
	public ConexionCliente(Servidor servidor, JTextPane controlMemo, Socket socket) {
		
		this.servidor = servidor;
		this.socket =socket;
		this.memo = controlMemo;
	}
	
	public void run() {
		
		try {
			String anteriores = memo.getText(); //optenemos los datos previos
			memo.setText( anteriores + "\n"+ this.socket.getInetAddress() + " conectado!" ); // luego lo añadimos informando que el nuevo se conecto
			
			this.clienteOut = new DataOutputStream(socket.getOutputStream());
			this.clienteIn = new DataInputStream( new BufferedInputStream(socket.getInputStream()));
			
			
			while(true) {
				
				try {
					
					String linea = clienteIn.readUTF(); //optenemos el nuevo mensaje que se recibe
					servidor.mensajeRecibido(socket.getInetAddress().toString(), linea); //al servidor le enviamos la ip del cliente y el mensaje
				
				}
				catch(Exception e) {
					
				}
				
			}
		}
		catch(Exception e) {
			
		}
	}

	public void enviar(String mensaje) {
		
		try {
			
			clienteOut.writeUTF(mensaje);
		}
		catch(Exception ex) {
			
		}
		
	}

	
	
}
