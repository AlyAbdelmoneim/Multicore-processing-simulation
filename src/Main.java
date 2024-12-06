import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ProcessReader reader = new ProcessReader();
        List<String> instructions1 = new LinkedList<>();
        try {
            instructions1 = reader.readFile("src/ProcessesFiles/program_1.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> instructions2 = new LinkedList<>();
        try {
            instructions2 = reader.readFile("src/ProcessesFiles/program_2.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> instructions3 = new LinkedList<>();
        try {
            instructions3 = reader.readFile("src/ProcessesFiles/program_3.txt");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        HashMap<String, Integer> sharedMemory = new HashMap<>();
        Process process1 = new Process(0, instructions1, 0, 100, sharedMemory);
        Process process2 = new Process(1, instructions2, 100, 200, sharedMemory);
        Process process3 = new Process(2, instructions3, 200, 300, sharedMemory);
        Queue<Process> readyQueue = new LinkedList<>();

        // Prompt user for scheduler selection
        Scanner scanner = new Scanner(System.in);
        Scheduler scheduler = null;
        System.out.println("Select the scheduler:");
        System.out.println("Type 'S' for SJF Scheduler or 'R' for RR Scheduler:");
        String choice = scanner.nextLine().trim().toUpperCase();

        if ("S".equals(choice)) {
            scheduler = new SJFScheduler();
            System.out.println("Using SJF Scheduler");
        } else if ("R".equals(choice)) {
            // you need to take the time quantum as input from the user then pass it to the RR scheduler
            System.out.println("Enter the time quantum for Round Robin Scheduler:");
            int timeQuantum = scanner.nextInt();
            scheduler = new RRScheduler(timeQuantum);
            System.out.println("Using Round Robin Scheduler with time quantum " + timeQuantum);
        } else {
            System.out.println("Invalid input! Defaulting to Round Robin Scheduler.");
            scheduler = new RRScheduler();
        }

        MasterCore master = new MasterCore(readyQueue, scheduler);
        master.addProcess(process1);
        master.addProcess(process2);
        master.addProcess(process3);

        master.scheduleProcesses();

        System.out.println("Memory:");
        for (Map.Entry<String, Integer> entry : sharedMemory.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        scanner.close();
    }
}
