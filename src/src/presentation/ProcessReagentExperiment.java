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

public class ProcessReagentExperiment implements UIProcess {
    String sampleID;

    public void execute(BufferedReader reader, Queue<BusinessProcessContainer> queue) throws IOException {
        System.out.println("Select reagent-based experiment to process:");
        System.out.println("Fetching valid experiments...");
        Set<String> experiments = this.printValidReagentExperiments();
        System.out.print(">");
        String experiment;

        while(true){
            experiment = reader.readLine();
            if(experiments.contains(experiment))break;
            else if(experiment.equals("X")) return;
            else System.out.print(experiment+" is not a valid experiment, please select one from the list or enter 'X' to cancel");
        }


        List<String> params = new ArrayList<>(Arrays.asList(experiment));
        BusinessProcessContainer newProcess = new BusinessProcessContainer("process reagent", params);
        queue.add(newProcess);
    }

    private Set<String> printValidReagentExperiments(){
        //This should call the database and add all the strings to this set
        Map<String, Experiment> models = LinkerManager.getInstance().getExperimentModels();

        Set<String> output = new HashSet<>();
        for(String experiment : models.keySet()){
            System.out.println(models.get(experiment).getExperimentID() + ": " + models.get(experiment).getType());
            output.add(experiment);
        }
        return output;

    }
}
