import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.exit;

public class UI {
    private static class InputReader {
        BufferedReader reader;
        String commandList = "\nmakeExperiment [ExperimentID] \n runExperiment [ExperimentID] \n exit";
        public InputReader(){
            reader = new BufferedReader(new InputStreamReader(System.in));
        }

        public Cmd getNextCommand() throws IOException {
            System.out.print(">");
            String[] inputStr = reader.readLine().split(" ");
            Cmd output = null;

            if(inputStr[0].equals("makeExperiment")) output = new MakeExperiment();
            else if(inputStr[0].equals("runExperiment")) output = new RunExperiment();
            else if(inputStr[0].equals("exit")) output = new ExitConsole();

            boolean success = false;
            if(output != null) success = output.setParams(inputStr);

            if (!success) {
                System.out.println("Invalid command given. Valid commands are: " + this.commandList);
                output = getNextCommand();
            }
            return output;
        }



    }



    //BEGIN CMD CODE============================================================================================
    interface  Cmd{
        public void execute();
        public boolean setParams(String[] newParams);
    }

    private static class MakeExperiment implements Cmd {
        String[] params;

        public void execute(){
            System.out.println("Made Experiment " + this.params[1]);
        }

        public boolean setParams(String[] newParams){
            this.params = newParams;
            if(newParams.length != 2) return false;
            return true;
        }

    }

    private static class RunExperiment implements Cmd {
        String[] params;

        public void execute() {
            System.out.println("Running experiment " + this.params[1]);
            System.out.println("Finished running experiment " + this.params[1]);

        }

        public boolean setParams(String[] newParams){
            this.params = newParams;
            if(newParams.length != 2) return false;
            return true;
        }

    }

    private static class ExitConsole implements Cmd {
        String[] params;

        public void execute(){
            exit(0);
        }

        public boolean setParams(String[] newParams){
            this.params = newParams;
            if(newParams.length != 1) return false;
            return true;
        }

    }
    //END CMD CODE============================================================================================


    public static void main(String[] args) throws IOException {
        Cmd nextCmd;
        InputReader console = new InputReader();

        while(true){
            nextCmd = console.getNextCommand();
            nextCmd.execute();
        }
    } 

}

