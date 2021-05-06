import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SampleExperiment extends Experiment {
	
	private String target;
	private int amount;
	private String where; //??????
	
	public SampleExperiment(String Name,String priority, String ExperimentID, String complete, String Description) {
		super(Name, priority, ExperimentID, complete, Description);
	}
	
	public void setTarget(String target) {this.target = target;}
	public String getTarget( ) {return target;}
	public void setAmount(int amount) {this.amount = amount;}
	public int getAmount() {return this.amount;}
	public String getWhere() {return where;}
	public void setWhere(String where) {this.where = where;}

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
		processedJSON.put("experiment_type", "Sample");

		JSONArray commandArray = new JSONArray();

		JSONObject collectCommand = new JSONObject();
		collectCommand.put("command", "C19");
		commandArray.add(collectCommand);

		JSONObject collectDataCommand = new JSONObject();
		collectDataCommand.put("command", "C16");
		collectDataCommand.put("param", "HGPC-1");
		commandArray.add(collectDataCommand);

		JSONObject ejectCommand = new JSONObject();
		ejectCommand.put("command", "C5");
		commandArray.add(ejectCommand);

		processedJSON.put("experiment_commands", commandArray);

		return processedJSON;
	}
}
