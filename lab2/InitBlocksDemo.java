package lab2;

// Lab 2 - Exercise 4: two static blocks and two instance blocks.
// PREDICT the output first, then run it.
public class InitBlocksDemo {

    static { System.out.println("static block 1"); }

    { System.out.println("instance block 1"); }

    static { System.out.println("static block 2"); }

    { System.out.println("instance block 2"); }

    InitBlocksDemo() {
        System.out.println("constructor");
    }

    public static void main(String[] args) {
        System.out.println("main starts");
        System.out.println("--- first object ---");
        new InitBlocksDemo();
        System.out.println("--- second object ---");
        new InitBlocksDemo();
    }
}
