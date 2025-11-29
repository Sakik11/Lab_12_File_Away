import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileInspector {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("src"));  // start in src folder

        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();

            int lineCount = 0;
            int wordCount = 0;
            int charCount = 0;

            System.out.println("Reading: " + selectedFile.getName());
            System.out.println("====================================");

            try {
                for (String line : Files.readAllLines(selectedFile.toPath())) {
                    System.out.println(line);
                    lineCount++;

                    // count words (ignore blank lines)
                    String trimmed = line.trim();
                    if (!trimmed.isEmpty()) {
                        wordCount += trimmed.split("\\s+").length;
                    }

                    // count characters (per line)
                    charCount += line.length();
                }

                System.out.println("\n===== FILE SUMMARY =====");
                System.out.println("File Name: " + selectedFile.getName());
                System.out.println("Lines: " + lineCount);
                System.out.println("Words: " + wordCount);
                System.out.println("Characters: " + charCount);

            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }
    }
}
