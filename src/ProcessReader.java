import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;

public class ProcessReader {
    public List<String> readFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<String> instructions = new ArrayList<>();
        try {
            String line = reader.readLine();
            while (line != null && !line.isEmpty()) {
                instructions.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
        return instructions;
    }
}
