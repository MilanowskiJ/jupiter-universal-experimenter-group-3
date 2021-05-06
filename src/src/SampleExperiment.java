import java.sql.ResultSet;
import java.sql.SQLException;

public class SampleExperiment extends Experiment {

	private String target;
	private String amount;
	private String where; //??????
	
	public SampleExperiment(String Name,String priority, String ExperimentID, String complete, String Description) {
		super(Name, priority, ExperimentID, complete, Description);
	}

	public SampleExperiment(String whatToSample, String howMuchToSample, String whereToSample, String experimentID){
		super(experimentID, "M", experimentID, "F", "N/A");
		this.target = whatToSample;
		this.amount = howMuchToSample;
		this.where = whereToSample;
	}
	
	public void setTarget(String target) {this.target = target;}
	public String getTarget( ) {return target;}
	public void setAmount(String amount) {this.amount = amount;}
	public String getAmount() {return this.amount;}
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

	@Override
	public String toString() {
		return this.target + this.amount + this.where + super.ExperimentID;
	}
}
