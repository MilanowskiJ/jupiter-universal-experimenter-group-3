package presentation;

import business.*;
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
        Map<String, Macro> macros;
        Map<String, Command> commandList;

        while(true){
            obs.update();
            inventory = linkerManager.getSupplyModels();
            capabilities = linkerManager.getCapabilityModels();
            experiments = linkerManager.getExperimentModels();
            macros = linkerManager.getMacroModels();
            commandList = linkerManager.getCommandModels();

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
            if(nextBusinessProcess == null) continue;

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
                List<String> commands = nextBusinessProcess.getParams();
                //0th is macro name, others are commands (in order)

                List<String> params = nextBusinessProcess.getParams2();
                //params for commands(in order)

                System.out.println("Commands: "+commands.toString());
                System.out.println("Params: "+params.toString());
                Macro newMacro = new Macro(commands.get(0));
                newMacro.makeCommandList(commands,params);

                linkerManager.add(newMacro);
            }
            else if(typeInfo[0].equals("makeComplex")){
                List<String> commands = nextBusinessProcess.getParams();
                //0th is experiment name, others are commands/macros (in order)

                List<String> params = nextBusinessProcess.getParams2();
                //params for commands(in order), nulls whenever no input needed

                System.out.println("commands: " + commands.toString());
                System.out.println("params: " + params.toString());

                ComplexExperiment complexExperiment = new ComplexExperiment(commands.get(0), "M", commands.get(0), "N", "N/A");

                for(int i = 1; i < commands.size(); i++){
                    if(macros.containsKey(commands.get(i))){
                        Macro temp = macros.get(commands.get(i));
                        complexExperiment.addExperimentStep(temp);
                    }else{
                        Command oldCommand = commandList.get(commands.get(i));
                        Command newCommand = new Command(oldCommand.getID(), oldCommand.getName(), oldCommand.getParameters());
                        newCommand.setParameterValues(params.get(i-1));
                        complexExperiment.addExperimentStep(newCommand);
                    }
                }

                linkerManager.add(complexExperiment);
            } else if(typeInfo[0].equals("editMacro")){
                List<String> commands = nextBusinessProcess.getParams();
                //0th is macro name, others are commands (in order)

                List<String> params = nextBusinessProcess.getParams2();
                //params for commands(in order)

                Macro newMacro = new Macro(commands.get(0));
                newMacro.makeCommandList(commands,params);

                linkerManager.update(newMacro);
            }
            else if(typeInfo[0].equals("processPayload")){
                List<String> params = nextBusinessProcess.getParams();
                //0th is payload name, others are experiments to be added to the payload
                Payload payload = new Payload();
                for(int i = 1; i < params.size(); i++){
                    payload.add(experiments.get(params.get(i)).process());
                }

                System.out.println(params.get(0).toString() + ": ");
                System.out.println(payload.process().toString());
            }
            else continue;
        }
        }
    }

