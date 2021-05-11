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
            " exit\n";

    public InputReader(){
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public UIProcess getNextCommand() throws IOException {
        System.out.print(">");
        String[] inputStr = reader.readLine().split(" ");
        UIProcess output = null;

        if(inputStr[0].equals("makeReagentExperiment")) output = new MakeReagentExperiment();
        else if(inputStr[0].equals("makeSampleExperiment")) output = new MakeSampleExperiment();
        else if(inputStr[0].equals("makeComplexExperiment")) output = new MakeComplexExperiment();
        else if(inputStr[0].equals("processReagentExperiment")) output = new ProcessReagentExperiment();
        else if(inputStr[0].equals("processSampleExperiment")) output = new ProcessSampleExperiment();
        else if(inputStr[0].equals("makeMacro")) output = new MakeMacro();
        else if(inputStr[0].equals("exit")) output = new ExitConsole();
        else
        {
            System.out.println("Invalid command given. Valid commands are: " + this.commandList);
            output = getNextCommand();
        }
        return output;
    }

    public String nextLine() throws IOException {
        return reader.readLine();
    }



}