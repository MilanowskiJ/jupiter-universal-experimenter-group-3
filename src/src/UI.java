import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;

import static java.lang.System.exit;

public class UI {
    private static class InputReader {
        BufferedReader reader;
        String commandList = "\nmakeSampleExperiment" +
                " makeReagentExperiment\n" +
                "makeComplexExperiment\n"+
                "runExperiment\n"+
                " exit\n";

        public InputReader(){
            reader = new BufferedReader(new InputStreamReader(System.in));
        }

        public Cmd getNextCommand() throws IOException {
            System.out.print(">");
            String[] inputStr = reader.readLine().split(" ");
            Cmd output = null;

            if(inputStr[0].equals("makeReagentExperiment")) output = new MakeReagentExperiment();
            if(inputStr[0].equals("makeSampleExperiment")) output = new MakeSampleExperiment();
            if(inputStr[0].equals("makeComplexExperiment")) output = new MakeComplexExperiment();
            else if(inputStr[0].equals("runExperiment")) output = new RunExperiment();
            else if(inputStr[0].equals("exit")) output = new ExitConsole();
            else
            {
                System.out.println("Invalid command given. Valid commands are: " + this.commandList);
                output = getNextCommand();
            }
            return output;
        }



    }



    //BEGIN CMD CODE============================================================================================
    interface  Cmd{
       // public void execute();
        public boolean execute(BufferedReader reader) throws IOException;
    }
    private static class MakeReagentExperiment implements Cmd{
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

    private static class MakeSampleExperiment implements Cmd{
        String whatToSample;
        String howMuchToSample;
        String whereToSample;
        String experimentID;

        @Override
        public boolean execute(BufferedReader reader) throws IOException {
            boolean exited = false;

            String[] inputStr;

            while(true) {
                System.out.println("Creating new sample-based experiment...");
                System.out.print("Please Enter Experiment Specification: ExperimentID\n>");
                experimentID = reader.readLine();

                System.out.print("Please Enter Experiment Specification: What to Sample\n>");
                whatToSample = reader.readLine();

                System.out.print("Please Enter Experiment Specification: How Much to Sample\n>");
                howMuchToSample = reader.readLine();

                System.out.print("Please Enter Experiment Specification: Where to Sample\n>");
                whereToSample = reader.readLine();

                System.out.println("Experiment Specification Entered:\n ExperimentID: " + experimentID + "\nWhat to Sample: " + whatToSample + "\nHow Much to Sample: " + howMuchToSample + "\nWhere to Sample: " + whereToSample);
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

    private static class MakeComplexExperiment implements Cmd{
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




















//old deprecated MakeExperiment class from demo


//    private static class MakeExperiment implements Cmd {
//        String[] params;
//
//        public void execute(){
//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//            String[] inputStr;
//            Experiment output;
//
//            System.out.print("Please Enter an Experiment Type: Sample Only (s), Reagent-Based (r), or Complex (c)\n>");
//            try{
//                inputStr = reader.readLine().split(" ");
//                if(inputStr[0].toUpperCase().equals("S")) {createSampleOnly(reader);}
//                else if (inputStr[0].toUpperCase().equals("R")) {createReagentBased(reader);}
//                else if (inputStr[0].toUpperCase().equals("C")) {createComplex(reader);}
//            } catch (IOException e) {return;}
//
//            System.out.println("Experiment Doable. \nMade Experiment " + this.params[1]);
//        }
//
//        /*
//            reagent is the quantity of each reagent, time to wait, and what detail
//            about sample it’s applied to, and measurements to take,
//
//            complex is a manually entered set of commands of
//            the form {verb, quantity, supply item, target}
//         */
//
//        private void createSampleOnly(BufferedReader reader) throws IOException{
//            String whatToSample;
//            String howMuchToSample;
//            String whereToSample;
//            String[] inputStr;
//
//            while(true) {
//                System.out.print("Please Enter Experiment Specification: What to Sample\n>");
//                whatToSample = reader.readLine();
//
//                System.out.print("Please Enter Experiment Specification: How Much to Sample\n>");
//                howMuchToSample = reader.readLine();
//
//                System.out.print("Please Enter Experiment Specification: Where to Sample\n>");
//                whereToSample = reader.readLine();
//
//                System.out.println("Experiment Specification Entered:\nWhat to Sample: " + whatToSample + "\nHow Much to Sample: " + howMuchToSample + "\nWhere to Sample: " + whereToSample);
//                System.out.print("Validate? (v)\n>");
//                inputStr = reader.readLine().split(" ");
//                if (inputStr[0].toUpperCase().equals("V")){
//                    break;
//                }
//            }
//        }
//
//        private void createReagentBased(BufferedReader reader) throws IOException{
//            String whatToSample;
//            String howMuchToSample;
//            String whereToSample;
//            String[] inputStr;
//
//            while(true) {
//                System.out.print("Please Enter Experiment Specification: What to Sample\n>");
//                whatToSample = reader.readLine();
//
//                System.out.print("Please Enter Experiment Specification: How Much to Sample\n>");
//                howMuchToSample = reader.readLine();
//
//                System.out.print("Please Enter Experiment Specification: Where to Sample\n>");
//                whereToSample = reader.readLine();
//
//                System.out.println("Experiment Specification Entered:\nWhat to Sample: " + whatToSample + "\nHow Much to Sample: " + howMuchToSample + "\nWhere to Sample: " + whereToSample);
//                System.out.print("Validate? (v)\n>");
//                inputStr = reader.readLine().split(" ");
//                if (inputStr[0].toUpperCase().equals("V")){
//                    break;
//                }
//            }
//        }
//
//        private void createComplex(BufferedReader reader) throws IOException{
//            String whatToSample;
//            String howMuchToSample;
//            String whereToSample;
//            String[] inputStr;
//
//            while(true) {
//                System.out.print("Please Enter Experiment Specification: What to Sample\n>");
//                whatToSample = reader.readLine();
//
//                System.out.print("Please Enter Experiment Specification: How Much to Sample\n>");
//                howMuchToSample = reader.readLine();
//
//                System.out.print("Please Enter Experiment Specification: Where to Sample\n>");
//                whereToSample = reader.readLine();
//
//                System.out.println("Experiment Specification Entered:\nWhat to Sample: " + whatToSample + "\nHow Much to Sample: " + howMuchToSample + "\nWhere to Sample: " + whereToSample);
//                System.out.print("Validate? (v)\n>");
//                inputStr = reader.readLine().split(" ");
//                if (inputStr[0].toUpperCase().equals("V")){
//                    break;
//                }
//            }
//        }
//
//        public boolean setParams(String[] newParams){
//            this.params = newParams;
//            if(newParams.length != 2) return false;
//            return true;
//        }
//
//    }

