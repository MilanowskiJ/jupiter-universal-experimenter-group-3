import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;


import static java.lang.System.exit;

public class ExitConsole implements Cmd {

    public ArrayList<String> execute(BufferedReader reader) throws IOException {
        exit(0);
        return null;
    }

    @Override
    public String getType() {
        return "exit console";
    }
}
