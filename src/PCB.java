public class PCB {
    int pid;
    int pc;
    int base;
    int limit;
    String state;

    public PCB(int pid, int pc, int base, int limit, String state) {
        this.pid = pid;
        this.pc = pc;
        this.base = base;
        this.limit = limit;
        this.state = state;
    }
}
