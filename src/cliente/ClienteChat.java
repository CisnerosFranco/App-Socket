package cliente;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JTextPane;

public class ClienteChat {

	private Socket cliente; 			//Socket para poder realizar la comunicacion
	private DataOutputStream streamOut; //
	private DataInputStream streamIn;   //
	private ChatThread thread;
	
	
	public ClienteChat(String host, int puerto, JTextPane textChat) {
		try {
			cliente = new Socket(host, puerto);
			streamOut = new DataOutputStream(cliente.getOutputStream()); 
			streamIn = new DataInputStream(new BufferedInputStream(cliente.getInputStream()));
		
			thread = new ChatThread(streamIn, textChat);//
			thread.start();
		}
		catch(Exception ex)
		{
			textChat.setText(textChat.getText() + "!\n\nFallo la conexion: " + ex.getMessage());
		}
		
	}
	
	public boolean enviar(String texto)
	{
		try
		{
			streamOut.writeUTF(" dice: " + texto);
		}
		catch(Exception ex)
		{
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * Se desconecta al cliente del chat y del servidor 
	 * @return
	 */
	public boolean desconectar(){
		try
		{
			streamOut.writeUTF(cliente.getInetAddress().getHostName() + " desconectado!");
			cliente.close();
		}
		catch(Exception ex)
		{
			return false;
		}
		
		return true;
	}

}
