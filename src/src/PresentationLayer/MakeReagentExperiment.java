package PresentationLayer;

import java.io.BufferedReader;
import java.io.IOException;

public class MakeReagentExperiment implements Cmd{
    String reagent;
    String sampleToApplyTo;
    String reagentQuantity;
    String measurementsToTake;
    String experimentID;

    @Override
    public boolean execute(BufferedReader reader) throws IOException {
        String[] inputStr;
        boolean exited = false;
        while(true) {
            System.out.println("Creating new reagent-based experiment...");
            System.out.print("Please Enter Experiment Specification: ExperimentID\n>");
            experimentID = reader.readLine();

            System.out.print("Please Enter Experiment Specification: Reagent to apply\n>");
            reagent = reader.readLine();

            System.out.print("Please Enter Experiment Specification: Quantity of reagent to apply\n>");
            reagentQuantity = reader.readLine();

            System.out.print("Please Enter Experiment Specification: Sample to apply reagent to\n>");
            sampleToApplyTo = reader.readLine();

            System.out.print("Please Enter Experiment Specification: measurementsToTake (seperate multiple measurements using a comma)\n>");
            measurementsToTake = reader.readLine();

            System.out.println("Experiment Specification Entered:\n ExperimentID: " + experimentID +
                    "\nReagent to apply: " + reagent +
                    "\nQuantity of reagent: " + reagentQuantity +
                    "\nSample to apply reagent to: " + sampleToApplyTo +
                    "\nMeasurements to take:" + measurementsToTake);
            System.out.print("Validate? (v)\n>");
            inputStr = reader.readLine().split(" ");
            if (inputStr[0].toUpperCase().equals("V")){
                exited = true;
                break;
            }
        }
        if(exited) System.out.println("Experiment Doable. \nMade Experiment " + experimentID);
        else System.out.println("Experiment " + experimentID + " was not validated, cancelling creation.");
        return exited;
    }
}
