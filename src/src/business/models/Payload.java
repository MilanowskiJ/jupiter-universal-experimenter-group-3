package business.models;

import business.models.Processable;
import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Payload implements Processable {
    List<JSONObject> payloadObjects;

    public Payload(){
        payloadObjects = new ArrayList<>();
    }

    @Override
    public JSONObject process() {
        JSONObject payload = new JSONObject();
        JSONObject payloadGrouper = new JSONObject();

        for(int i = 0; i < payloadObjects.size(); i++){
            JSONObject current = payloadObjects.get(i);
            payloadGrouper.put("experiment" + i, current);
        }

        payload.put("payload", payloadGrouper);

        return payload;
    }

    public void add(JSONObject object){
        payloadObjects.add(object);
    }
}

