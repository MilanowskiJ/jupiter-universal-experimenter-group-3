package business.models;

import business.models.Experiment;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComplexExperiment extends Experiment {
	private String target;
	private String supplyItem;
	private String quantity;
	private String whatToDo;
	public ComplexExperiment(String Name, String priority, String ExperimentID, String complete, String Description) {
		super(Name, priority, ExperimentID, complete, Description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getType() {
		return "Complex";
	}


	public void setTarget(String target){this.target = target;}
	public void setSupplyItem(String supplyItem){this.supplyItem = supplyItem;}
	public void setQuantity(String quantity){this.quantity = quantity;}
	public void setWhatToDo(String whatToDo){this.whatToDo = whatToDo;}
	@Override
	public void validate() {

	}

	@Override
	public String getQuery() {
		return "SELECT ExperimentName, Priority, Complete, ExperimentID, ExperimentType, Description from Experiment";
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

	@Override
	public void processResult(ResultSet result) throws SQLException {
	}

	public JSONObject process() {
		return null;
	}
}
