import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ProcessReagentExperiment implements Cmd{
    String sampleID;

    @Override
    public ArrayList<String> execute(BufferedReader reader) throws IOException {
        System.out.println("Select reagent-based experiment to process:");
        System.out.println("Fetching valid experiments...");
        Set<String> experiments = this.printValidReagentExperiments();
        System.out.print(">");
        String experiment;

        while(true){
            experiment = reader.readLine();
            if(experiments.contains(experiment))break;
            else if(experiment.equals("X")) return null;
            else System.out.print(experiment+" is not a valid experiment, please select one from the list or enter 'X' to cancel");
        }

        //pass this to the experiment processor
        System.out.println("Processing...");
        return new ArrayList<String>(Arrays.asList(experiment));
    }

    @Override
    public String getType() {
        return "process reagent";
    }

    private Set<String> printValidReagentExperiments(){
        //This should call the database and add all the strings to this set
        Set<String> output = new HashSet<>();
        output.add("reagentExp1");
        output.add("reagentExp2");
        output.add("reagentExp3");
        output.add("reagentExp4");
        output.add("reagentExp5");
        for(String experiment : output){
            System.out.println(experiment);
        }
        return output;

    }
}
