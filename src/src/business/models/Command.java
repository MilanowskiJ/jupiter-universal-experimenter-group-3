package business.models;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Command implements DatabaseModel, Processable, ComplexExperiment.ExperimentStepWrapper {
    private String ID;

    private String name;
    private String parameters;
    private String parameterValues;
    public Command(String ID, String name, String parameters) {
        this.ID = ID;
        this.name = (name == null) ? "" : name;
        this.parameters = parameters;
        this.parameterValues = null;
    }

    public String getParameterValues() {
        return parameterValues;
    }

    public void setParameterValues(String parameterValues) {
        this.parameterValues = parameterValues;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public String getParameters() {
        return parameters;
    }

    @Override
    public String addQuery() {
        return String.format("INSERT INTO Commands (CommandID, CommandName, Params) VALUES ('%s', '%s', '%s')",
                this.ID,
                this.name,
                this.parameters);
    }

    @Override
    public String updateQuery() {
        return String.format("UPDATE Commands SET CommandName = '%s', Params = '%s' WHERE CommandID = '%s';",
                this.name,
                this.parameters,
                this.ID);
    }

    @Override
    public String deleteQuery() {
        return String.format("DELETE FROM Commands WHERE CommandID = '%s';",
                this.ID);
    }

    @Override
    public String getDatabaseID() {
        return ID;
    }

    @Override
    public JSONObject process() {
        JSONObject commandJSON = new JSONObject();
        commandJSON.put("command", ID);
        if(parameters != null) {
            commandJSON.put("param", "[" + parameters + "]");
        }

        return commandJSON;
    }

    @Override
    public String getIDProcessor() {
        return "'" + this.getDatabaseID() + "', 'NULL'";
    }

    @Override
    public String getParamValues() {
        return this.parameterValues;
    }

    @Override
    public List<JSONObject> processStep() {
        List<JSONObject> list = new ArrayList();
        list.add(this.process());
        return list;
    }
}
