import java.util.ArrayList;
import java.util.List;

public class Process {

    List<Instruction> instructions;
    PCB pcb;

    public Process(PCB pcb, List<String> instructions) {
        this.pcb = pcb;
        this.instructions = fromStringToInstruction(instructions);
    }
    public List<Instruction> fromStringToInstruction(List<String> instructions) {
        List<Instruction> instructionList = new ArrayList<>();
        for (String instruction : instructions) {
            String[] parts = instruction.split(" ");
            if(parts[0].equals("add")) {

            } else if(parts[0].equals("assign")) {

            } else if(parts[0].equals("YIELD")) {

            }
        }
        return instructionList;

    }
}
