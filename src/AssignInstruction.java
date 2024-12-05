import java.util.HashMap;

public class AssignInstruction extends Instruction {
    String dest;
    String src;
    public AssignInstruction(HashMap<String,Integer> memory, String dest, String src) {
        super(memory);
        this.dest = dest;
        this.src = src;
        System.out.println(sharedMemory == null ? "sharedMemory is null" : "sharedMemory is not null in assign");
    }
    public void execute() {
        sharedMemory.put(dest, sharedMemory.get(src));
    }
}
