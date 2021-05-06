import java.util.ArrayList;

public abstract class Experiment implements DatabaseModel{
	
	private String Name;
	private String priority;
	private String ExperimentID;
	private String complete;
	private String Description;
	
	public Experiment(String Name, String priority, String ExperimentID, String complete, String Description) {
		this.Name = Name;
		this.priority = priority;
		this.ExperimentID = ExperimentID;
		this.complete = complete;
		this.Description = Description;
	}

	abstract public void validate();
	
	public boolean isDoable(ArrayList<Capability> capabilities, ArrayList<Supply> supplies) {
		return false;
	}
}
