package PresentationLayer;

import java.io.BufferedReader;
import java.io.IOException;

public class MakeComplexExperiment implements Cmd{
    String experimentID;
    String whatToDo;
    String quantity;
    String supplyItem;
    String target;

    @Override
    public boolean execute(BufferedReader reader) throws IOException {
        boolean exited = false;

        String[] inputStr;

        while(true) {
            System.out.println("Creating new complex experiment...");
            System.out.print("Please Enter Experiment Specification: What to do (verb)\n>");
            whatToDo = reader.readLine();

            System.out.print("Please Enter Experiment Specification: Quantity\n>");
            quantity = reader.readLine();

            System.out.print("Please Enter Experiment Specification: Supply Item \n>");
            supplyItem = reader.readLine();

            System.out.print("Please Enter Experiment Specification: Target \n>");
            target = reader.readLine();

            System.out.println("Experiment Specification Entered:\nWhat to do: " + whatToDo + "\nQuantity: " + quantity + "\nSupply item: " + supplyItem + "\nTarget: " + target);
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
