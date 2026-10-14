package lab10;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

// Lab 10 - Exercise 2: a Predicate (true/false test) plus removeIf
public class RemoveEvens {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println("Before: " + numbers);

        // Predicate<Integer>: takes an Integer, answers true or false.  n -> n % 2 == 0 is a lambda.
        Predicate<Integer> isEven = n -> n % 2 == 0;

        numbers.removeIf(isEven);                 // removes every element for which the test is true
        System.out.println("After removing evens: " + numbers);
    }
}
