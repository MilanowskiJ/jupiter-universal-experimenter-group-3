import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.simple.parser.JSONParser;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CommunicationSubsystem extends Thread {
	private ServerSocket serverSocket;
	private int port;
	boolean running = false;
	
	public CommunicationSubsystem(int port) {
		this.port = port;
	}
	
	public void stopServer() {
		this.running = false;
		this.interrupt();
	}
	
	public void startServer() {
		try {
			serverSocket = new ServerSocket(port);
			this.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		running = true;
		while(running) {
			try {
				System.out.println("Listening for a connection");
				
				Socket socket = serverSocket.accept();
				
				handleMessages(socket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void handleMessages(Socket socket) {
		// TODO Auto-generated method stub
		System.out.println("Connection recieved");
		try
        {
            System.out.println( "Received a connection" );

            // Get input and output streams
            BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );

            // Write out our header to the client
            System.out.println( "listening for Json file" );
            // Echo lines back to the client until the client closes the connection or we receive an empty line
            String line = in.readLine();
            JSONParser parser = new JSONParser();
            JSONObject report = (JSONObject) parser.parse(line);
            
            System.out.println("file received: " + report.toString());
            
            // Close our connection
            in.close();
            socket.close();

            System.out.println( "Connection closed" );
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
	}
	
	public static void main(String[] args) {
		int port = 1024;
		CommunicationSubsystem server = new CommunicationSubsystem(port);
		server.startServer();
		
		try
        {
            Thread.sleep( 60000 );
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }

        server.stopServer();
	}
}
