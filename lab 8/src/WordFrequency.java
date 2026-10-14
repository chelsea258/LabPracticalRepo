//exe 4

import java.util.*;

public class WordFrequency {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter a paragraph:");
        String paragraph = input.nextLine().toLowerCase();

        String[] words = paragraph.split("\\W+");
        Map<String, Integer> frequency = new HashMap<>();

        for (String word : words) {
            if (!word.isEmpty()) {
                frequency.merge(word, 1, Integer::sum);
            }
        }

        frequency.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue()
                        .reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .forEach(entry ->
                        System.out.println(entry.getKey() + " = "
                                + entry.getValue()));

        input.close();
    }
}

