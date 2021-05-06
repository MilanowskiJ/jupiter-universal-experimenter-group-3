import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

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

	@Override
	public String getQuery() {
		return null;
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
		commandArray.add(collectCommand);

		JSONObject flaskCommand = new JSONObject();
		flaskCommand.put("command", "C8");
		commandArray.add(flaskCommand);

		JSONObject addCommand = new JSONObject();
		addCommand.put("command", "C6");
		addCommand.put("param", "[]");
		commandArray.add(addCommand);

		JSONObject mixCommand = new JSONObject();
		mixCommand.put("command", "C4");
		commandArray.add(mixCommand);

		JSONObject collectDataCommand = new JSONObject();
		collectDataCommand.put("command", "C16");
		collectDataCommand.put("param", "HGPC-1");
		commandArray.add(collectDataCommand);

		JSONObject ejectCommand = new JSONObject();
		ejectCommand.put("command", "C7");
		commandArray.add(ejectCommand);

		processedJSON.put("experiment_commands", commandArray);

		return processedJSON;
	}
}
