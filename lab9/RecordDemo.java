package lab9;

// Lab 9 - Exercise 2: a record is a short way to write an immutable data class
record Student(String name, int age) { }

public class RecordDemo {
    public static void main(String[] args) {
        Student a = new Student("Tariro", 19);
        Student b = new Student("Tariro", 19);
        Student c = new Student("Farai", 21);

        System.out.println(a);                          // toString is written for us
        System.out.println("a equals b? " + a.equals(b));   // equals compares the VALUES
        System.out.println("a == b?     " + (a == b));      // still two different objects
        System.out.println("a equals c? " + a.equals(c));

        System.out.println("Name component: " + a.name());  // accessor is name(), not getName()
        System.out.println("Age component:  " + a.age());
    }
}
