import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Process {
    int pid;
    List<Instruction> instructions;
    PCB pcb;
    HashMap<String, Integer> sharedMemory;

    public Process(int pid, List<String> instructions, int base, int limit) {
        this.pid = pid;
        this.pcb = new PCB(pid, 0, base, limit, "ready");
        this.instructions = fromStringToInstruction(instructions);
    }
    public List<Instruction> fromStringToInstruction(List<String> instructions) {
        List<Instruction> instructionList = new ArrayList<>();
        for (String instruction : instructions) {
            String[] parts = instruction.split(" ");
            if(parts[0].equals("assign")) {
                if(parts.length == 3) {
                    instructionList.add(new AssignInstruction(sharedMemory, parts[1], parts[2]));
                }else if(parts.length == 5) {
                    instructionList.add(new AssignArithmeticInstruction(sharedMemory, parts[1], parts[2], parts[3], parts[4]));
                }else {
                    System.out.println("Invalid instruction");
                }
            } else if(parts[0].equals("print")) {
                instructionList.add(new PrintInstruction(sharedMemory, parts[1]));
            } else {
                System.out.println("Invalid instruction");
            }
        }
        return instructionList;
    }
    public boolean isDone() {
        return pcb.state.equals("terminated");
    }
    public void execute(int numInstructions) {
        for (int i = 0; i < numInstructions; i++) {
            if (pcb.pc < instructions.size()) {
                instructions.get(pcb.pc).execute();
                pcb.pc++;
            } else {
                pcb.state = "terminated";
                break;
            }
        }
    }
}
