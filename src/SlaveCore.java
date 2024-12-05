public class SlaveCore {
    int coreID;
    MasterCore masterCore;
    Process currentProcess;
    boolean idle;

    public SlaveCore(int coreID, MasterCore masterCore) {
        this.coreID = coreID;
        this.masterCore = masterCore;
        this.idle = true;
    }
    public boolean isIdle() {
        return idle;
    }
    public void assignProcess(Process process) {
        currentProcess = process;
        idle = false;
        //executeProcess(2);
    }
    public void executeProcess(int quantum) {
        currentProcess.execute(quantum);
        if (currentProcess.isDone()) {
            idle = true;
        }
    }
}
