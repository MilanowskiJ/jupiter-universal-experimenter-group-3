import java.util.ArrayList;

<<<<<<< HEAD
public abstract class Experiment implements DatabaseModel{

	protected String Name;
	protected String priority;
	protected String ExperimentID;
	protected String complete;
	protected String Description;
	
	public Experiment(String Name, String priority, String ExperimentID, String complete, String Description) {
		this.name = Name;
		this.priority = priority;
		this.experimentID = ExperimentID;
		this.complete = complete;
		this.description = Description;
	}

	abstract public void validate();
	
	public boolean isDoable(ArrayList<Capability> capabilities, ArrayList<Supply> supplies) {
		return false;
	}
}
