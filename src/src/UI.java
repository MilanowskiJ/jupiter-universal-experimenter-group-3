import PresentationLayer.BusinessProcessContainer;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class UI {




    //BEGIN CMD CODE============================================================================================








//deprecated for this milestone
//    private static class RunExperiment implements Cmd {
//        String[] params;
//
//        public void execute() {
//            System.out.println("Running experiment " + this.params[1]);
//            System.out.println("Finished running experiment " + this.params[1]);
//
//        }
//
//        public boolean setParams(String[] newParams){
//            this.params = newParams;
//            if(newParams.length != 2) return false;
//            return true;
//        }
//
//    }


    //END CMD CODE============================================================================================


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

