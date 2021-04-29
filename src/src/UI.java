import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

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
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String[] inputStr;
            Experiment output;

            System.out.print("Please Enter an Experiment Type: Sample Only (s), Reagent-Based (r), or Complex (c)\n>");
            try{
                inputStr = reader.readLine().split(" ");
                if(inputStr[0].toUpperCase().equals("S")) {createSampleOnly(reader);}
                else if (inputStr[0].toUpperCase().equals("R")) {createReagentBased(reader);}
                else if (inputStr[0].toUpperCase().equals("C")) {createComplex(reader);}
            } catch (IOException e) {return;}

            System.out.println("Experiment Doable. \nMade Experiment " + this.params[1]);
        }

        /*
            reagent is the quantity of each reagent, time to wait, and what detail
            about sample it’s applied to, and measurements to take,

            complex is a manually entered set of commands of
            the form {verb, quantity, supply item, target}
         */

        private void createSampleOnly(BufferedReader reader) throws IOException{
            String whatToSample;
            String howMuchToSample;
            String whereToSample;
            String[] inputStr;

            while(true) {
                System.out.print("Please Enter Experiment Specification: What to Sample\n>");
                whatToSample = reader.readLine();

                System.out.print("Please Enter Experiment Specification: How Much to Sample\n>");
                howMuchToSample = reader.readLine();

                System.out.print("Please Enter Experiment Specification: Where to Sample\n>");
                whereToSample = reader.readLine();

                System.out.println("Experiment Specification Entered:\nWhat to Sample: " + whatToSample + "\nHow Much to Sample: " + howMuchToSample + "\nWhere to Sample: " + whereToSample);
                System.out.print("Validate? (v)\n>");
                inputStr = reader.readLine().split(" ");
                if (inputStr[0].toUpperCase().equals("V")){
                    break;
                }
            }
        }

        private void createReagentBased(BufferedReader reader) throws IOException{
            String whatToSample;
            String howMuchToSample;
            String whereToSample;
            String[] inputStr;

            while(true) {
                System.out.print("Please Enter Experiment Specification: What to Sample\n>");
                whatToSample = reader.readLine();

                System.out.print("Please Enter Experiment Specification: How Much to Sample\n>");
                howMuchToSample = reader.readLine();

                System.out.print("Please Enter Experiment Specification: Where to Sample\n>");
                whereToSample = reader.readLine();

                System.out.println("Experiment Specification Entered:\nWhat to Sample: " + whatToSample + "\nHow Much to Sample: " + howMuchToSample + "\nWhere to Sample: " + whereToSample);
                System.out.print("Validate? (v)\n>");
                inputStr = reader.readLine().split(" ");
                if (inputStr[0].toUpperCase().equals("V")){
                    break;
                }
            }
        }

        private void createComplex(BufferedReader reader) throws IOException{
            String whatToSample;
            String howMuchToSample;
            String whereToSample;
            String[] inputStr;

            while(true) {
                System.out.print("Please Enter Experiment Specification: What to Sample\n>");
                whatToSample = reader.readLine();

                System.out.print("Please Enter Experiment Specification: How Much to Sample\n>");
                howMuchToSample = reader.readLine();

                System.out.print("Please Enter Experiment Specification: Where to Sample\n>");
                whereToSample = reader.readLine();

                System.out.println("Experiment Specification Entered:\nWhat to Sample: " + whatToSample + "\nHow Much to Sample: " + howMuchToSample + "\nWhere to Sample: " + whereToSample);
                System.out.print("Validate? (v)\n>");
                inputStr = reader.readLine().split(" ");
                if (inputStr[0].toUpperCase().equals("V")){
                    break;
                }
            }
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

