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

    public boolean checkReagentExperiment(String reagent, String sample){
        //check Arm-2
        //check pipet
        //check reagent quantity
        //check sample quantity
        return false;
    }

    public boolean checkComplexExperiment(String supplyItem, String supplyQuantity, String sample){
        //check summplyItem quantity
        //check sample quantity
        //check based on verb?

        return false;
    }


}
