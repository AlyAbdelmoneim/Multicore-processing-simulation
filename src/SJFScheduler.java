import java.util.*;

public class SJFScheduler implements Scheduler {
    @Override
    public Process selectNextProcess(Queue<Process> readyQueue) {
        if (readyQueue.isEmpty()) {
            return null;
        }

        // Convert Queue to List to sort by the number of remaining instructions
        List<Process> processes = new ArrayList<>(readyQueue);
        Collections.sort(processes, Comparator.comparingInt(Process::getRemainingInstructions));

        // Remove the selected process from the queue and return it
        Process selectedProcess = processes.get(0);
        readyQueue.remove(selectedProcess);
        return selectedProcess;
    }
}
