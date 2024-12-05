import java.util.HashMap;

public class AssignArithmeticInstruction extends Instruction {
    String dest;
    String src1;
    String src2;
    String op;

    public AssignArithmeticInstruction(HashMap<String,String> memory, String dest, String src1, String src2, String op) {
        super(memory);
        this.dest = dest;
        this.src1 = src1;
        this.src2 = src2;
        this.op = op;
    }
    public void execute(){
        int src1Value = Integer.parseInt(src1);
        int src2Value = Integer.parseInt(src2);
        int result = 0;
        if (op.equals("+")) {
            result = src1Value + src2Value;
        } else if (op.equals("-")) {
            result = src1Value - src2Value;
        } else if (op.equals("*")) {
            result = src1Value * src2Value;
        } else if (op.equals("/")) {
            result = src1Value / src2Value;
        }
        sharedMemory.put(dest, Integer.toString(result));
    }

}
