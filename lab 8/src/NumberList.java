

//exe 1
import java.util.ArrayList;
import java.util.Collections;

public class NumberList {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();

        Collections.addAll(numbers, 10, 20, 30, 40, 50,
                60, 70, 80, 90, 100);

        int sum = 0;
        int maximum = numbers.get(0);

        for (int number : numbers) {
            sum += number;

            if (number > maximum) {
                maximum = number;
            }
        }

        System.out.println("Numbers: " + numbers);
        System.out.println("Sum: " + sum);
        System.out.println("Maximum: " + maximum);

        Collections.reverse(numbers);
        System.out.println("Reverse order: " + numbers);
    }
}

