import java.util.Queue;

public interface Scheduler {
    public Process selectNextProcess(Queue<Process> readyQueue);
}
