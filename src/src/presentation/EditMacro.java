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
        Set<String> macros = this.printMacros();
        System.out.print(">");
        String macro;
        while(true){
            macro = reader.readLine();
            if(macros.contains(macro))break;
            else if(macro.equals("X")) return;
            else {
                System.out.println(macro+" is not a valid macro, please select one from the list or enter 'X' to cancel");
                System.out.print(">");
            }
        }

        //TODO: get macro commands params in order from database (jamari write the query pls <3)
        Map<String, String> cmdToParams = null; //command to params list
        Map<String, List<String>> macroToCmdList = null; //macro to ordered commands

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

        BusinessProcessContainer newProcess = new BusinessProcessContainer("editMacro", commandOutput, paramOutput);
        queue.add(newProcess);
    }

    public String getType() {
        return "editMacro";

    }

    private Set<String> printMacros(){
        //TODO: This should call the database and add all the strings to this set
        Map<String, Experiment> models = null;// LinkerManager.getInstance().getExperimentModels();

        Set<String> output = new HashSet<>();
        for(String macro : models.keySet()){
            //TODO: change this print line to print macro names
            System.out.println(models.get(macro).getExperimentID() + ": " + models.get(macro).getType());
            output.add(macro);
        }

        return output;

    }
}
