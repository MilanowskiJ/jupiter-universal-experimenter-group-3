package business.models;

import org.json.JSONArray;

public class EmptyMacro implements MacroDecorator {

    @Override
    public void execute() {
    }

    public String toString() {
        return "";
    }

    @Override
    public void Process(JSONArray array) {

    }
}
