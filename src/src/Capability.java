import java.sql.ResultSet;
import java.sql.SQLException;

public class Capability implements DatabaseModel{
	private String ID;
	private String type;
	private String name;
	private String description;
	private String status;

	public Capability(String ID, String type, String name, String description, String status) {
		this.ID = ID;
		this.type = type;
		this.name = name;
		this.description = description;
		this.status = status;
	}

	public String getID() {return this.ID;}

	public String getType() {return this.type;}
	public String getName() {return this.name;}
	public String getDescription() {return this.description;}
	public String getStatus() {return this.status;}
	public boolean isOperational() {
		return (this.status.compareTo("Operational") == 0);
	}
	public void setStatus(String status) { this.status = status; }

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
	public String getDatabaseID() {
		return ID;
	}

	@Override
	public void processResult(ResultSet result) throws SQLException {
	}
}