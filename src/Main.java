import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ProcessReader reader = new ProcessReader();
        List<String> instructions1 = new LinkedList<>();
        try {
            instructions1 = reader.readFile("src/ProcessesFiles/program_1.txt");
        }catch (IOException e) {
            e.printStackTrace();
        }
        List<String> instructions2 = new LinkedList<>();
        try {
            instructions2 = reader.readFile("src/ProcessesFiles/program_2.txt");
        }catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<String, Integer> sharedMemory = new HashMap<>();
        Process process1 = new Process(0, instructions1, 0, 100, sharedMemory);
        Process process2 = new Process(1, instructions2, 100, 200, sharedMemory);
        Queue<Process> readyQueue = new LinkedList<>();
        MasterCore master = new MasterCore(readyQueue);
        master.addProcess(process1);
        master.addProcess(process2);
        master.scheduleProcesses();

        //print the memory
        System.out.println("Memory:");
        for (Map.Entry<String, Integer> entry : sharedMemory.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

    }
}
