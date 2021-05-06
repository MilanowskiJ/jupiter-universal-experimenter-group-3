package business;

import business.Experiment;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReagentExperiment extends Experiment {
	
	private String reagent;
	private String amount;
	private String sampleID;
	private String measurementsToTake;

	public ReagentExperiment(String Name, String priority, String ExperimentID, String complete, String Description) {
		super(Name, priority, ExperimentID, complete, Description);

	}


	public void setReagent(String reagent) {this.reagent = reagent;}
	public void setAmount(String amount) {this.amount = amount;}
	public void setSampleID(String sampleID) {this.sampleID = sampleID;}
	public void setMeasurements(String measurements) {this.measurementsToTake = measurements;}

	@Override
	public String getQuery() {
		return "SELECT ExperimentName, Priority, Complete, ExperimentID, ExperimentType, Description from business.Experiment";
	}

	@Override
	public String addQuery() {
		return null;
	}

	@Override
	public String updateQuery() {
		return null;
	}

	@Override
	public String deleteQuery() {
		return null;
	}

	@Override
	public String getDatabaseID() {
		return super.name;
	}


	@Override
	public void processResult(ResultSet result) throws SQLException {
	}

	@Override
	public JSONObject process() {
		JSONObject processedJSON = new JSONObject();
		processedJSON.put("experiment_id", super.experimentID);
		processedJSON.put("experiment_name", super.name);
		processedJSON.put("experiment_type", "Reagent");

		JSONArray commandArray = new JSONArray();

		JSONObject collectCommand = new JSONObject();
		collectCommand.put("command", "C19");
		commandArray.put(collectCommand);

		JSONObject flaskCommand = new JSONObject();
		flaskCommand.put("command", "C8");
		commandArray.put(flaskCommand);

		JSONObject addCommand = new JSONObject();
		addCommand.put("command", "C6");
		addCommand.put("param", "[]");
		commandArray.put(addCommand);

		JSONObject mixCommand = new JSONObject();
		mixCommand.put("command", "C4");
		commandArray.put(mixCommand);

		JSONObject collectDataCommand = new JSONObject();
		collectDataCommand.put("command", "C16");
		collectDataCommand.put("param", "HGPC-1");
		commandArray.put(collectDataCommand);

		JSONObject ejectCommand = new JSONObject();
		ejectCommand.put("command", "C7");
		commandArray.put(ejectCommand);

		processedJSON.put("experiment_commands", commandArray);

		return processedJSON;
	}
}
