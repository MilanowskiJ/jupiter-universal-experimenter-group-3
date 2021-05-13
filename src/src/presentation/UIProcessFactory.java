package presentation;

public class UIProcessFactory extends AbstractUIProcessFactory {
    @Override
    UIProcess makeUIProcess(String userInput) {
        if(userInput.equals("makeReagentExperiment")) return new MakeReagentExperiment();
        else if(userInput.equals("makeSampleExperiment")) return new MakeSampleExperiment();
        else if(userInput.equals("makeComplexExperiment")) return new MakeComplexExperiment();
        else if(userInput.equals("processReagentExperiment")) return new ProcessReagentExperiment();
        else if(userInput.equals("processSampleExperiment")) return new ProcessSampleExperiment();
        else if(userInput.equals("makeMacro")) return new MakeMacro();
        else if(userInput.equals("editMacro")) return new EditMacro();
        else if(userInput.equals("exit")) return new ExitConsole();
        else return null;
    }
}
