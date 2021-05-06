package business;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Sample{
	private String sampleID;
	private String name;
	private int amount;
	
	public Sample(String sampleID, String name, int amount) {
		this.sampleID = sampleID;
		this.name = name;
		this.amount = amount;
	}
	
	public String getSampleID() {return sampleID;}
	public String getName() {return name;}
	public int getAmount() {return amount;}
}
