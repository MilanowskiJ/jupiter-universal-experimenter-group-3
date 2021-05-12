package business.models;

import org.json.JSONArray;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Macro implements DatabaseModel, Processable{
    String name;
    ArrayList<Command> commandList;

    public Macro(String name){
        this.name = name;
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

    public void makeCommandList(List<String> commandIDs, List<String> params) {
        Iterator<String> c = commandIDs.iterator();
        Iterator<String> p = params.iterator();
        if(c.hasNext()) {
            c.next();
            while (c.hasNext() && p.hasNext()) {

            }
        }
    }
}
