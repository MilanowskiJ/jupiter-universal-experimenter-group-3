package business;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import data.CommunicationSubsystem;
import org.json.JSONObject;

public class CommunicationObserver extends Observer {
	
	private Stack<JSONObject> files;
	
	public CommunicationObserver(CommunicationSubsystem CS) {
		this.CS = CS;
		this.CS.attach(this);
		files = new Stack<JSONObject>();
	}

	@Override
	public synchronized void update() {
		// TODO Auto-generated method stub
		files = this.CS.getFiles();
	}
	
	public boolean hasNext() {
		return !files.empty();
	}
	
	public JSONObject next() {
		return files.pop();
	}
}
