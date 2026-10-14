package lab9;

public class LevelDemo {

    static void printAdvice(Level level) {
        // A switch on an enum: the compiler knows every possible value
        switch (level) {
            case LOW:
                System.out.println("LOW: all is calm, keep an eye on things.");
                break;
            case MEDIUM:
                System.out.println("MEDIUM: be careful and make a plan.");
                break;
            case HIGH:
                System.out.println("HIGH: act now!");
                break;
        }
    }

    public static void main(String[] args) {
        for (Level level : Level.values()) {          // values() gives all constants in order
            printAdvice(level);
        }

        Level chosen = Level.valueOf("MEDIUM");       // text -> enum constant
        System.out.println("Chosen: " + chosen + ", position " + chosen.ordinal());
        System.out.println("Same constant? " + (chosen == Level.MEDIUM));   // == is correct for enums
    }
}
