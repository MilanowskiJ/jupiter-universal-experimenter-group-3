import java.io.IOException;
import java.util.List;

public class JUMPSystem {


    public static void main(String[] args) throws IOException {
        Cmd nextCmd;
        InputReader console = new InputReader();
        while(true){
            nextCmd = console.getNextCommand();
            String[] cmdInfo = nextCmd.getType().split(" ");

            if(cmdInfo[0].equals("make")){
                List<String> makeParams = nextCmd.execute(console.reader); //get the list of params to process
                if(makeParams == null) continue;

                Experiment newExperiment = ExperimentMaker.makeExperiment(makeParams, cmdInfo[1]);
                if(newExperiment == null) System.out.println("Failed to create experiment");
                else System.out.print(newExperiment.toString());
            }

            else if(cmdInfo[0].equals("process")){
                List<String> toProcess = nextCmd.execute(console.reader); //get the name of the string to process

                if(toProcess == null) continue;
                //pass to experiment processor

                String processedString = ExperimentProcessor.processExperiment(toProcess.get(0), cmdInfo[1]);
                System.out.println(processedString);
            } else{
                System.out.println("other");
                nextCmd.execute(console.reader) ;
            }

            //nextCmd.execute(console.reader);
        }
    }
}
