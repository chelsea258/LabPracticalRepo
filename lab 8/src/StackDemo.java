//exe 3
import java.util.ArrayList;

class GenericStack<T> {
    private ArrayList<T> items = new ArrayList<>();

    public void push(T item) {
        items.add(item);
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return items.remove(items.size() - 1);
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return items.get(items.size() - 1);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}

public class StackDemo {
    public static void main(String[] args) {
        GenericStack<String> stack = new GenericStack<>();

        stack.push("Java");
        stack.push("Python");
        stack.push("C++");

        System.out.println("Top item: " + stack.peek());
        System.out.println("Removed item: " + stack.pop());
        System.out.println("Is empty? " + stack.isEmpty());
    }
}