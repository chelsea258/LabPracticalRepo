

// exexrcise yechishanu
import java.util.Arrays;

public class BubbleSortDemo {

    public static <T extends Comparable<T>> void bubbleSort(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] marks = {75, 45, 90, 60, 80};
        String[] names = {"Tendai", "Alice", "Brian", "Rudo"};

        bubbleSort(marks);
        bubbleSort(names);

        System.out.println("Sorted marks: " + Arrays.toString(marks));
        System.out.println("Sorted names: " + Arrays.toString(names));
    }
}

