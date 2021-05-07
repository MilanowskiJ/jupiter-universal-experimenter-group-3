package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Stack;

import business.CommunicationObserver;
import business.Observer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CommunicationSubsystem extends Thread {
	private ServerSocket serverSocket;
	private int port;
	boolean running = false;
	private ArrayList<Observer> observers;
	private Stack<JSONObject> files;
	private ArrayList<JSONObject> payloads;
	
	public CommunicationSubsystem(int port) {
		this.port = port;
		observers = new ArrayList<Observer>();
		this.files = new Stack<JSONObject>();
		this.payloads = new ArrayList<JSONObject>();
	}
	
	public void stopServer() {
		this.running = false;
		this.interrupt();
	}
	
	public void startServer() {
		try {
			serverSocket = new ServerSocket(port);
			InetAddress ip = InetAddress.getLocalHost();
			System.out.println(ip.toString());
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
//				System.out.println("Listening for a connection");
				
				Socket socket = serverSocket.accept();
				
				handleMessages(socket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void handleMessages(Socket socket) {
		// TODO Auto-generated method stub
//		System.out.println("Connection recieved");
		try
        {
//            System.out.println( "Received a connection" );

            // Get input and output streams
            BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );

            // Write out our header to the client
//            System.out.println( "listening for Json file" );
            // Echo lines back to the client until the client closes the connection or we receive an empty line
            String line = in.readLine();
			JSONObject report = null;
			try {
				report = new JSONObject(line);
			}catch (JSONException err){
			}
            
//            System.out.println("file received: " + report.toString());
            this.files.add(report);
            notifyAllObservers();
            // Close our connection
            in.close();
            socket.close();

//            System.out.println( "Connection closed" );
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
	}
	
	private void notifyAllObservers() {
		// TODO Auto-generated method stub
		for (Observer obs : observers) {
			obs.update();
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

	public void attach(CommunicationObserver communicationObserver) {
		// TODO Auto-generated method stub
		this.observers.add(communicationObserver);
	}

	public synchronized void addPayload(JSONObject payload) {
		files.add(processPayload(payload));
	}

	public synchronized void sendPayloads() {

	}

	private synchronized JSONObject processPayload(JSONObject payload){
		JSONObject returnStatus = new JSONObject();
		JSONObject returnStatusBody = new JSONObject();
		JSONArray inventoryArray = new JSONArray();
		JSONArray capabilitiesArray = new JSONArray();
		JSONArray experimentStatusArray = new JSONArray();

		JSONObject temp = payload.getJSONObject("payload");
		for (String currentExperiment : temp.keySet()){
			JSONObject newObject = new JSONObject();
			String experimentID = temp.getJSONObject(currentExperiment).getString("experiment_id");
			newObject.put(experimentID, "success");
		}

		returnStatusBody.put("inventory", inventoryArray);
		returnStatusBody.put("capabilities", capabilitiesArray);
		returnStatusBody.put("experiment_status", experimentStatusArray);

		returnStatus.put("return-status", returnStatus);

		return returnStatus;
	}
	
	public Stack<JSONObject> getFiles() {
		return this.files;
	}
}
