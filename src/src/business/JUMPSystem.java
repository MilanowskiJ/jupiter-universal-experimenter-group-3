package business;

import business.*;
import data.CommunicationSubsystem;
import data.LinkerManager;
import org.json.JSONObject;
import presentation.InputReader;
import presentation.UIProcess;


import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class JUMPSystem {

    public static void main(String[] args) throws IOException {
        //Communications
        CommunicationSubsystem CS = new CommunicationSubsystem(1024);
        CommunicationObserver obs = new CommunicationObserver(CS);
        CS.startServer();

        //UI
        UIProcess nextUIProcess;
        InputReader console = new InputReader();

        //business.Command Execution
        Queue<BusinessProcessContainer> queue = new LinkedList<>();
        ExperimentCreator experimentCreator = new ExperimentCreator();
        ExperimentChecker experimentChecker = new ExperimentChecker();

        //Database Management
        LinkerManager linkerManager = new LinkerManager();
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
            }
            else if(typeInfo[0].equals("process")){
                //insert process logic here, nextBusinessProcess.getParams().get(0) has the experiment ID to process
            }
            else continue;
        }
        }
    }

