package data;

import business.models.*;

import java.util.Map;

public class LinkerManager {

    private static LinkerManager single_instance = null;

    private GenericLinker<Supply> supplyLinker;
    private GenericLinker<Capability> capabilityLinker;
    private GenericLinker<Experiment> experimentLinker;
    private GenericLinker<Command> commandLinker;
    private GenericLinker<Macro> macroLinker;

    //@TODO: fill connectionUrl fields here
    private final String connectionUrl =
            "jdbc:sqlserver://titan.csse.rose-hulman.edu:1433;"
                    + "databaseName=374Team3;"
                    + "user=morrisjj;"
                    + "password=Password!@#";

    public LinkerManager() {
        String supplyGetQuery = "SELECT Name, QuantityAvailable, QuantityOriginally, Type, Unit from Supplies";
        String experimentGetQuery = "SELECT  Experiment.ExperimentName, Experiment.Priority, Experiment.Complete, Experiment.ExperimentID, Experiment.ExperimentType, Experiment.Description,\n" +
                "ReagentExperiment.Reagent, ReagentExperiment.Amount, ReagentExperiment.SampleId, SampleExperiment.Target, SampleExperiment.Location, SampleExperiment.Amount AS sampleAmount\n" +
                "FROM Experiment LEFT JOIN SampleExperiment \n" +
                "ON Experiment.ExperimentID = SampleExperiment.ExperimentID LEFT JOIN \n" +
                "ReagentExperiment ON Experiment.ExperimentID = ReagentExperiment.ExperimentID";
        String capabilityGetQuery = "SELECT ID, Name, Type, Description, Status from Capabilities";
        String commandGetQuery = "SELECT CommandID, CommandName, Params, from Commands";
        String macroGetQuery = "";

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

        experimentLinker = new GenericLinker<Experiment>(connectionUrl,
                experimentGetQuery,
                (result -> {
                    switch (result.getString("ExperimentType")){
                        case "Sample":
                            SampleExperiment tempSample = new SampleExperiment(result.getString("ExperimentName"),
                                    result.getString("Priority"),
                                    result.getString("ExperimentID"),
                                    result.getString("Complete"),
                                    result.getString("Description"));
                            tempSample.setTarget(result.getString("Target"));
                            tempSample.setAmount(result.getInt("Amount"));
                            tempSample.setWhere(result.getString("Location"));
                            return tempSample;
                        case "Reagent":
                            ReagentExperiment tempReagent = new ReagentExperiment(result.getString("ExperimentName"),
                                    result.getString("Priority"),
                                    result.getString("ExperimentID"),
                                    result.getString("Complete"),
                                    result.getString("Description"));
                            tempReagent.setReagent(result.getString("Reagent"));
                            tempReagent.setAmount(result.getInt("Amount"));
                            tempReagent.setSampleID(result.getString("SampleId"));
                            return tempReagent;
                        case "Complex":
                            ComplexExperiment tempComplex = new ComplexExperiment(result.getString("ExperimentName"),
                                    result.getString("Priority"),
                                    result.getString("ExperimentID"),
                                    result.getString("Complete"),
                                    result.getString("Description"));
                            return tempComplex;
                        default: return null;
                    }}
                ));

        commandLinker = new GenericLinker<>(connectionUrl,
                commandGetQuery,
                (result -> new Command(result.getString("CommandID"),
                        result.getString("CommandName"),
                        result.getString("Params"))));

        macroLinker = new GenericLinker<>(connectionUrl,
                macroGetQuery,
                (result -> new Macro()));
    }

    public Map<String, Supply> getSupplyModels() {return supplyLinker.getModels();}
    public boolean add(Supply model) {return supplyLinker.add(model);}
    public boolean update(Supply model) {return supplyLinker.update(model);}
    public boolean delete(Supply model) {return supplyLinker.delete(model);}
    public boolean updateSupplyGroup(Map<String, Supply> models) {
        for(String key: models.keySet()){
            update(models.get(key));
        }
        return true;
    }

    public Map<String, Capability> getCapabilityModels() {return capabilityLinker.getModels();}
    public boolean add(Capability model) {return capabilityLinker.add(model);}
    public boolean update(Capability model) {return capabilityLinker.update(model);}
    public boolean delete(Capability model) {return capabilityLinker.delete(model);}
    public boolean updateCapabilityGroup(Map<String, Capability> models) {
        for(String key: models.keySet()){
            update(models.get(key));
        }
        return true;
    }

    public Map<String, Experiment> getExperimentModels() {return experimentLinker.getModels();}
    public boolean add(Experiment model) {return experimentLinker.add(model);}
    public boolean update(Experiment model) {return experimentLinker.update(model);}
    public boolean delete(Experiment model) {return experimentLinker.delete(model);}
    public boolean updateExperimentGroup(Map<String, Experiment> models) {
        for(String key: models.keySet()){
            update(models.get(key));
        }
        return true;
    }

    public Map<String, Command> getCommandModels() {return commandLinker.getModels();}
    public boolean add(Command model) {return commandLinker.add(model);}
    public boolean update(Command model) {return commandLinker.update(model);}
    public boolean delete(Command model) {return commandLinker.delete(model);}
    public boolean updateCommandGroup(Map<String, Command> models) {
        for(String key: models.keySet()){
            update(models.get(key));
        }
        return true;
    }

    public Map<String, Macro> getMacroModels() {return macroLinker.getModels();}
    public boolean add(Macro model) {return macroLinker.add(model);}
    public boolean update(Macro model) {return macroLinker.update(model);}
    public boolean delete(Macro model) {return macroLinker.delete(model);}
    public boolean updateMacroGroup(Map<String, Macro> models){
        for(String key: models.keySet()){
            update(models.get(key));
        }
        return true;
    }

    public static synchronized LinkerManager getInstance(){
        if (single_instance == null)
            single_instance = new LinkerManager();

        return single_instance;
    }
}
