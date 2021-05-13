package presentation;

import presentation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReader {
    public BufferedReader reader;
    String commandList = "\nmakeSampleExperiment\n" +
            " makeReagentExperiment\n" +
            "makeComplexExperiment\n"+
            "processReagentExperiment\n"+
            "processSampleExperiment\n"+
            "makeMacro\n"+
            "editMacro\n"+
            " exit\n";

    public InputReader(){
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public UIProcess getNextCommand() throws IOException {
        System.out.print(">");
        String[] inputStr = reader.readLine().split(" ");
        AbstractUIProcessFactory factory = new UIProcessFactory();
        UIProcess output = factory.makeUIProcess(inputStr[0]);


        if(output == null){
            System.out.println("Invalid command given. Valid commands are: " + this.commandList);
            output = getNextCommand();
        }
        return output;
    }

    public String nextLine() throws IOException {
        return reader.readLine();
    }



}