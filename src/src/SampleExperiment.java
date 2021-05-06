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
	public void validate() {

	}
}
