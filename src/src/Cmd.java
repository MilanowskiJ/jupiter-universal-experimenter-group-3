import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public interface  Cmd{
    //make the presetation layer not the business layer
    //

    public ArrayList<String> execute(BufferedReader reader) throws IOException;
    public String getType();
}
