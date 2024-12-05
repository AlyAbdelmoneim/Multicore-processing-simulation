import java.util.ArrayList;
import java.util.List;

public class Core {
    private boolean isMaster;
    private List<Process> processQueue;
    private List<Core> slaveCores;

    public Core(boolean isMaster) {
        this.isMaster = isMaster;
        this.processQueue = new ArrayList<>();
        if (!isMaster) {
            this.slaveCores = new ArrayList<>();
        }
    }

    public void scheduleTask(Process process) {
        if (isMaster) {
            if (!slaveCores.isEmpty()) {
                for (Core slave : slaveCores) {
                    slave.addProcess(process);
                }
            } else {
                executeProcess(process);
            }
        }
    }

    public void executeProcess(Process process) {
        processQueue.add(process);
    }

    public void addSlave(Core slaveCore) {
        if (isMaster) {
            slaveCores.add(slaveCore);
        }
    }

    public void addProcess(Process process) {
        processQueue.add(process);
    }

    public void executeAll() {
        for (Process process : processQueue) {
            executeProcess(process);
        }
    }

    public boolean isMaster() {
        return isMaster;
    }

    public List<Process> getProcessQueue() {
        return processQueue;
    }

    public List<Core> getSlaveCores() {
        return slaveCores;
    }
}
