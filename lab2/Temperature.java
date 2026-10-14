package lab2;

// Lab 2 - Exercise 5 (Challenge): static factory methods + private constructor
public class Temperature {

    private final double celsius;     // we always store Celsius internally

    // PRIVATE: nobody outside can call "new Temperature(...)".
    // They must use one of the factory methods below.
    private Temperature(double celsius) {
        this.celsius = celsius;
    }

    // Static factory methods: the NAME tells you which unit you are giving
    public static Temperature fromCelsius(double c) {
        return new Temperature(c);
    }

    public static Temperature fromFahrenheit(double f) {
        double c = (f - 32) * 5.0 / 9.0;
        return new Temperature(c);
    }

    public double getCelsius() {
        return celsius;
    }

    public double getFahrenheit() {
        return celsius * 9.0 / 5.0 + 32;
    }

    @Override
    public String toString() {
        return String.format("%.1f C / %.1f F", getCelsius(), getFahrenheit());
    }

    public static void main(String[] args) {
        Temperature boiling = Temperature.fromCelsius(100);
        Temperature body = Temperature.fromFahrenheit(98.6);

        System.out.println("Boiling water: " + boiling);
        System.out.println("Body temperature: " + body);
        // Temperature t = new Temperature(30);   // would NOT compile: constructor is private
    }
}
