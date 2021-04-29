
public class ReagentExperiment extends Experiment {
	
	private String reagent;
	private int amount;
	private String sampleID;

	public ReagentExperiment(String Name, String priority, String ExperimentID, String complete, String Description) {
		super(Name, priority, ExperimentID, complete, Description);
		
	}
	
	private void setReagent(String reagent) {this.reagent = reagent;}
	private void setAmount(int amount) {this.amount = amount;}
	private void setSampleID(String sampleID) {this.sampleID = sampleID;}

}
