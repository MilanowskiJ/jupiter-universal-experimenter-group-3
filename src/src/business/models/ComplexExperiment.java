package business.models;

import business.models.Experiment;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComplexExperiment extends Experiment {
	private ArrayList<ExperimentStepWrapper> experimentSteps;

	public ComplexExperiment(String Name, String priority, String ExperimentID, String complete, String Description) {
		super(Name, priority, ExperimentID, complete, Description);
		experimentSteps = new ArrayList<>();
	}

	public void addExperimentStep(ExperimentStepWrapper processable){
		experimentSteps.add(processable);
	}

	@Override
	public String getType() {
		return "Complex";
	}

	@Override
	public void validate() {

	}

	@Override
	public String addQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("INSERT INTO Experiment (ExperimentName, Priority, Complete, ExperimentID, ExperimentType, Description) " +
						"VALUES ('%s', '%s', '%s', '%s', '%s', '%s');",
				super.getName(),
				super.getPriority(),
				super.getComplete(),
				super.getExperimentID(),
				this.getType(),
				super.getDescription()));
		for(int i = 0; i < experimentSteps.size(); i++){
			sb.append(String.format("INSERT INTO ComplexExperiment (ExperimentID, CommandID, MacroName, ParamValues, Step) " +
					"VALUES ('%s', %s, '%s', '%d');",
					this.getExperimentID(),
					experimentSteps.get(i).getIDProcessor(),
					experimentSteps.get(i).getParamValues(),
					i
					));
		}
		return sb.toString();
	}

	@Override
	public String updateQuery() {
		return String.format("UPDATE Experiment SET ExperimentName = '%s', Priority = '%s', " +
						"Complete = '%s', ExperimentType = '%s', Description = '%s' WHERE ExperimentID = '%s';",
				super.name,
				super.priority,
				super.complete,
				"Complex",
				super.description,
				super.experimentID);
	}

	@Override
	public String deleteQuery() {
		return null;
	}

	@Override
	public String getDatabaseID() {
		return super.experimentID;
	}

	public JSONObject process() {
		JSONObject processedJSON = new JSONObject();
		processedJSON.put("experiment_id", super.experimentID);
		processedJSON.put("experiment_name", super.name);
		processedJSON.put("experiment_type", "Complex");

		JSONArray commandArray = new JSONArray();

		for(ExperimentStepWrapper currentStep: experimentSteps){
			for(JSONObject currentObject: currentStep.processStep()) {
				commandArray.put(currentObject);
			}
		}

		processedJSON.put("experiment_commands", commandArray);

		return processedJSON;
	}

	public interface ExperimentStepWrapper{
		public String getIDProcessor();
		public String getParamValues();
		public List<JSONObject> processStep();
	}
}
