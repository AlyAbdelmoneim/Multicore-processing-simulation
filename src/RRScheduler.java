import java.util.Queue;

public class RRScheduler implements Scheduler{
    public int timeQuantum;

    public RRScheduler(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }
    public RRScheduler() {
        this.timeQuantum = 2;
    }

    @Override
    public Process selectNextProcess(Queue<Process> readyQueue) {
        return readyQueue.poll();
    }
}
