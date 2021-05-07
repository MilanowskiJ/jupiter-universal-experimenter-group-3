package business.models;

import business.models.Experiment;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SampleExperiment extends Experiment {
	
	private String target;
	private String amount;
	private String where; //??????
	
	public SampleExperiment(String Name,String priority, String ExperimentID, String complete, String Description) {
		super(Name, priority, ExperimentID, complete, Description);
	}



	public void setTarget(String target) {this.target = target;}
	public String getTarget( ) {return target;}
	public void setAmount(String amount) {this.amount = amount;}
	public String getAmount() {return this.amount;}
	public String getWhere() {return where;}
	public void setWhere(String where) {this.where = where;}

	@Override
	public String getQuery() {
		return "SELECT ExperimentName, Priority, Complete, ExperimentID, ExperimentType, Description from Experiment";
	}

	@Override
	public String addQuery() {
		return String.format("INSERT INTO Experiment (ExperimentName, Priority, Complete, ExperimentID, ExperimentType, Description) VALUES ('%s', '%s', '%s', '%s', '%s', '%s');" +
						"INSERT INTO SampleExperiment (ExperimentID, Target, Amount, Location) VALUES ('%s', '%s', '%s', '%s')",
				super.getName(),
				super.getPriority(),
				super.getComplete(),
				super.getExperimentID(),
				"Sample",
				super.getDescription(),
				super.getExperimentID(),
				this.target,
				this.amount,
				this.where);
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
		processedJSON.put("experiment_type", "business.models.Sample");

		JSONArray commandArray = new JSONArray();

		JSONObject collectCommand = new JSONObject();
		collectCommand.put("command", "C19");
		commandArray.put(collectCommand);

		JSONObject collectDataCommand = new JSONObject();
		collectDataCommand.put("command", "C16");
		collectDataCommand.put("param", "HGPC-1");
		commandArray.put(collectDataCommand);

		JSONObject ejectCommand = new JSONObject();
		ejectCommand.put("command", "C5");
		commandArray.put(ejectCommand);

		processedJSON.put("experiment_commands", commandArray);

		return processedJSON;
	}
}