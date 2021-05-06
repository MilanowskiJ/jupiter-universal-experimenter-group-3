import org.json.simple.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComplexExperiment extends Experiment {

	public ComplexExperiment(String Name, String priority, String ExperimentID, String complete, String Description) {
		super(Name, priority, ExperimentID, complete, Description);
		// TODO Auto-generated constructor stub
	}

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

	@Override
	public JSONObject process() {
		return null;
	}
}
