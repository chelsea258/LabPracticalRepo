
// exe 2
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class UniqueNames {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();

        names.add("Tendai");
        names.add("Rudo");
        names.add("Tendai");
        names.add("Tinashe");
        names.add("Rudo");

        Set<String> uniqueNames = new LinkedHashSet<>(names);

        System.out.println("Original names: " + names);
        System.out.println("Unique names: " + uniqueNames);
    }
}

