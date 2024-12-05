import java.util.HashMap;

public class AssignInstruction extends Instruction {
    String dest;
    int src;
    public AssignInstruction(HashMap<String,Integer> memory, String dest, String src) {
        super(memory);
        this.dest = dest;
        this.src = Integer.parseInt(src);
    }
    public void execute() {
        sharedMemory.put(dest, src);
    }
}
