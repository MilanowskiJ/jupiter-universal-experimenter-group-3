package presentation;

import business.BusinessProcessContainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class MakeMacro implements UIProcess {
    String MacroName;
    List<String> commandOutput = new ArrayList<>();
    List<String> paramOutput = new ArrayList<>();
    @Override
    public void execute(BufferedReader reader, Queue<BusinessProcessContainer> queue) throws IOException {
        boolean exited = false;




            System.out.println("Creating new macro...");
            System.out.print("Please Enter Macro Specification: Macro name\n>");
            MacroName = reader.readLine();
            commandOutput.add(MacroName);

            //This is the list of commands provided in the spreadsheet
            Map<String, String> cmdToParam = new HashMap<>();
            cmdToParam.put("C1", "x,y,z");
            cmdToParam.put("C2", "x,y,z");
            cmdToParam.put("C3", "x,y,z");
            cmdToParam.put("C4", "C");
            cmdToParam.put("C5", "SampleID");
            cmdToParam.put("C6", "reagent,amount");
            cmdToParam.put("C7", null);
            cmdToParam.put("C8", null);
            cmdToParam.put("C9", "tool");
        cmdToParam.put("C10", "x,y,z");
        cmdToParam.put("C11", null);
        cmdToParam.put("C12", "x,y,z,time");
        cmdToParam.put("C13", "x,y,z");
        cmdToParam.put("C14", "x,y,z,time");
        cmdToParam.put("C15", "x,y,z");
        cmdToParam.put("C16", "sensor");
        cmdToParam.put("C17", "x,y,z");
        cmdToParam.put("C18", "mode");
        cmdToParam.put("C19", null);
        cmdToParam.put("C20", null);


        while(true){
                System.out.print("Add command to macro by entering its ID (or enter X to continue): \n>");
                String readString = reader.readLine();
                if(readString.toUpperCase().equals("X")) break;

                else if(cmdToParam.keySet().contains(readString)){
                    commandOutput.add(readString);
                } else {
                    System.out.println(readString +" is not a valid command ID.");
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
            if(paramOutput.isEmpty()) {
                System.out.println("Macro must have at least one command, aborting operation");
                return;
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
                System.out.println("MacroName " + MacroName + " was not validated, cancelling creation.");
                return;
        }

        BusinessProcessContainer newProcess = new BusinessProcessContainer("makeMacro", commandOutput, paramOutput);
        queue.add(newProcess);
    }

    public String getType() {
        return "makeMacro";
    }


}
