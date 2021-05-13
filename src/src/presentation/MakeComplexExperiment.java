package presentation;

import business.BusinessProcessContainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class MakeComplexExperiment implements UIProcess {


    @Override
    public void execute(BufferedReader reader, Queue<BusinessProcessContainer> queue) throws IOException {
        List<String> commandOutput = new ArrayList<>();
        List<String> paramOutput = new ArrayList<>();

        System.out.println("Creating new complex experiment...");
        System.out.print("Please Enter Experiment Specification: Experiment name\n>");
        String experimentID = reader.readLine();
        commandOutput.add(experimentID);


        //TODO: get this map and set from the database
        Map<String, String> cmdToParam = new HashMap<>();
        Set<String> macroNames = new HashSet<>();
        macroNames.add("MC1");
        macroNames.add("MC2");

        cmdToParam.put("C1", null);
        cmdToParam.put("C2", "x,y,z");
        cmdToParam.put("C3", null);
        cmdToParam.put("C4", "SampleID");
        cmdToParam.put("C5", "reagent,amount");
        cmdToParam.put("C6", null);
        cmdToParam.put("C7", "x,y,z");
        cmdToParam.put("C8", null);
        cmdToParam.put("C9", "C");
        cmdToParam.put("C10", null);

        while(true){

            System.out.print("Add command or macro to complex experiment by entering its ID or name respectively (or enter X to continue): \n>");
            String readString = reader.readLine();
            if(readString.toUpperCase().equals("X")) break;
            else if(cmdToParam.keySet().contains(readString)){
                commandOutput.add(readString);
            } else if(macroNames.contains(readString)){
                System.out.println("Macro "+readString+" added to the complex experiment");
                commandOutput.add(readString);
                paramOutput.add(null);
                continue;
            }
            else {
                System.out.println(readString +" is not a valid command ID or macro name.");
                continue;
            }

            String paramType = cmdToParam.get(readString);
            if(paramType == null){
                paramOutput.add(null);
                continue;
            }

            int expectedLen = paramType.split(",").length;
            while(true){

                System.out.println("Enter parameter(s) of type "+paramType+": \n>");
                readString = reader.readLine();
                if(expectedLen == readString.split(",").length){
                    paramOutput.add(readString);
                    break;
                } else System.out.println("Incorrect number of parameters passed for this input.");
            }

        }

        System.out.println("Experiment ID: " +commandOutput.get(0));
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
            System.out.println("Experiment " + experimentID + " was not validated, cancelling creation.");
            return;
        }

        BusinessProcessContainer newProcess = new BusinessProcessContainer("makeComplex", commandOutput, paramOutput);
        queue.add(newProcess);
    }

    public String getType() {
        return "makeComplex";

    }


}