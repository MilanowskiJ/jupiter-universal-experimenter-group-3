package presentation;

import business.BusinessProcessContainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ProcessPayload implements UIProcess{
    @Override
    public void execute(BufferedReader reader, Queue<BusinessProcessContainer> queue) throws IOException {
        String payloadName;
        List<String> output = new ArrayList<>();
        System.out.println("Enter payload name: ");
        payloadName = reader.readLine();
        output.add(payloadName);

        while(true) {
            System.out.print("Add experiment to payload by entering its ID (or enter X to continue): \n>");
            String readString = reader.readLine();
            if (readString.toUpperCase().equals("X")) break;
            else output.add(readString);
        }


        int expCounter = 0;
        for(String str : output){
            if(expCounter == 0) System.out.println("Payload name: " + str);
            else System.out.println("Experiment "+expCounter+": "+str);
            expCounter++;
        }

        if(expCounter == 1){
            System.out.println("Payload must have at least one experiment, aborting operation");
            return;
        }

        System.out.print("Validate? (v)\n>");
        if (!reader.readLine().split(" ")[0].toUpperCase().equals("V")){
            System.out.println("Payload " + output.get(0) + " was not validated, cancelling creation.");
            return;
        }

        queue.add(new BusinessProcessContainer("processPayload", output));
    }
}
