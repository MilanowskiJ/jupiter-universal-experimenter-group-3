import java.util.List;

public class ExperimentChecker {


    public ExperimentChecker(){

    }

    public boolean checkSampleExperiment(){
        //Just return if status is "Operational" for all of these (in database)
        //check Arm-1
        //check Arm-2
        //check Arm-3
        //check test tube available quantity
        //check ejector
        return false;
    }

    public boolean checkReagentExperiment(String reagent, String sample, String reagentQuantity){
        //check Arm-2
        //check pipet
        //check reagent quantity
        //check sample quantity
        return false;
    }

    public boolean checkComplexExperiment(String supplyItem, String supplyQuantity, String sample){
        //check summplyItem quantity
        //check sample quantity
        //check based on verb? maybe just hold the L on that and focus on functionality

        return false;
    }


    public boolean checkExperiment(String type, List<String> params) {
        if(type.equals("complex"))return checkComplexExperiment(params.get(3), params.get(2), params.get(4));
        else if(type.equals("sample")) return checkSampleExperiment();
        else if(type.equals("reagent")) return checkReagentExperiment(params.get(0), params.get(1), params.get(2));
        else return false;

    }

}
