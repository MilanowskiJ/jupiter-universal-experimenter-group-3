package business;

import business.models.ComplexExperiment;
import business.models.Experiment;
import business.models.ReagentExperiment;
import business.models.SampleExperiment;

import java.util.List;

public class ExperimentCreator {
    public ExperimentCreator(){

    }
    public Experiment makeNewExperiment(String type, List<String> params){
        if(type.equals("sample")) return makeSampleExperiment(params);
        else if(type.equals("complex")) return makeComplexExperiment(params);
        else if(type.equals("reagent")) return makeReagentExperiment(params);
        else return null;

    }
    public SampleExperiment makeSampleExperiment(List<String> params){
        String experimentID = params.get(3);
        String whereToSample = params.get(2);
        String howMuchToSample = params.get(1);
        String whatToSample = params.get(0);
        SampleExperiment output = new SampleExperiment("Experiment"+experimentID, "M", experimentID, "F", "N/A");

        output.setWhere(whereToSample);
        output.setAmount(Integer.parseInt(howMuchToSample));
        output.setTarget(whatToSample);
        return output;
    }

    public ReagentExperiment makeReagentExperiment(List<String> params){
        String experimentID = params.get(4);
        String measurementsToTake = params.get(3);
        String reagentQuantity = params.get(2);
        String sampleToApplyTo = params.get(1);
        String reagent = params.get(0);
        ReagentExperiment output = new ReagentExperiment("E" + experimentID, "M", experimentID, "F", "N/A");
        output.setAmount(Integer.parseInt(reagentQuantity));
        output.setReagent(reagent);
        output.setSampleID(sampleToApplyTo);
        output.setMeasurements(measurementsToTake);


        return output;
    }
    public ComplexExperiment makeComplexExperiment(List<String> params){
        String target = params.get(4);
        String supplyItem = params.get(3);
        String quantity = params.get(2);
        String whatToDo = params.get(1);
        String experimentID = params.get(0);
        ComplexExperiment output = new ComplexExperiment("business.models.Experiment " + experimentID, "M", experimentID, "F", "N/A");

        output.setQuantity(quantity);
        output.setTarget(target);
        output.setSupplyItem(supplyItem);
        output.setWhatToDo(whatToDo);
        return output;
    }
}
