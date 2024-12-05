import java.util.HashMap;

public abstract class Instruction {
    HashMap<String, String> sharedMemory;
    public Instruction(HashMap<String, String> sharedMemory) {
        this.sharedMemory = sharedMemory;
    }
    public abstract void execute();
}
