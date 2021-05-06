package PresentationLayer;

import java.util.List;

public class BusinessProcessContainer {
    private String type;
    private List<String> params;

    public BusinessProcessContainer(String type, List<String> params){
        this.type = type;
        this.params = params;
    }

    public String getType(){
        return this.type;
    }

    public List<String> getParams(){
        return this.params;
    }
}
