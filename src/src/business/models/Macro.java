package business.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;

public class Macro implements DatabaseModel, Processable{

    ArrayList<Command> commandList;

    public Macro(){
        commandList = new ArrayList<>();
    }

    @Override
    public String addQuery() {
        return null;
    }

    @Override
    public String updateQuery() {
        return null;
    }

    @Override
    public String deleteQuery() {
        return null;
    }

    @Override
    public String getDatabaseID() {
        return null;
    }

    @Override
    public JSONObject process() {
        JSONArray commandList = new JSONArray();

        for(Command currentCommand: commandList){
            commandList.put(currentCommand.process());
        }

        JSONObject commands = new JSONObject();
        commands.put("experiment_commands", commandList);

        return commands;
    }
}
