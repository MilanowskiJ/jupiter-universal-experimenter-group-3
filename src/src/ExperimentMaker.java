import java.util.List;

public class ExperimentMaker {

    public static Experiment makeExperiment(List<String> params, String type){
        Experiment output = null;
        if(type.equals("sample")){
            output = new SampleExperiment(params.get(0), params.get(1),params.get(2), params.get(3));
        }else if(type.equals("reagent")){
            System.out.println("not yet supported");
        } else return null;
        return output;
    }
}