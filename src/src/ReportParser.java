import org.json.JSONArray;
import org.json.JSONObject;

public class ReportParser {
    public static void parseReport(JSONObject json, ){
        JSONObject returnStatus = json.getJSONObject("return-status");

        updateInventory(returnStatus.getJSONArray("inventory"));
        updateCapabilities(returnStatus.getJSONArray("capabilities"));
        updateExperimentStatus(returnStatus.getJSONArray("experiment_status"));
    }

    private static void updateInventory(JSONArray inventoryJSON){
        for(int i = 0; i < inventoryJSON.length(); i++){
            JSONObject temp = inventoryJSON.getJSONObject(i);
            for(String inventoryItem: temp.keySet()){
                temp.get(inventoryItem);
            }
        }
    }

    private static void updateCapabilities(JSONArray capabilitiesJSON){
        for(int i = 0; i < capabilitiesJSON.length(); i++){
            JSONObject temp = capabilitiesJSON.getJSONObject(i);
            for(String capability: temp.keySet()){
                temp.get(capability);
            }
        }
    }

    private static void updateExperimentStatus(JSONArray experimentJSON){
        for(int i = 0; i < experimentJSON.length(); i++){
            JSONObject temp = experimentJSON.getJSONObject(i);
            for(String experiment: temp.keySet()){
                temp.get(experiment);
            }
        }
    }
}
