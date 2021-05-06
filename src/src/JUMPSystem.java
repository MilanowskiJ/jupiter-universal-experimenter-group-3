import PresentationLayer.BusinessProcessContainer;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class JUMPSystem {


    public static void main(String[] args) throws IOException {
        UIProcess nextUIProcess;
        InputReader console = new InputReader();
        Queue<BusinessProcessContainer> queue = new LinkedList<>();
        while(true){
            nextUIProcess = console.getNextCommand();
            nextUIProcess.execute(console.reader, queue);

            BusinessProcessContainer nextBusinessProcess = queue.poll();
            String[] typeInfo = nextBusinessProcess.getType().split(" ");
            if(typeInfo[0].equals("make")){
                //Experiment newExperiment = ExperimentCreator.makeExperiment(typeInfo[1], nextBusinessProcess.getParams());
                //pass new experiment to the database
            }
            else if(typeInfo[0].equals("process")){
                //insert process logic here, nextBusinessProcess.getParams().get(0) has the experiment ID to process
            }
            else continue;
        }
        }
    }

