package presentation;

import business.BusinessProcessContainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class MakeMacro implements UIProcess {
    String MacroName;
    List<String> output = new ArrayList<>();

    @Override
    public void execute(BufferedReader reader, Queue<BusinessProcessContainer> queue) throws IOException {
        boolean exited = false;




            System.out.println("Creating new macro...");
            System.out.print("Please Enter Macro Specification: Macro name\n>");
            MacroName = reader.readLine();
            output.add(MacroName);

            //TODO: get this from the database
            Map<String, String> cmdToParam = null;
            while(true){
                System.out.print("Add command to macro by entering its ID (or enter X to continue): \n>");
                String readString = reader.readLine();
                if(readString.toUpperCase().equals("X")) break;
                else if(cmdToParam.keySet().contains(readString)){
                    output.add(readString);
                } else {
                    System.out.println(readString +" is not a valid command ID.");
                    continue;
                }

                String paramType = cmdToParam.get(readString);
                int expectedLen = paramType.split(",").length;
                while(true){
                    System.out.println("Enter parameter(s) of type "+paramType+": \n>");
                    readString = reader.readLine();
                    if(expectedLen == readString.split(",").length){
                        output.add(readString);
                        break;
                    } else System.out.println("Incorrect number of parameters passed for this input.");
                }
            }

        Iterator<String> outItr = output.listIterator();
            System.out.println("Macro Name: " +outItr.next());
            int i = 1;

            while(outItr.hasNext()){
                String command = outItr.next();
                String param = outItr.next();
                System.out.println("Command #" + i + ": "+ command +" params: " + (param == null ? "N/A" : param));
                i++;
            }
            if(i == 1) {
                System.out.println("Macro must have at least one command, aborting operation");
                return;
            }
            System.out.print("Validate? (v)\n>");
            if (reader.readLine().split(" ")[0].toUpperCase().equals("V")){
                System.out.println("MacroName " + MacroName + " was not validated, cancelling creation.");
        }

        BusinessProcessContainer newProcess = new BusinessProcessContainer("makeMacro", output);
        queue.add(newProcess);
    }

    public String getType() {
        return "makeMacro";

    }


}
