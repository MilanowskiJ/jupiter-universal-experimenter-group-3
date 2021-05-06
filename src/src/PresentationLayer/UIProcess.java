package PresentationLayer;

import java.io.BufferedReader;
import java.io.IOException;

public interface  Cmd{
    // public void execute();
    public boolean execute(BufferedReader reader) throws IOException;
}
