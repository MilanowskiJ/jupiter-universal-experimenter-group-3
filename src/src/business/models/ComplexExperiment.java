package business.models;

import business.models.Experiment;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComplexExperiment extends Experiment {
	private ArrayList<Processable> experimentSteps;

	public ComplexExperiment(String Name, String priority, String ExperimentID, String complete, String Description) {
		super(Name, priority, ExperimentID, complete, Description);
		experimentSteps = new ArrayList<>();
	}

	public void addExperimentStep(Processable processable){
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
		return null;
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
		return null;
	}
}
