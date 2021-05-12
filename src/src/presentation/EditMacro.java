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
        List<String> params = null; //ordered list of params
        List<String> commands = null; //ordered lis tof commands
        List<String> output = null;
        int cmdIndex = 0;
        for(String paramToEdit : params){

            System.out.println("Enter new parameter of type ( "+paramToEdit+") for command " + commands.get(cmdIndex) + ": ");
//            String newParam =
//            output.add(commands.get(cmdIndex));
//            output.add();

        }



        BusinessProcessContainer newProcess = new BusinessProcessContainer("editMacro", Arrays.asList(macro));
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
