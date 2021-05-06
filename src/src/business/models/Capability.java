package business.models;

import business.models.DatabaseModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Capability implements DatabaseModel {
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
		return "SELECT ID, Name, Type, Description, Status from Capabilities";
	}

	@Override
	public String addQuery() {
		return String.format("INSERT INTO Capabilities (ID, Name, Type, Description, Status) VALUES ('%s', '%s', '%s', '%s','%s');",
				this.getID(),
				this.getName(),
				this.getType(),
				this.getDescription(),
				this.getStatus());
	}

	@Override
	public String updateQuery() {
		return String.format("UPDATE Capabilities SET Name = '%s', Type = '%s', Description = '%s', Status = '%s' WHERE ID = '%s';",
				this.getName(),
				this.getType(),
				this.getDescription(),
				this.getStatus(),
				this.getID());
	}

	@Override
	public String deleteQuery() {
		return String.format("DELETE FROM Capabilities WHERE ID = '%s';",
				this.getID());
	}

	@Override
	public String getDatabaseID() {
		return ID;
	}

	@Override
	public void processResult(ResultSet result) throws SQLException {
	}
}