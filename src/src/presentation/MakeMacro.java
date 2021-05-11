package presentation;

import business.BusinessProcessContainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class MakeMacro implements UIProcess {
    String MacroName;
    List<String> commands = new ArrayList<>();

    @Override
    public void execute(BufferedReader reader, Queue<BusinessProcessContainer> queue) throws IOException {
        boolean exited = false;

        String[] inputStr;
        while(true) {


            System.out.println("Creating new complex experiment...");
            System.out.print("Please Enter Macro Specification: Macro name\n>");
            MacroName = reader.readLine();
            commands.add(MacroName);

            while(true){
                System.out.print("Add command to macro by entering its ID (or enter X to continue): \n>");
                String readString = reader.readLine();
                if(readString.toUpperCase().equals("X")) break;
                else if(readString.length() >= 2 && readString.length() <= 4 && readString.charAt(0) == 'C'){
                    commands.add(readString);
                } else System.out.println(readString +" is not a valid command ID.");
            }


            System.out.println("Macro Name: " + commands.get(0));
            int i = 1;
            for(String command : commands){
                if(command.equals(commands.get(0)))continue;
                System.out.println("Step "+i+": "+command);
                i++;
            }
            if(i == 1) {
                System.out.println("Macro must have at least one command, aborting operation");
                return;
            }
            System.out.print("Validate? (v)\n>");
            inputStr = reader.readLine().split(" ");
            if (inputStr[0].toUpperCase().equals("V")){
                exited = true;
                break;
            } else break;
        }
        if(exited) System.out.println("Macro "+MacroName+" validated.");
        else {
            System.out.println("MacroName " + MacroName + " was not validated, cancelling creation.");
            return;
        }

        BusinessProcessContainer newProcess = new BusinessProcessContainer("make macro", commands);
        queue.add(newProcess);
    }

    public String getType() {
        return "make macro";

    }


}
