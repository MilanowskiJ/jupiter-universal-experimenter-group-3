package business;

import business.models.Capability;
import business.models.Supply;

import java.util.List;
import java.util.Map;

public class ExperimentChecker {
    private Map<String, Capability> capabilityMap;
    private Map<String, Supply> supplyMap;

    public ExperimentChecker(){
        capabilityMap = null;
        supplyMap = null;
    }

    public void updateCheckState(Map<String, Capability> capabilityMap, Map<String, Supply> supplyMap){
        this.capabilityMap = capabilityMap;
        this.supplyMap = supplyMap;
    }

    public boolean checkSampleExperiment(){
        //check Arm-1
        if(capabilityMap.get("Arm-1") == null)
            return false;
        if(capabilityMap.get("Arm-1").getStatus().equals("Failed"))
            return false;

        //check Arm-2
        if(capabilityMap.get("Arm-2") == null)
            return false;
        if(capabilityMap.get("Arm-2").getStatus().equals("Failed"))
            return false;

        //check test tube available quantity
        if(supplyMap.get("test tube") == null)
            return false;
        if(supplyMap.get("test tube").getQuantityAvailable() < 1)
            return false;

        //check ejector
        if(capabilityMap.get("E1") == null)
            return false;
        if(capabilityMap.get("E1").getStatus().equals("Failed"))
            return false;

        return true;
    }

    public boolean checkReagentExperiment(String reagent, String sample, String reagentQuantity){
        //check Arm-2
        if(capabilityMap.get("Arm-2") == null)
            return false;
        if(capabilityMap.get("Arm-2").getStatus().equals("Failed"))
            return false;

        //check pipet
        if(capabilityMap.get("T3") == null)
            return false;
        if(capabilityMap.get("T3").getStatus().equals("Failed"))
            return false;

        //check reagent quantity
        if(supplyMap.get(reagent) == null)
            return false;
        if(supplyMap.get(reagent).getQuantityAvailable() < Integer.parseInt(reagentQuantity))
            return false;

        //check sample quantity
        if(supplyMap.get(sample) == null)
            return false;
        if(supplyMap.get(sample).getQuantityAvailable() < 1)
            return false;

        return true;
    }

    public boolean checkComplexExperiment(String supplyItem, String supplyQuantity, String sample){
        if(supplyMap.get(supplyItem) == null)
            return false;
        if(supplyMap.get(supplyItem).getQuantityAvailable() < Integer.parseInt(supplyQuantity))
            return false;
        if(supplyMap.get(sample) == null)
            return false;

        return true;
    }


    public boolean checkExperiment(String type, List<String> params) {
        if(type.equals("complex"))return checkComplexExperiment(params.get(3), params.get(2), params.get(4));
        else if(type.equals("sample")) return checkSampleExperiment();
        else if(type.equals("reagent")) return checkReagentExperiment(params.get(0), params.get(1), params.get(2));
        else return false;

    }

}
