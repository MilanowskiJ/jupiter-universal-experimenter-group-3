import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LinkerManager {
    private GenericLinker<Supply> supplyLinker;
    private GenericLinker<Capability> capabilityLinker;
    private GenericLinker<Experiment> experimentLinker;
    private GenericLinker<Command> commandLinker;

    //@TODO: fill connectionUrl fields here
    private final String connectionUrl =
            "jdbc:sqlserver://yourserver.database.windows.net:1433;"
                    + "database=TODO;"
                    + "user=yourusername@yourserver;"
                    + "password=yourpassword;"
                    + "encrypt=true;"
                    + "trustServerCertificate=false;"
                    + "loginTimeout=30;";

    public LinkerManager() {
        String supplyGetQuery = "SELECT Name, QuantityAvailable, QuantityOriginally, Type, Unit from Supplies";
        String capabilityGetQuery = "SELECT Name, QuantityAvailable, QuantityOriginally, Type, Unit from Supplies";
        String experimentGetQuery = "SELECT Name, QuantityAvailable, QuantityOriginally, Type, Unit from Supplies";
        String commandGetQuery = "SELECT Name, QuantityAvailable, QuantityOriginally, Type, Unit from Supplies";

        supplyLinker = new GenericLinker<>(connectionUrl,
                supplyGetQuery,
                (result -> new Supply(result.getString("Name"),
                        result.getInt("QuantityAvailable"),
                        result.getInt("QuantityOriginally"),
                        result.getString("Type"),
                        result.getString("Unit"))));

        capabilityLinker = new GenericLinker<>(connectionUrl,
                capabilityGetQuery,
                (result -> new Capability(result.getString("ID"),
                        result.getString("Type"),
                        result.getString("Name"),
                        result.getString("Description"),
                        result.getString("Status"))));
    }

    public List<Supply> getSupplyModels() {return supplyLinker.getModels();}
    public boolean add(Supply model) {return supplyLinker.add(model);}
    public boolean update(Supply model) {return supplyLinker.update(model);}
    public boolean delete(Supply model) {return supplyLinker.delete(model);}

    public List<Capability> getCapabilityModels() {return capabilityLinker.getModels();}
    public boolean add(Capability model) {return capabilityLinker.add(model);}
    public boolean update(Capability model) {return capabilityLinker.update(model);}
    public boolean delete(Capability model) {return capabilityLinker.delete(model);}

    public List<Experiment> getExperimentModels() {return experimentLinker.getModels();}
    public boolean add(Experiment model) {return experimentLinker.add(model);}
    public boolean update(Experiment model) {return experimentLinker.update(model);}
    public boolean delete(Experiment model) {return experimentLinker.delete(model);}

    public List<Command> getCommandModels() {return commandLinker.getModels();}
    public boolean add(Command model) {return commandLinker.add(model);}
    public boolean update(Command model) {return commandLinker.update(model);}
    public boolean delete(Command model) {return commandLinker.delete(model);}
}
