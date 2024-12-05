import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Process {
    int pid;
    List<Instruction> instructions;
    PCB pcb;
    HashMap<String, Integer> sharedMemory;

    public Process(int pid, List<String> instructions, int base, int limit, HashMap<String, Integer> sharedMemory) {
        this.pid = pid;
        this.sharedMemory = sharedMemory;
        this.pcb = new PCB(pid, 0, base, limit, "ready");
        this.instructions = fromStringToInstruction(instructions);
        System.out.println(sharedMemory == null ? "sharedMemory is null" : "sharedMemory is not null");
    }

    //old method to convert string instructions to Instruction objects!!!!!!

//    public List<Instruction> fromStringToInstruction(List<String> instructions) {
//        List<Instruction> instructionList = new ArrayList<>();
//        for (String instruction : instructions) {
//            String[] parts = instruction.split(" ");
//            if(parts[0].equals("assign")) {
//                if(parts.length == 3) {
//                    instructionList.add(new AssignInstruction(sharedMemory, parts[1], parts[2]));
//                }else if(parts.length == 5) {
//                    instructionList.add(new AssignArithmeticInstruction(sharedMemory, parts[1], parts[2], parts[3], parts[4]));
//                }else {
//                    System.out.println("Invalid assign instruction");
//                }
//            } else if(parts[0].equals("print")) {
//                instructionList.add(new PrintInstruction(sharedMemory, parts[1]));
//            } else {
//                System.out.println("Invalid print instruction");
//            }
//        }
//        return instructionList;
//    }

    //new method to convert string instructions to Instruction objects
    public List<Instruction> fromStringToInstruction(List<String> instructions) {
        List<Instruction> instructionList = new ArrayList<>();
        for (String instruction : instructions) {
            String[] parts = instruction.split(" ");
            if (parts[0].equals("assign")) {
                if (parts.length == 3) {
                    // Handle simple assignment
                    instructionList.add(new AssignInstruction(sharedMemory, parts[1], parts[2]));
                } else if (parts.length == 4) {
                    // Handle arithmetic operations with keywords (e.g., assign z add a b)
                    String operator = convertKeywordToOperator(parts[2]);
                    instructionList.add(new AssignArithmeticInstruction(sharedMemory, parts[1], parts[3], parts[4], operator));
                } else {
                    System.out.println("Invalid assign instruction");
                }
            } else if (parts[0].equals("print")) {
                instructionList.add(new PrintInstruction(sharedMemory, parts[1]));
            } else {
                System.out.println("Invalid instruction");
            }
        }
        return instructionList;
    }

    // Helper method to convert keywords to operators
    private String convertKeywordToOperator(String keyword) {
        switch (keyword) {
            case "add":
                return "+";
            case "subtract":
                return "-";
            case "multiply":
                return "*";
            case "divide":
                return "/";
            default:
                throw new IllegalArgumentException("Unknown operation keyword: " + keyword);
        }
    }



    public boolean isDone() {
        return pcb.state.equals("terminated");
    }
    public void execute(int numInstructions) {
        pcb.state = "running";
        for (int i = 0; i < numInstructions; i++) {
            if (pcb.pc < instructions.size()) {
                instructions.get(pcb.pc).execute();
                pcb.pc++;
            } else {
                pcb.state = "terminated";
                break;
            }
        }
        if(pcb.state.equals("running")) {
            pcb.state = "ready";
        }
    }
}
