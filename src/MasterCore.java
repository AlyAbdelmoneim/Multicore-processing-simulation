import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class MasterCore {
    SlaveCore slave1;
    SlaveCore slave2;
    Queue<Process> readyQueue;
    List<Process> completedProcesses;

    public MasterCore(Queue<Process> readyQueue) {
        this.readyQueue = readyQueue;
        slave1 = new SlaveCore(1, this);
        slave2 = new SlaveCore(2, this);
        completedProcesses = new ArrayList<>();
    }
    public void addProcess(Process process) {
        process.pcb.state = "ready";
        readyQueue.add(process);
    }

    // Main scheduling loop
    public void scheduleProcesses() {
        while (!allProcessesCompleted()) {
            Process nextProcess = selectNextProcess();
            if (nextProcess != null) {
                if (slave1.isIdle()) {
                    slave1.assignProcess(nextProcess);
                } else if (slave2.isIdle()) {
                    slave2.assignProcess(nextProcess);
                } else {
                    readyQueue.add(nextProcess);
                }
            }
            slave1.executeProcess(2);
            slave2.executeProcess(2);
        }
        System.out.println("All processes completed!");
        logProcessStates();
    }

    // Select the next process (default: FIFO)
    private Process selectNextProcess() {
        return readyQueue.poll();
    }

    // Handle a preempted process (add it back to the ready queue)
    public void handlePreemptedProcess(Process process) {
        process.pcb.state = "ready";
        readyQueue.add(process);
    }

    // Handle a completed process
    public void handleCompletedProcess(Process process) {
        process.pcb.state = "terminated";
        completedProcesses.add(process);
    }

    // Check if all processes are completed
    private boolean allProcessesCompleted() {
        return readyQueue.isEmpty() && slave1.isIdle() && slave2.isIdle();
    }

    // Log the state of all processes (for debugging)
    private void logProcessStates() {
        System.out.println("Completed Processes:");
        for (Process process : completedProcesses) {
            System.out.println("Process " + process.pid + ": " + process.pcb.state);
        }
    }
}

