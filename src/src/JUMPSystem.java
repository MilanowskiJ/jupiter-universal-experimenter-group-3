import org.json.JSONObject;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class JUMPSystem {

    public static void main(String[] args) throws IOException {
        CommunicationSubsystem CS = new CommunicationSubsystem(1024);
        CS.startServer();
        CommunicationObserver obs = new CommunicationObserver(CS);
        UIProcess nextUIProcess;
        InputReader console = new InputReader();
        Queue<BusinessProcessContainer> queue = new LinkedList<>();
        ExperimentCreator experimentCreator = new ExperimentCreator();
        ExperimentChecker experimentChecker = new ExperimentChecker();

        while(true){
            while (obs.hasNext()) {
                JSONObject report = obs.next();
                System.out.println("Report received: " + report.toString());
            }

            nextUIProcess = console.getNextCommand();
            nextUIProcess.execute(console.reader, queue);

            BusinessProcessContainer nextBusinessProcess = queue.poll();
            String[] typeInfo = nextBusinessProcess.getType().split(" ");
            if(typeInfo[0].equals("make")){

                Experiment newExperiment = experimentCreator.makeNewExperiment(typeInfo[1], nextBusinessProcess.getParams());
                if(newExperiment == null) System.out.println("Failed to create new experiment");

                boolean isValid = experimentChecker.checkExperiment(typeInfo[1], nextBusinessProcess.getParams());
            }
            else if(typeInfo[0].equals("process")){
                //insert process logic here, nextBusinessProcess.getParams().get(0) has the experiment ID to process
            }
            else continue;
        }
        }
    }

