public class Student {
    //  private fields ensure external code cannot manipulate state directly
    private String name;
    private double marks;

    public Student(String name, double marks) {
        setName(name);
        setMarks(marks); // reuse validation in constructor
    }

    // Getters and Setters with validation
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name.trim();
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        // Enforces class invariant — marks must stay between 0 and 100
        if (marks < 0.0 || marks > 100.0) {
            throw new IllegalArgumentException("Marks must be between 0 and 100. Given: " + marks);
        }
        this.marks = marks;
    }

    //  Derived property calculated dynamically; no dedicated field needed
    public char getGrade() {
        if (marks >= 75) return 'A';
        if (marks >= 65) return 'B';
        if (marks >= 50) return 'C';
        return 'F';
    }

    public static void main(String[] args) {
        Student s1 = new Student("Chelsea", 82.5);
        System.out.println(s1.getName() + " Marks: " + s1.getMarks() + ", Grade: " + s1.getGrade());

        try {
            s1.setMarks(105); // Invalid mark test
        } catch (IllegalArgumentException e) {
            System.out.println("Validation caught: " + e.getMessage());
        }
    }
}