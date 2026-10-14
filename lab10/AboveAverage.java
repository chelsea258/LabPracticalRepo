package lab10;

import java.util.List;

// Lab 10 - Exercise 4: streams. Print the names of students above the class average, A to Z.
record Student(String name, int marks) { }

public class AboveAverage {
    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Tariro", 78),
                new Student("Farai", 55),
                new Student("Rudo", 91),
                new Student("Kuda", 62),
                new Student("Blessing", 85),
                new Student("Anesu", 40));

        // Step 1: the class average (mapToInt turns each Student into its marks, average() works them out)
        double average = students.stream()
                .mapToInt(Student::marks)
                .average()
                .orElse(0);
        System.out.println("Class average: " + average);

        // Step 2: filter -> map -> sorted -> print
        System.out.println("Above average, in alphabetical order:");
        students.stream()
                .filter(s -> s.marks() > average)     // keep only the high scorers
                .map(Student::name)                   // Student -> name
                .sorted()                             // Strings sort A to Z naturally
                .forEach(name -> System.out.println("  " + name));
    }
}
