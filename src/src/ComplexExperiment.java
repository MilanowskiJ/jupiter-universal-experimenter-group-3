import org.json.simple.JSONObject;

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
	public void setTarget(String target){this.target = target;}
	public void setSupplyItem(String supplyItem){this.supplyItem = supplyItem;}
	public void setQuantity(String quantity){this.quantity = quantity;}
	public void setWhatToDo(String whatToDo){this.whatToDo = whatToDo;}
	@Override
	public void validate() {

	}

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

	public JSONObject process() {
		return null;
	}
}
