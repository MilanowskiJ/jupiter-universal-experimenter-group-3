import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

public class ReportParser {
    public static void parseReport(JSONObject json,
                                   Map<String, Supply> inventory,
                                   Map<String, Capability> capabilities,
                                   Map<String, Experiment> experiments){
        JSONObject returnStatus = json.getJSONObject("return-status");

        updateInventory(returnStatus.getJSONArray("inventory"), inventory);
        updateCapabilities(returnStatus.getJSONArray("capabilities"), capabilities);
        updateExperimentStatus(returnStatus.getJSONArray("experiment_status"), experiments);
    }

    private static void updateInventory(JSONArray inventoryJSON, Map<String, Supply> inventory){
        for(int i = 0; i < inventoryJSON.length(); i++){
            JSONObject temp = inventoryJSON.getJSONObject(i);
            for(String inventoryItem: temp.keySet()){
                inventory.get(inventoryItem).setQuantityAvailable((Integer) temp.get(inventoryItem));
            }
        }
    }

    private static void updateCapabilities(JSONArray capabilitiesJSON, Map<String, Capability> capabilities){
        for(int i = 0; i < capabilitiesJSON.length(); i++){
            JSONObject temp = capabilitiesJSON.getJSONObject(i);
            for(String capability: temp.keySet()){
                capabilities.get(capability).setStatus((String) temp.get(capability));
            }
        }
    }

    private static void updateExperimentStatus(JSONArray experimentJSON, Map<String, Experiment> experiments){

        for(int i = 0; i < experimentJSON.length(); i++){
            JSONObject temp = experimentJSON.getJSONObject(i);
            for(String experiment: temp.keySet()){
                experiments.get(experiment).setComplete(decideExperiment((String) temp.get(experiment)));
            }
        }
    }

    private static String decideExperiment(String x) {if(x.equals("success")) return "Y"; else return "N";}
}
