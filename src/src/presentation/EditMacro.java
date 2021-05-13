package presentation;

import business.BusinessProcessContainer;
import business.models.Experiment;
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
        Map<String, List<String>> macroToCmdList = this.printMacros();
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

        //TODO: get macro commands params in order from database
        Map<String, String> cmdToParams = new HashMap<>(); //command to params list
        cmdToParams.put("C1", null);
        cmdToParams.put("C2", "x,y,z");
        cmdToParams.put("C3", null);
        cmdToParams.put("C4", "SampleID");
        cmdToParams.put("C5", "reagent,amount");
        cmdToParams.put("C6", null);
        cmdToParams.put("C7", "x,y,z");
        cmdToParams.put("C8", null);
        cmdToParams.put("C9", "C");
        cmdToParams.put("C10", null);


        List<String> commandOutput = new ArrayList<>();
        List<String> paramOutput = new ArrayList<>();

        commandOutput.add(macro); //format: macroName, cmd, param, cmd, param, ....
        List<String> commands = macroToCmdList.get(macro);

        for(String command : commands){
            String paramToEdit = cmdToParams.get(command);
            commandOutput.add(command);
            if(paramToEdit == null) paramOutput.add(null);
            else{
                int expectedLen = paramToEdit.split(",").length;
                String newParam;
                while (true){
                    System.out.println("Enter new parameter of type ( "+paramToEdit+") for command " + command + ": ");
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
        if (reader.readLine().split(" ")[0].toUpperCase().equals("V")){
            System.out.println("MacroName " + MacroName + " was not validated, cancelling creation.");
            return;
        }

        BusinessProcessContainer newProcess = new BusinessProcessContainer("editMacro", commandOutput, paramOutput);
        queue.add(newProcess);
    }

    public String getType() {
        return "editMacro";

    }

    private Map<String, List<String>> printMacros(){
        //TODO: get this from database (the lists should be ordered)
        Map<String, List<String>> macroToCommandList = new HashMap<>();// LinkerManager.getInstance().getExperimentModels();
        List<String> macro1List = new ArrayList<>(Arrays.asList("C1", "C2", "C3", "C4", "C5"));
        List<String> macro2List = new ArrayList<>(Arrays.asList("C7", "C8", "C9", "C10", "C6"));

        macroToCommandList.put("MC1", macro1List);
        macroToCommandList.put("MC2", macro2List);

        for(String macro : macroToCommandList.keySet()){
            //TODO: change this print line to print macro names (for jamari to do)
            System.out.println(macro);
        }

        return macroToCommandList;

    }
}
