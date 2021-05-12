package business;

import business.models.*;
import data.CommunicationSubsystem;
import data.LinkerManager;
import org.json.JSONObject;
import presentation.InputReader;
import presentation.UIProcess;


import java.io.IOException;
import java.util.*;

public class JUMPSystem {

    public static void main(String[] args) throws IOException {
        //Communications
        CommunicationSubsystem CS = new CommunicationSubsystem(1024);
        CommunicationObserver obs = new CommunicationObserver(CS);
        CS.startServer();

        //UI
        UIProcess nextUIProcess;
        InputReader console = new InputReader();

        //business.models.Command Execution
        Queue<BusinessProcessContainer> queue = new LinkedList<>();
        ExperimentCreator experimentCreator = new ExperimentCreator();
        ExperimentChecker experimentChecker = new ExperimentChecker();

        //Database Management
        LinkerManager linkerManager = LinkerManager.getInstance();
        Map<String, Supply> inventory;
        Map<String, Capability> capabilities;
        Map<String, Experiment> experiments;

        while(true){
            obs.update();
            inventory = linkerManager.getSupplyModels();
            capabilities = linkerManager.getCapabilityModels();
            experiments = linkerManager.getExperimentModels();

            while (obs.hasNext()) {
                JSONObject report = obs.next();
                ReportParser.parseReport(report, inventory, capabilities, experiments);
                System.out.println("Report received: " + report.toString());
            }

            linkerManager.updateSupplyGroup(inventory);
            linkerManager.updateCapabilityGroup(capabilities);
            linkerManager.updateExperimentGroup(experiments);
            experimentChecker.updateCheckState(capabilities, inventory);

            nextUIProcess = console.getNextCommand();
            nextUIProcess.execute(console.reader, queue);

            BusinessProcessContainer nextBusinessProcess = queue.poll();
            String[] typeInfo = nextBusinessProcess.getType().split(" ");

            if(typeInfo[0].equals("make")){

                Experiment newExperiment = experimentCreator.makeNewExperiment(typeInfo[1], nextBusinessProcess.getParams());
                if(newExperiment == null) System.out.println("Failed to create new experiment");

                boolean isValid = experimentChecker.checkExperiment(typeInfo[1], nextBusinessProcess.getParams());
                if(isValid) System.out.println("Experiment doable!");
                else System.out.println("Experiment impossible with current system state");
                linkerManager.add(newExperiment);
            }
            else if(typeInfo[0].equals("process")){
                JSONObject processedExperiment = experiments.get(nextBusinessProcess.getParams().get(0)).process();
                Payload payload = new Payload();
                payload.add(processedExperiment);
                CS.addPayload(payload.process());
            }
            else if(typeInfo[0].equals("makeMacro")){
                List<String> macroParams = nextBusinessProcess.getParams();
                //0th index is macro name
                //1 -> nth indices are commands to be added to the macro


                List<String> params = new ArrayList<>();
                //0th index has the parameters for the 0th command

                for(String command : macroParams){
                    if(command.equals(macroParams.get(0))) continue;

                    //TODO: get needed params from the database using command as key
                    String neededParams = "x,y,z";
                    if(neededParams.isEmpty()) {
                        params.add(null);
                        continue;
                    }

                    System.out.println("Enter comma delimited parameters for C1 (" +neededParams+ "): ");
                    params.add(console.nextLine());

                }


                //TODO: Work with the inputs
                Macro newMacro = new Macro(macroParams.get(0));
                newMacro.makeCommandList(macroParams,params);
            }
            else if(typeInfo[0].equals("processMacro")){


            } else if(typeInfo[0].equals("editMacro")){

            }
            else continue;
        }
        }
    }

