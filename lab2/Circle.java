package lab2;

// Lab 2 - Exercises 1 and 2: constructors and a static counter
public class Circle {

    private static int count = 0;      // ONE copy shared by every Circle (Exercise 2)
    private double radius;             // each Circle has its own radius

    // Constructor 1: takes the radius
    public Circle(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }
        this.radius = radius;          // this.radius = the field, radius = the parameter
        count++;                       // one more circle exists
    }

    // Constructor 2: no argument, defaults the radius to 1.0
    public Circle() {
        this(1.0);                     // calls constructor 1 (must be the FIRST line)
    }

    public static int getCount() {     // static getter: call it with Circle.getCount()
        return count;
    }

    public double area() {
        return Math.PI * radius * radius;
    }

    public static void main(String[] args) {
        System.out.println("Circles at start: " + Circle.getCount());
        Circle small = new Circle();          // radius 1.0
        Circle big = new Circle(5.5);
        System.out.printf("small area = %.2f%n", small.area());
        System.out.printf("big area   = %.2f%n", big.area());
        System.out.println("Circles created: " + Circle.getCount());
    }
}
