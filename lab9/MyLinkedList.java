package lab9;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Lab 9 - Exercise 4: a linked list with a STATIC NESTED Node and an INNER iterator class.
// Because it implements Iterable, it works in a for-each loop.
public class MyLinkedList<T> implements Iterable<T> {

    // STATIC NESTED class: a Node does not need to know about any particular list
    private static class Node<T> {
        T value;
        Node<T> next;          // reference to the next node (null at the end)

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> head;      // first node
    private Node<T> tail;      // last node
    private int size = 0;

    public void add(T value) {
        Node<T> node = new Node<>(value);
        if (head == null) {            // empty list: the new node is first AND last
            head = node;
            tail = node;
        } else {
            tail.next = node;          // hook it after the current last node
            tail = node;
        }
        size++;
    }

    public int size() {
        return size;
    }

    // for-each calls this to get an iterator
    @Override
    public Iterator<T> iterator() {
        return new ListIterator();     // an INNER class needs an outer object; "this" is supplied automatically
    }

    // INNER class (not static): it can read the outer list's private field "head"
    private class ListIterator implements Iterator<T> {

        private Node<T> current = head;    // start at the first node

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            T value = current.value;
            current = current.next;        // step forward
            return value;
        }
    }

    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("Tariro");
        list.add("Farai");
        list.add("Rudo");

        System.out.println("Size: " + list.size());
        for (String name : list) {         // works because MyLinkedList is Iterable
            System.out.println("  " + name);
        }

        MyLinkedList<Integer> numbers = new MyLinkedList<>();
        numbers.add(10);
        numbers.add(20);
        int total = 0;
        for (int n : numbers) {
            total = total + n;
        }
        System.out.println("Total of numbers: " + total);
    }
}
