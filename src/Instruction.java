import java.util.HashMap;

public abstract class Instruction {
    HashMap<String, Integer> sharedMemory;
    public Instruction(HashMap<String, Integer> sharedMemory) {
        this.sharedMemory = sharedMemory;
    }
    public abstract void execute();
}
