package business.models;

import org.json.JSONArray;

public interface MacroDecorator {
    public void execute();
    public String toString();
    public void Process(JSONArray array);
}
