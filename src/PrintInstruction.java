import java.util.HashMap;

public class PrintInstruction extends Instruction {
    String src;
    public PrintInstruction(HashMap<String, Integer> memory, String src) {
        super(memory);
        this.src = src;
    }
    public void execute() {
        System.out.println("printing " + src + " = " + sharedMemory.get(src));
    }
}
