package cliente;

import java.io.DataInputStream;

import javax.swing.JTextPane;

public class ChatThread extends Thread{

	private DataInputStream streamIn;
	private JTextPane textChat;
	
	
	public ChatThread(DataInputStream streamIn, JTextPane textChat) {
		
		this.streamIn = streamIn;
		this.textChat = textChat;
	}
	
	public void run() {
		
		
		this.textChat.setText("Thread cliente corriendo"); //Corremos el thread
		
		while(true) { // Esperamos a que nos llegue un mensaje
			
			try {
				
				String str = streamIn.readUTF(); //aqui recibimos el mensaje
				this.textChat.setText(this.textChat.getText() + "\n" + str); //Al mensaje recibido lo añadimos en el Panel de Chats
			
			}
			catch(Exception ex) {
		
			}
		
		}
	}
}
