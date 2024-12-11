import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

public class MasterCore {
    SlaveCore slave1;
    SlaveCore slave2;
    Queue<Process> readyQueue;
    HashMap<Process, Integer> completedProcesses;
    Scheduler scheduler;
    int cycle = 0;

    public MasterCore(Queue<Process> readyQueue, Scheduler scheduler) {
        this.readyQueue = readyQueue;
        slave1 = new SlaveCore(1, this);
        slave2 = new SlaveCore(2, this);
        completedProcesses = new HashMap<>();
        this.scheduler = scheduler;
    }

    public void addProcess(Process process) {
        process.pcb.state = "ready";
        readyQueue.add(process);
    }

    public void scheduleProcesses() {
        while (!allProcessesCompleted()) {
            System.out.println("Cycle " + cycle + ":");

            // Log the ready queue
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

            // Execute one instruction per cycle for each core
            if (!slave1.isIdle()) {
                Process preemptedProcess = slave1.executeProcess(1); // Execute 1 instruction
                if (preemptedProcess != null) {
                    handlePreemptedProcess(preemptedProcess);
                }
            }

            if (!slave2.isIdle()) {
                Process preemptedProcess = slave2.executeProcess(1); // Execute 1 instruction
                if (preemptedProcess != null) {
                    handlePreemptedProcess(preemptedProcess);
                }
            }

            // Increment cycle counter
            cycle++;
        }

        System.out.println("All processes completed!");
        logProcessStates();
    }

    // Handle a preempted process (add it back to the ready queue)
//    public void handlePreemptedProcess(Process process) {
//        process.pcb.state = "ready";
//        readyQueue.add(process);
//    }

    // v2 of the handlePreemptedProcess method
    // Handle a preempted process (add it back to the ready queue)
    public void handlePreemptedProcess(Process process) {
        if (!readyQueue.contains(process)) { // Check if the process is already in the ready queue
            process.pcb.state = "ready";
            readyQueue.add(process);
        } else {
            System.out.println("Process " + process.pid + " is already in the ready queue, skipping duplicate addition.");
        }
    }




    // Handle a completed process
    public void handleCompletedProcess(Process process) {
        process.pcb.state = "terminated";
        completedProcesses.put(process, cycle);
    }

    // Check if all processes are completed
    private boolean allProcessesCompleted() {
        return readyQueue.isEmpty() && slave1.isIdle() && slave2.isIdle();
    }

    // Log the state of all processes (for debugging)
    private void logProcessStates() {
        System.out.println("Completed Processes:");
        for (Process process : completedProcesses.keySet()) {
            System.out.println("Process " + process.pid + " completed at cycle " + completedProcesses.get(process));
        }
    }
}
