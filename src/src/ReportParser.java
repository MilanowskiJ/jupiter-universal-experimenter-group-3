import org.json.JSONArray;
import org.json.JSONObject;

public class ReportParser {
    public static void parseReport(JSONObject json){
        JSONObject returnStatus = json.getJSONObject("return-status");

        updateInventory(returnStatus.getJSONArray("inventory"));
        updateCapabilities(returnStatus.getJSONArray("capabilities"));
        updateExperimentStatus(returnStatus.getJSONArray("experiment_status"));
    }

    private static void updateInventory(JSONArray inventoryJSON){
        for(int i = 0; i < inventoryJSON.length(); i++){
            inventoryJSON.getJSONObject(i);
        }
    }

    private static void updateCapabilities(JSONArray capabilitiesJSON){
        for(int i = 0; i < capabilitiesJSON.length(); i++){
            JSONObject temp = capabilitiesJSON.getJSONObject(i);
            temp
        }
    }

    private static void updateExperimentStatus(JSONArray experimentJSON){
        for(int i = 0; i < experimentJSON.length(); i++){
            experimentJSON.getJSONObject(i);
        }
    }
}
