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

        //TODO: get this map and set from the database
        Map<String, String> cmdToParam = null;
        Set<String> macroNames = null;
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
        BusinessProcessContainer newProcess = new BusinessProcessContainer("make complex", commandOutput, paramOutput);
        queue.add(newProcess);
    }

    public String getType() {
        return "make complex";

    }


}