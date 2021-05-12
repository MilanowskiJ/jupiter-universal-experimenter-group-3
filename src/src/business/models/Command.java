package business.models;

import business.models.DatabaseModel;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Command implements DatabaseModel, Processable {
    private String ID;
    private String name;
    private String parameters;

    public Command(String ID, String name, String parameters) {
        this.ID = ID;
        this.name = (name == null) ? "" : name;
        this.parameters = parameters;
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
}
