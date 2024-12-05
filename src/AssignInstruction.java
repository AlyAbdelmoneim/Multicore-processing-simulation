import java.util.HashMap;

public class AssignInstruction extends Instruction {
    String dest;
    String src;
    public AssignInstruction(HashMap<String,String> memory, String dest, String src) {
        super(memory);
        this.dest = dest;
        this.src = src;
    }
    public void execute() {
        sharedMemory.put(dest, src);
    }
}
