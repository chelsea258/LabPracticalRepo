package lab10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Lab 10 - Exercise 1: save five lines typed by the user, then read them back
public class NotesFile {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Path path = Path.of("notes.txt");         // saved next to where you run the program

        List<String> lines = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            System.out.print("Line " + i + ": ");
            lines.add(keyboard.nextLine());
        }

        try {
            Files.write(path, lines);             // creates the file, or overwrites it
            System.out.println("Saved " + lines.size() + " lines to " + path);

            List<String> readBack = Files.readAllLines(path);
            System.out.println("--- Read back from the file ---");
            for (String line : readBack) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("File problem: " + e.getMessage());
        }
    }
}
