package presentation;

import business.BusinessProcessContainer;
import presentation.UIProcess;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Queue;

public class ExitConsole implements UIProcess {

    public void execute(BufferedReader reader, Queue<BusinessProcessContainer> queue) throws IOException {
        BusinessProcessContainer newProcess = new BusinessProcessContainer("exit", null);
        queue.add(newProcess);
        return;
    }



}
