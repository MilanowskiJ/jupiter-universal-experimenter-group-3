import PresentationLayer.BusinessProcessContainer;
import PresentationLayer.UIProcess;

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
            else System.out.print(experiment+" is not a valid experiment, please select one from the list or create a new one (or enter 'X' to cance)");
        }

        List<String> params = new ArrayList<>(Arrays.asList(experiment));
        BusinessProcessContainer newProcess = new BusinessProcessContainer("process sample", params);
        queue.add(newProcess);
    }
    private Set<String> printValidSampleExperiments(){
        //This should call the database and add all the strings to this set
        Set<String> output = new HashSet<>();
        output.add("sampleExp1");
        output.add("sampleExp2");
        output.add("sampleExp3");
        output.add("sampleExp4");
        output.add("sampleExp5");
        for(String experiment : output){
            System.out.println(experiment);
        }
        return output;

    }

    public String getType() {
        return "process sample";
    }
}
