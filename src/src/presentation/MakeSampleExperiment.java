package presentation;

import business.BusinessProcessContainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class MakeSampleExperiment implements UIProcess {
    String whatToSample;
    String howMuchToSample;
    String whereToSample;
    String experimentID;

    public void execute(BufferedReader reader, Queue<BusinessProcessContainer> queue) throws IOException {
        boolean exited = false;

        String[] inputStr;
        ArrayList<String> output = new ArrayList<>();
        while(true) {
            System.out.println("Creating new sample-based experiment...");
            System.out.print("Please Enter business.Experiment Specification: ExperimentID\n>");
            experimentID = reader.readLine();

            System.out.print("Please Enter business.Experiment Specification: What to business.Sample\n>");
            whatToSample = reader.readLine();

            System.out.print("Please Enter business.Experiment Specification: How Much to business.Sample\n>");
            howMuchToSample = reader.readLine();

            System.out.print("Please Enter business.Experiment Specification: Where to business.Sample\n>");
            whereToSample = reader.readLine();

            System.out.println("business.Experiment Specification Entered:\n ExperimentID: " + experimentID + "\nWhat to business.Sample: " + whatToSample + "\nHow Much to business.Sample: " + howMuchToSample + "\nWhere to business.Sample: " + whereToSample);
            System.out.print("Validate? (v)\n>");
            inputStr = reader.readLine().split(" ");
            if (inputStr[0].toUpperCase().equals("V")){
                exited = true;
                break;
            }
        }
        if(exited) System.out.println("business.Experiment Doable. \nMade business.Experiment " + experimentID);
        else {
            System.out.println("business.Experiment " + experimentID + " was not validated, cancelling creation.");
            return;
        }

        List<String> params = new ArrayList<>(Arrays.asList(whatToSample, howMuchToSample, whereToSample, experimentID));
        BusinessProcessContainer newProcess = new BusinessProcessContainer("make sample", params);
        queue.add(newProcess);
    }

    public String getType() {
        return "make sample";
    }
}

