package PresentationLayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Queue;

import static java.lang.System.exit;

public class ExitConsole implements UIProcess {

    public void execute(BufferedReader reader, Queue<BusinessProcessContainer> queue) throws IOException {
        BusinessProcessContainer newProcess = new BusinessProcessContainer("exit", null);
        queue.add(newProcess);
        return;
    }



}
