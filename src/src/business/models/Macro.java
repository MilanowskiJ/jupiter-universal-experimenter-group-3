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
        commandList = new ArrayList<Command>();
    }

    @Override
    public String addQuery() {
        StringBuilder query = new StringBuilder();

        for (int index = 0; index < commandList.size(); index++) {
            Command currentCommand = commandList.get(index);
            query.append(String.format("INSERT INTO Snackros (CommandID, MacroName, CommandOrder, Params) VALUES ('%s', '%s', %d, '%s');",
                    currentCommand.getDatabaseID(),
                    name,
                    index,
                    currentCommand.getParameterValues()
                    ));
        }

        return query.toString();
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
        return name;
    }

    @Override
    public JSONObject process() {
        JSONArray commandList = new JSONArray();

        for(Object currentCommand: commandList){
            commandList.put(((Command) currentCommand).process());
        }

        JSONObject commands = new JSONObject();
        commands.put("experiment_commands", commandList);

        return commands;
    }

    public void makeCommandList(List<String> commandIDs, List<String> params) {
        Iterator<String> c = commandIDs.iterator();
        Iterator<String> p = params.iterator();
        Command temp;
        if(c.hasNext()) {
            c.next();
            while (c.hasNext() && p.hasNext()) {
                String stemp = p.next();
                temp = new Command(c.next(), "", stemp);
                if(stemp != null)
                    temp.setParameterValues(stemp);
                commandList.add(temp);
            }
        }
    }

    public void addCommand(Command command){
        commandList.add(command);
    }
}
