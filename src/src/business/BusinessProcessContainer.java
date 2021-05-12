package business;

import java.util.List;

public class BusinessProcessContainer {
    private String type;
    private List<String> params;
    private List<String> params2;

    public BusinessProcessContainer(String type, List<String> params){
        this.type = type;
        this.params = params;

    }

    public BusinessProcessContainer(String type, List<String> params, List<String> params2){
        this.type = type;
        this.params = params;
        this.params2 = params2;
    }

    public String getType(){
        return this.type;
    }

    public List<String> getParams(){
        return this.params;
    }

    public List<String> getParams2(){ return this.params2;}
}
