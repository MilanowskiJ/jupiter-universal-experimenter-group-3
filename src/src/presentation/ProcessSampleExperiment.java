package presentation;

import business.BusinessProcessContainer;
import business.models.Experiment;
import data.LinkerManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.*;

public class ProcessSampleExperiment implements UIProcess {

    public void execute(BufferedReader reader, Queue<BusinessProcessContainer> queue) throws IOException {
        System.out.println("Select sample-based experiment to process:");
        System.out.println("Fetching valid experiments...");
        Set<String> experiments = this.printValidSampleExperiments();
        System.out.print(">");
        String experiment;
        while(true){
            experiment = reader.readLine();
            if(experiments.contains(experiment))break;
            else if(experiment.equals("X")) return;
            else {
                System.out.println(experiment+" is not a valid experiment, please select one from the list or enter 'X' to cancel");
                System.out.print(">");
            }
        }

        List<String> params = new ArrayList<>(Arrays.asList(experiment));
        BusinessProcessContainer newProcess = new BusinessProcessContainer("process sample", params);
        queue.add(newProcess);
    }
    private Set<String> printValidSampleExperiments(){
        //This should call the database and add all the strings to this set
        Map<String, Experiment> models = LinkerManager.getInstance().getExperimentModels();

        Set<String> output = new HashSet<>();
        for(String experiment : models.keySet()){
            System.out.println(models.get(experiment).getExperimentID() + ": " + models.get(experiment).getType());
            output.add(experiment);
        }

        return output;

    }

    public String getType() {
        return "process sample";
    }
}
