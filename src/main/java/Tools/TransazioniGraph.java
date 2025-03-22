package Tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TransazioniGraph {
    private final String filePath;

    public TransazioniGraph(String filePath) {
        this.filePath = filePath;
        runPythonScript();
    }

    public void runPythonScript() {
        try {

            String pythonExecutable = ".venv/Scripts/python.exe";
            String pythonScriptPath = "src/main/resources/script.py";
            ProcessBuilder pb = new ProcessBuilder(pythonExecutable, pythonScriptPath, filePath);
            pb.redirectErrorStream(true);

            Process process = pb.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            process.waitFor();

        } catch (Exception e) {
            System.out.println();
        }
    }

}
