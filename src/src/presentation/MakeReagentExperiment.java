package presentation;

import business.BusinessProcessContainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class MakeReagentExperiment implements UIProcess {
    String reagent;
    String sampleToApplyTo;
    String reagentQuantity;
    String measurementsToTake;
    String experimentID;

    public void execute(BufferedReader reader, Queue<BusinessProcessContainer> queue) throws IOException {
        String[] inputStr;
        boolean exited = false;
        ArrayList<String> output = new ArrayList<>();

        while(true) {
            System.out.println("Creating new reagent-based experiment...");
            System.out.print("Please Enter business.models.Experiment Specification: ExperimentID\n>");
            experimentID = reader.readLine();

            System.out.print("Please Enter business.models.Experiment Specification: Reagent to apply\n>");
            reagent = reader.readLine();

            System.out.print("Please Enter business.models.Experiment Specification: Quantity of reagent to apply\n>");
            reagentQuantity = reader.readLine();

            System.out.print("Please Enter business.models.Experiment Specification: business.models.Sample to apply reagent to\n>");
            sampleToApplyTo = reader.readLine();

            System.out.print("Please Enter business.models.Experiment Specification: measurementsToTake (seperate multiple measurements using a comma)\n>");
            measurementsToTake = reader.readLine();

            System.out.println("business.models.Experiment Specification Entered:\n ExperimentID: " + experimentID +
                    "\nReagent to apply: " + reagent +
                    "\nQuantity of reagent: " + reagentQuantity +
                    "\nbusiness.models.Sample to apply reagent to: " + sampleToApplyTo +
                    "\nMeasurements to take:" + measurementsToTake);
            System.out.print("Validate? (v)\n>");
            inputStr = reader.readLine().split(" ");
            if (inputStr[0].toUpperCase().equals("V")){
                exited = true;
                break;
            }
        }
        if(exited) System.out.println("business.models.Experiment Doable. \nMade business.models.Experiment " + experimentID);
        else {
            System.out.println("business.models.Experiment " + experimentID + " was not validated, cancelling creation.");
            return;
        }

            List<String> params = new ArrayList<>(Arrays.asList(reagent, sampleToApplyTo, reagentQuantity, measurementsToTake, experimentID));
            BusinessProcessContainer newProcess = new BusinessProcessContainer("make reagent", params);
            queue.add(newProcess);
    }

    public String getType() {
        return "make reagent";
    }
}
