package business;

import business.Capability;
import business.DatabaseModel;

import java.util.ArrayList;


public abstract class Experiment implements DatabaseModel, Processable {

	protected String name;

	protected String priority;
	protected String experimentID;
	protected String complete;
	protected String description;
	public Experiment(String Name, String priority, String ExperimentID, String complete, String Description) {
		this.name = Name;
		this.priority = priority;
		this.experimentID = ExperimentID;
		this.complete = complete;
		this.description = Description;
	}

	public String getPriority() { return priority; }
	public void setPriority(String priority) { this.priority = priority; }
	public String getExperimentID() { return experimentID; }
	public void setExperimentID(String experimentID) { this.experimentID = experimentID; }
	public String getComplete() { return complete; }
	public void setComplete(String complete) { this.complete = complete; }
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }

	public void validate() {
		
	}
	
	public boolean isDoable(ArrayList<Capability> capabilities, ArrayList<Supply> supplies) {
		return false;
	}
}
