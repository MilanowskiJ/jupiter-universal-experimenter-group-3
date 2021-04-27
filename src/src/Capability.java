
public class Capability {
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
}