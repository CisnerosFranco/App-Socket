package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

import javax.swing.JTextPane;

public class Servidor extends Thread {

	private ServerSocket server;
	private LinkedList<ConexionCliente> conexiones;//puede haber mas de un cliente conectado
	private JTextPane memo;
	
	public Servidor(JTextPane controlMemo, int puerto) {
		
		try
		{
			server = new ServerSocket(puerto);
			conexiones = new LinkedList<ConexionCliente> ();
			memo = controlMemo;
		}
		catch(IOException ie)
		{
				System.out.println("Hubo un error, No se pudo habrir el socket del servidor.");
				System.exit(1);
		}
		
		try
		{
			//retorna la direccion IP remota a la cual esta conectado el Socket
			InetAddress addr = InetAddress.getLocalHost();
			
			//Get IP address
			String ipAddr = addr.getHostAddress().toString();
			memo.setText(memo.getText() + "Direccion IP Local: "+ ipAddr);
			
			//Get hostname
			String hostname = addr.getHostName();
			memo.setText(memo.getText() + "\nHostName: " + hostname);
		}
		catch(UnknownHostException e)
		{
			e.printStackTrace();
		}
	}

	
	public void run() {
		
		while(true) {
			try {
				
				Socket cliente = server.accept();  //le paso mis propiedades; servidor, memo cliente      
				ConexionCliente conexion = new ConexionCliente(this, memo, cliente);
				conexiones.add(conexion);
				conexion.start();
			}
			catch(IOException ie) {
				break;
			}
		}
	}


	public void mensajeRecibido(String ip, String mensaje) {
		for(ConexionCliente cc: conexiones )
			cc.enviar(ip + ": " + mensaje);
		
		String anteriores = memo.getText();
		memo.setText(anteriores + "\n" + ip + ": " + mensaje); //agregamos el nuevo mensaje al Chat
		
	}
	
	public void detener() {
		
		try {
			server.close();
		}
		catch(Exception e) {
			
		}
	}
	
}
