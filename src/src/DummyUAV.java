import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DummyUAV {
	
	private static String filepath = "src/Data/JIU-return-status.json";
	
	public static void main(String[] args) {
		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();
			System.out.println(ip.toString());
			Socket socket = new Socket(ip,1024);
			OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);
			
			JSONParser jsonParser = new JSONParser();
			
			FileReader reader = new FileReader(filepath);
			Object obj;
			obj = jsonParser.parse(reader);
			JSONObject returnStatus = (JSONObject) obj;
			out.write(returnStatus.toString());
			
		
		
			out.close();
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
