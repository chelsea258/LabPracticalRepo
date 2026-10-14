public class Car {
    String brand;
    int year;
    double mileage;

    // method to print the details of a car
    void display() {
        System.out.println("Brand: " + brand + ", Year: " + year + ", Mileage: " + mileage + " km");
    }

    //  returns true if the car is older than 25 years (assuming current year is 2026)
    boolean isAntique() {
        int currentYear = 2026;
        return (currentYear - year) > 25;
    }

    public static void main(String[] args) {
        // EXPLAIN: 'new' allocates memory for each car on the heap
        Car car1 = new Car();
        car1.brand = "Toyota";
        car1.year = 1995;
        car1.mileage = 220000.5;

        Car car2 = new Car();
        car2.brand = "Ford";
        car2.year = 2018;
        car2.mileage = 45000.0;

        Car car3 = new Car();
        car3.brand = "Mercedes-Benz";
        car3.year = 1980;
        car3.mileage = 310000.0;

        // Displaying details and checking antique status
        car1.display();
        System.out.println("Is antique? " + car1.isAntique());
        System.out.println();

        car2.display();
        System.out.println("Is antique? " + car2.isAntique());
        System.out.println();

        car3.display();
        System.out.println("Is antique? " + car3.isAntique());
    }
}