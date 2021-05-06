import java.io.BufferedReader;
import java.io.IOException;
import java.util.Queue;

public class ExitConsole implements UIProcess {

    public void execute(BufferedReader reader, Queue<PresentationLayer.BusinessProcessContainer> queue) throws IOException {
        PresentationLayer.BusinessProcessContainer newProcess = new PresentationLayer.BusinessProcessContainer("exit", null);
        queue.add(newProcess);
        return;
    }



}
