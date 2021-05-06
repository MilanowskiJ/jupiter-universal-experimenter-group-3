import org.json.JSONObject;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class JUMPSystem {

    public static void main(String[] args) throws IOException {
        CommunicationSubsystem CS = new CommunicationSubsystem(1024);
        CommunicationObserver obs = new CommunicationObserver(CS);
        UIProcess nextUIProcess;
        InputReader console = new InputReader();
        Queue<BusinessProcessContainer> queue = new LinkedList<>();
        ExperimentCreator experimentCreator = new ExperimentCreator();

        while(true){
            while (obs.hasNext()) {
                JSONObject report = obs.next();
                System.out.println("Report received");
            }

            nextUIProcess = console.getNextCommand();
            nextUIProcess.execute(console.reader, queue);

            BusinessProcessContainer nextBusinessProcess = queue.poll();
            String[] typeInfo = nextBusinessProcess.getType().split(" ");
            if(typeInfo[0].equals("make")){
                System.out.println("Params are: " + nextBusinessProcess.getParams().toString());
                System.out.println("And type info is: " + typeInfo[1]);

                Experiment newExperiment = experimentCreator.makeNewExperiment(typeInfo[1], nextBusinessProcess.getParams());
                if(newExperiment == null) System.out.println("Failed to create new experiment");
                System.out.println(newExperiment.toString());
                //pass new experiment to the database
            }
            else if(typeInfo[0].equals("process")){
                //insert process logic here, nextBusinessProcess.getParams().get(0) has the experiment ID to process
            }
            else continue;
        }
        }
    }

