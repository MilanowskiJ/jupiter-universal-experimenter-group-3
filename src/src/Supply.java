
public class Supply {

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
	
}