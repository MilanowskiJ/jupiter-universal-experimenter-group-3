package business.models;

import business.models.DatabaseModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Supply implements DatabaseModel {

//	if we find a way to use an enum while keeping things decoupled, that'd be great
//	public enum Type {
//		REAGENT,
//		EXPENDABLE
//	}
//	public enum Unit {
//		cl,
//		g,
//		unit,
//		ml
//	}

	private String name;

	private String unit;
	private String type;
	private int quantityAvailable;
	private int quantityOriginal;
	public Supply(String name, int qAvailable, int qOriginal, String type, String unit) {
		this.name = name;
		this.quantityAvailable = qAvailable;
		this.quantityOriginal = qOriginal;
		this.unit = unit;
		this.type = type;
	}

	//checks whether there is at least n quantity available

	private boolean checkEnough(int n) {
		return quantityAvailable >= n;
	}
	private void reduceQuantityAvailable(int n) {
		if (this.checkEnough(n)) {
			this.quantityAvailable -= n;
		}
	}

	public int getQuantityAvailable() {return quantityAvailable;}

	public int getQuantityOriginal() {return quantityOriginal;}
	public String getName() {return name;}
	public String getType() {return type;}
	public String getUnit() {return unit;}

	public void setName(String name) {
		this.name = name;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}
	public void setQuantityOriginal(int quantityOriginal) {
		this.quantityOriginal = quantityOriginal;
	}

	@Override
	public String getQuery() {
		return "SELECT Name, QuantityAvailable, QuantityOriginally, Type, Unit from Supplies";
	}

	@Override
	public String addQuery() {
		return String.format("INSERT INTO Supplies (Name, QuantityAvailable, QuantityOriginally, Type, Unit) VALUES ('%s', %d, %d, '%s', '%s');",
				this.getName(),
				this.getQuantityAvailable(),
				this.getQuantityOriginal(),
				this.getType(),
				this.getUnit());
	}

	@Override
	public String updateQuery() {
		return String.format("UPDATE Supplies SET QuantityAvailable = %d, QuantityOriginally = %d, Type = '%s', Unit = '%s' WHERE Name = '%s';",
				this.getQuantityAvailable(),
				this.getQuantityOriginal(),
				this.getType(),
				this.getUnit(),
				this.getName());
	}

	@Override
	public String deleteQuery() {
		return String.format("DELETE FROM Supplies WHERE Name = '%s';",
				this.getName());
	}

	@Override
	public String getDatabaseID() {
		return name;
	}

	@Override
	public void processResult(ResultSet result) throws SQLException {
	}
}