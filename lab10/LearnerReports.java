package lab10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

// The same record style as Program 10.2, with a helper that works out the grade band
record Learner(String name, int roll, double gpa) {

    static Learner fromCsv(String line) {
        String[] p = line.split(",");
        return new Learner(p[0].trim(), Integer.parseInt(p[1].trim()), Double.parseDouble(p[2].trim()));
    }

    // Grade bands (my assumption, the manual does not give them): A = 3.5+, B = 3.0 to 3.49, C = below 3.0
    String band() {
        if (gpa >= 3.5) {
            return "A";
        } else if (gpa >= 3.0) {
            return "B";
        } else {
            return "C";
        }
    }
}

// Lab 10 - Exercise 5 (Challenge): load learners.csv, group by band, write one report file per band
public class LearnerReports {
    public static void main(String[] args) {
        Path input = Path.of("learners.csv");

        try {
            // 1. Load: each CSV line becomes a Learner
            List<Learner> learners = new ArrayList<>();
            for (String line : Files.readAllLines(input)) {
                if (!line.isBlank()) {
                    learners.add(Learner.fromCsv(line));
                }
            }
            System.out.println("Loaded " + learners.size() + " learners");

            // 2. Group: a Map from band ("A", "B", "C") to the list of learners in that band
            //    (TreeMap keeps the bands in order A, B, C)
            Map<String, List<Learner>> byBand = learners.stream()
                    .collect(Collectors.groupingBy(Learner::band, TreeMap::new, Collectors.toList()));

            // 3. Write one report file per band
            for (Map.Entry<String, List<Learner>> entry : byBand.entrySet()) {
                String band = entry.getKey();
                List<String> report = new ArrayList<>();
                report.add("Band " + band + " report (" + entry.getValue().size() + " learners)");
                for (Learner l : entry.getValue()) {
                    report.add(String.format("%-10s roll %d  GPA %.1f", l.name(), l.roll(), l.gpa()));
                }
                Path out = Path.of("report-" + band + ".txt");
                Files.write(out, report);
                System.out.println("Wrote " + out + " (" + entry.getValue().size() + " learners)");
            }
        } catch (NoSuchFileException e) {
            System.out.println("Cannot find " + e.getMessage() + ". Run the program in the folder that contains learners.csv.");
        } catch (IOException e) {
            System.out.println("File problem: " + e.getMessage());
        }
    }
}
