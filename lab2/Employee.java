package lab2;

// Lab 2 - Exercise 3: auto-incrementing id, 3 overloaded constructors, 1 copy constructor
public class Employee {

    private static int nextId = 1;        // shared counter: the id the NEXT employee will get

    private final int employeeId;         // final: assigned exactly once
    private String name;
    private String department;
    private double salary;

    // Constructor 1: full details. This is the ONLY place that hands out an id.
    public Employee(String name, String department, double salary) {
        this.employeeId = nextId;
        nextId++;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // Constructor 2: no salary yet -> chains to constructor 1
    public Employee(String name, String department) {
        this(name, department, 0.0);
    }

    // Constructor 3: only a name -> chains to constructor 2
    public Employee(String name) {
        this(name, "Unassigned");
    }

    // Copy constructor: same details, but a NEW employee, so it gets a NEW id
    public Employee(Employee other) {
        this(other.name, other.department, other.salary);
    }

    public void display() {
        System.out.printf("ID %d | %s | %s | $%.2f%n", employeeId, name, department, salary);
    }

    public static void main(String[] args) {
        Employee e1 = new Employee("Tariro", "IT", 1200.50);
        Employee e2 = new Employee("Farai", "Finance");
        Employee e3 = new Employee("Rudo");
        Employee e4 = new Employee(e1);      // copy of Tariro

        e1.display();
        e2.display();
        e3.display();
        e4.display();
        System.out.println("e1 and e4 same object? " + (e1 == e4));
    }
}
