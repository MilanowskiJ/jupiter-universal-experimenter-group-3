package PresentationLayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReader {
    BufferedReader reader;
    String commandList = "\nmakeSampleExperiment\n" +
            " makeReagentExperiment\n" +
            "makeComplexExperiment\n"+
            "processReagentExperiment\n"+
            "processSampleExperiment\n"+
            " exit\n";

    public InputReader(){
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public Cmd getNextCommand() throws IOException {
        System.out.print(">");
        String[] inputStr = reader.readLine().split(" ");
        Cmd output = null;

        if(inputStr[0].equals("makeReagentExperiment")) output = new MakeReagentExperiment();
        else if(inputStr[0].equals("makeSampleExperiment")) output = new MakeSampleExperiment();
        else if(inputStr[0].equals("makeComplexExperiment")) output = new MakeComplexExperiment();
        else if(inputStr[0].equals("processReagentExperiment")) output = new ProcessReagentExperiment();
        else if(inputStr[0].equals("processSampleExperiment")) output = new ProcessSampleExperiment();
        else if(inputStr[0].equals("exit")) output = new ExitConsole();
        else
        {
            System.out.println("Invalid command given. Valid commands are: " + this.commandList);
            output = getNextCommand();
        }
        return output;
    }



}