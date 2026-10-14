class Rectangle {
    double width;
    double height;

    double area() {
        return width * height;
    }

    double perimeter() {
        return 2 * (width + height);
    }

    void display() {
        System.out.println("Width: " + width + ", Height: " + height +
                ", Area: " + area() + ", Perimeter: " + perimeter());
    }
}

public class RectangleDemo {
    public static void main(String[] args) {

        // Creating an array of 5 Rectangle references (initially all null)
        Rectangle[] rectangles = new Rectangle[5];

        rectangles[0] = new Rectangle(); rectangles[0].width = 3.0; rectangles[0].height = 4.0; // area = 12
        rectangles[1] = new Rectangle(); rectangles[1].width = 5.0; rectangles[1].height = 5.0; // area = 25
        rectangles[2] = new Rectangle(); rectangles[2].width = 2.0; rectangles[2].height = 8.0; // area = 16
        rectangles[3] = new Rectangle(); rectangles[3].width = 10.0; rectangles[3].height = 4.0; // area = 40 (largest)
        rectangles[4] = new Rectangle(); rectangles[4].width = 1.0; rectangles[4].height = 9.0; // area = 9

        // Finding the rectangle with the maximum area
        Rectangle maxRectangle = rectangles[0];
        for (int i = 1; i < rectangles.length; i++) {
            if (rectangles[i].area() > maxRectangle.area()) {
                maxRectangle = rectangles[i];
            }
        }

        System.out.println("=== Rectangle with Largest Area ===");
        maxRectangle.display();
        System.out.println();

        // --- INTERMEDIATE EXERCISE 2: ALIASING ---
        System.out.println("=== Aliasing Demonstration ===");
        Rectangle r1 = new Rectangle();
        r1.width = 10.0;
        r1.height = 5.0;

        // EXPLAIN: r2 copies the REFERENCE (memory address), not the actual object.
        // Both r1 and r2 now point to the exact same object on the heap.
        Rectangle r2 = r1;

        System.out.println("Original r1.width: " + r1.width); // prints 10.0

        // Changing state via r2 reference
        r2.width = 25.0;

        // EXPLAIN: Since r1 and r2 refer to the same object, r1.width also reflects the change.
        System.out.println("r1.width after changing r2.width: " + r1.width); // prints 25.0
    }
}
