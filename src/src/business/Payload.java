package business;

import business.Processable;
import org.json.JSONObject;

import java.util.List;

public class Payload implements Processable {
    List<JSONObject> payloadObjects;

    @Override
    public JSONObject process() {
        return null;
    }
}

