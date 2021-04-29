import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class LinkerManager {
    private GenericLinker<Supply> supplyLinker;
    private GenericLinker<Capability> capabilityLinker;
    private GenericLinker<Experiment> experimentLinker;
    private GenericLinker<Command> commandLinker;

    //@TODO: fill connectionUrl fields here
    private String connectionUrl =
            "jdbc:sqlserver://yourserver.database.windows.net:1433;"
                    + "database=TODO;"
                    + "user=yourusername@yourserver;"
                    + "password=yourpassword;"
                    + "encrypt=true;"
                    + "trustServerCertificate=false;"
                    + "loginTimeout=30;";

    public LinkerManager() {
        String supplyGetQuery = "SELECT Name, QuantityAvailable, QuantityOriginally, Type, Unit from Supplies";

        supplyLinker = new GenericLinker<>(connectionUrl,
                supplyGetQuery,
                (result -> new Supply(result.getString("Name"),
                        result.getInt("QuantityAvailable"),
                        result.getInt("QuantityOriginally"),
                        result.getString("Type"),
                        result.getString("Unit"))),
                (model -> {
                    return String.format("INSERT INTO Supplies (Name, QuantityAvailable, QuantityOriginally, Type, Unit) VALUES ('%s', %d, %d, '%s', '%s');",
                            model.getName(),
                            model.getQuantityAvailable(),
                            model.getQuantityOriginal(),
                            model.getType(),
                            model.getUnit());
                }));
    }

    public List<Supply> getSupplyModels() {return supplyLinker.getModels();}
    public boolean addSupply(Supply model) {return supplyLinker.add(model);}
    public boolean updateSupply(Supply model) {return supplyLinker.update(model);}
    public boolean deleteSupply(int id) {return supplyLinker.delete(id);}

    public List<Capability> getCapabilityModels() {return capabilityLinker.getModels();}
    public boolean addCapability(Capability model) {return capabilityLinker.add(model);}
    public boolean updateCapability(Capability model) {return capabilityLinker.update(model);}
    public boolean deleteCapability(int id) {return capabilityLinker.delete(id);}

    public List<Experiment> getExperimentModels() {return experimentLinker.getModels();}
    public boolean addExperiment(Experiment model) {return experimentLinker.add(model);}
    public boolean updateExperiment(Experiment model) {return experimentLinker.update(model);}
    public boolean deleteExperiment(int id) {return experimentLinker.delete(id);}

    public List<Command> getCommandModels() {return commandLinker.getModels();}
    public boolean addCommand(Command model) {return commandLinker.add(model);}
    public boolean updateCommand(Command model) {return commandLinker.update(model);}
    public boolean deleteCommand(int id) {return commandLinker.delete(id);}
}
