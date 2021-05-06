package presentation;

import business.BusinessProcessContainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Queue;

public interface UIProcess {
    // public void execute();
    public void execute(BufferedReader reader, Queue<BusinessProcessContainer> queue) throws IOException;
}
