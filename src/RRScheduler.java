import java.util.Queue;

public class RRScheduler implements Scheduler{
    @Override
    public Process selectNextProcess(Queue<Process> readyQueue) {
        return readyQueue.poll();
    }
}
