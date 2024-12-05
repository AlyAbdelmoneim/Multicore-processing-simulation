public class Instruction {
    String type;
    public Instruction(String type) {
        this.type = type;
    }
    void execute() {
        if(type.equals("ASSIGN")) {
            // Assign the value to the variable
        } else if(type.equals("ARITHMETIC")) {
            // Perform arithmetic operation
        } else if(type.equals("PRINT")) {
            // Print the value of the variable
        } else {
            throw new UnsupportedOperationException("Unknown instruction type!");
        }
    }
}
