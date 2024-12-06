import java.util.HashMap;
import java.util.*;
public class AssignInstruction extends Instruction {
    String dest;
    String src;
    public AssignInstruction(HashMap<String,Integer> memory, String dest, String src) {
        super(memory);
        this.dest = dest;
        this.src = src;
    }
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the value to assign to " + dest);
        int value = sc.nextInt();
        sharedMemory.put(dest, value);
    }
}
