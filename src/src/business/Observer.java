package business;

import data.CommunicationSubsystem;

public abstract class Observer {
	
	protected CommunicationSubsystem CS;
	
	public abstract void update();
}
