package presentation;

import business.BusinessProcessContainer;
import business.models.Command;
import business.models.Experiment;
import business.models.Macro;
import data.LinkerManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class EditMacro implements UIProcess{
    String MacroName;
    List<String> commands = new ArrayList<>();

    @Override
    public void execute(BufferedReader reader, Queue<BusinessProcessContainer> queue) throws IOException {

        System.out.println("Select a macro from the following list to edit: ");
        Map<String, Macro> macroToCmdList = this.printMacros();
        System.out.print(">");
        String macro;
        while(true){
            macro = reader.readLine();
            if(macroToCmdList.containsKey(macro))break;
            else if(macro.equals("X")) return;
            else {
                System.out.println(macro+" is not a valid macro, please select one from the list or enter 'X' to cancel");
                System.out.print(">");
            }
        }

        Macro currentMacro = macroToCmdList.get(macro);

        Map<String, String> cmdToParams = new HashMap<>(); //command to params list

        for(Command currentCommand: currentMacro.getCommandList()){
            cmdToParams.put(currentCommand.getID(), currentCommand.getParameters());
        }

        List<String> commandOutput = new ArrayList<>();
        List<String> paramOutput = new ArrayList<>();

        commandOutput.add(macro); //format: macroName, cmd, param, cmd, param, ....

        List<String> commands = new ArrayList<>();

        for(Command currentCommand: macroToCmdList.get(macro).getCommandList()){
            commands.add(currentCommand.getID());
        }

        for(String command : commands){
            String paramToEdit = cmdToParams.get(command);
            commandOutput.add(command);
            if(paramToEdit == null) paramOutput.add(null);
            else{
                int expectedLen = paramToEdit.split(",").length;
                String newParam;
                while (true){
                    System.out.println("Enter new parameter of type ("+paramToEdit+") for command " + command + ": ");
                    newParam = reader.readLine();
                    if(newParam.split(",").length == expectedLen) break;
                    else System.out.println("Incorrect number of parameters passed for this input.");
                }
                paramOutput.add(newParam);

            }

        }

        System.out.println("Macro Name: " +commandOutput.get(0));
        int cmdIndex = 1;

        int cmdOutLen = commandOutput.size();
        while(cmdIndex < cmdOutLen){
            String command = commandOutput.get(cmdIndex);
            String param = paramOutput.get(cmdIndex-1);
            System.out.println("Command #" + cmdIndex + ": "+ command +" params: " + (param == null ? "N/A" : param));
            cmdIndex++;
        }

        System.out.print("Validate? (v)\n>");
        if (!reader.readLine().split(" ")[0].toUpperCase().equals("V")){
            System.out.println("MacroName " + macro + " was not validated, cancelling creation.");
            return;
        }

        BusinessProcessContainer newProcess = new BusinessProcessContainer("editMacro", commandOutput, paramOutput);
        queue.add(newProcess);
    }

    public String getType() {
        return "editMacro";

    }

    private Map<String, Macro> printMacros(){
        Map<String, Macro> macroToCommandList = LinkerManager.getInstance().getMacroModels();

        for(String macro : macroToCommandList.keySet()){
            //TODO: change this print line to print macro names (for jamari to do)
            System.out.println(macro);
        }

        return macroToCommandList;

    }
}
