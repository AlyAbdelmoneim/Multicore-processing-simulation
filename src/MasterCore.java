import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class MasterCore {
    SlaveCore slave1;
    SlaveCore slave2;
    Queue<Process> readyQueue;
    List<Process> completedProcesses;
    Scheduler scheduler;

    public MasterCore(Queue<Process> readyQueue, Scheduler scheduler) {
        this.readyQueue = readyQueue;
        slave1 = new SlaveCore(1, this);
        slave2 = new SlaveCore(2, this);
        completedProcesses = new ArrayList<>();
        this.scheduler = scheduler;
    }
    public void addProcess(Process process) {
        process.pcb.state = "ready";
        readyQueue.add(process);
    }
    public void scheduleProcesses() {
        while (!allProcessesCompleted()) {
            System.out.println("The ready queue is: " + readyQueue);
            // Check for idle slave cores and assign processes
            if (slave1.isIdle() && !readyQueue.isEmpty()) {
                Process nextProcess = scheduler.selectNextProcess(readyQueue);
                System.out.println("Slave core 1 is now executing process " + nextProcess.pid);
                slave1.assignProcess(nextProcess);
            }
            if (slave2.isIdle() && !readyQueue.isEmpty()) {
                Process nextProcess = scheduler.selectNextProcess(readyQueue);
                System.out.println("Slave core 2 is now executing process " + nextProcess.pid);
                slave2.assignProcess(nextProcess);
            }

            // Execute processes on slave cores for a single quantum
            if (!slave1.isIdle()) {
                Process preemptedProcess = slave1.executeProcess(2); // Execute for quantum=2
                if (preemptedProcess != null) {
                    handlePreemptedProcess(preemptedProcess);
                }
                slave1.idle = true;
            }

            if (!slave2.isIdle()) {
                Process preemptedProcess = slave2.executeProcess(2); // Execute for quantum=2
                if (preemptedProcess != null) {
                    handlePreemptedProcess(preemptedProcess);
                }
                slave2.idle = true;
            }
        }

        System.out.println("All processes completed!");
        logProcessStates();
    }

    //removed this method since it is now in the Scheduler interface
//    // Select the next process (default: FIFO)
//    private Process selectNextProcess() {
//        return readyQueue.poll();
//    }

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

