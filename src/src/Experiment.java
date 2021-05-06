import java.util.ArrayList;

public abstract class Experiment implements DatabaseModel, Processable{
	
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
		
	public void validate() {
		
	}
	
	public boolean isDoable(ArrayList<Capability> capabilities, ArrayList<Supply> supplies) {
		return false;
	}
}
