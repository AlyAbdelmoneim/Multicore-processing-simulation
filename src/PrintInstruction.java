import java.util.HashMap;

public class PrintInstruction extends Instruction {
    String src;
    public PrintInstruction(HashMap<String, String> memory, String src) {
        super(memory);
        this.src = src;
    }
    public void execute() {
        System.out.println(sharedMemory.get(src));
    }
}
