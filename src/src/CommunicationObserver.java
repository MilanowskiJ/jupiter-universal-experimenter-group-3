import java.util.ArrayList;
import java.util.LinkedList;

import org.json.JSONObject;

public class CommunicationObserver extends Observer {
	
	private ArrayList<JSONObject> files;
	private int index;
	
	public CommunicationObserver(CommunicationSubsystem CS) {
		this.CS = CS;
		this.CS.attach(this);
		index = 0;
		files = new ArrayList<JSONObject>();
	}

	@Override
	public synchronized void update() {
		// TODO Auto-generated method stub
		files = this.CS.getFiles();
		index = 0;
	}
	
	public boolean hasNext() {
		return index < files.size();
	}
	
	public JSONObject next() {
		return files.get(index++);
	}
}
