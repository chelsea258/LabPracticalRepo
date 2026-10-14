package lab10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;

// Lab 10 - Exercise 3: count lines, words and characters in a text file
// Usage: java TextStats sample.txt      (with no argument it uses sample.txt)
public class TextStats {
    public static void main(String[] args) {
        String fileName = "sample.txt";
        if (args.length > 0) {
            fileName = args[0];
        }

        try {
            List<String> lines = Files.readAllLines(Path.of(fileName));

            int lineCount = lines.size();
            int wordCount = 0;
            int charCount = 0;
            for (String line : lines) {
                charCount = charCount + line.length();           // line breaks are not counted
                if (!line.isBlank()) {
                    wordCount = wordCount + line.trim().split("\\s+").length;
                }
            }

            System.out.println("File:       " + fileName);
            System.out.println("Lines:      " + lineCount);
            System.out.println("Words:      " + wordCount);
            System.out.println("Characters: " + charCount);
        } catch (NoSuchFileException e) {
            System.out.println("Cannot find the file: " + fileName);
        } catch (IOException e) {
            System.out.println("Could not read the file: " + e.getMessage());
        }
    }
}
