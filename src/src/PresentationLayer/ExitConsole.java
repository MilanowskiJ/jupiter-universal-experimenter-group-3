package PresentationLayer;

import java.io.BufferedReader;
import java.io.IOException;

import static java.lang.System.exit;

public class ExitConsole implements Cmd {

    public boolean execute(BufferedReader reader) throws IOException {
        exit(0);
        return true;
    }



}
