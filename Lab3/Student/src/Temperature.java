
public class Temperature {
    // EXPLAIN: Internal state is hidden in Kelvin (K).
    // The internal representation can change (e.g., to Celsius) without breaking callers.
    private double kelvin;

    public Temperature(double kelvin) {
        setKelvin(kelvin);
    }

    public void setKelvin(double kelvin) {
        // Absolute zero validation (0 K)
        if (kelvin < 0.0) {
            throw new IllegalArgumentException("Temperature cannot be below absolute zero (0 K).");
        }
        this.kelvin = kelvin;
    }

    // Static Factory methods for flexibility
    public static Temperature fromCelsius(double celsius) {
        return new Temperature(celsius + 273.15);
    }

    public static Temperature fromFahrenheit(double fahrenheit) {
        return new Temperature((fahrenheit - 32) * 5 / 9 + 273.15);
    }

    // External Accessors perform conversions on demand
    public double getKelvin()     { return kelvin; }
    public double getCelsius()    { return kelvin - 273.15; }
    public double getFahrenheit() { return (kelvin - 273.15) * 9 / 5 + 32; }

    public static void main(String[] args) {
        Temperature temp = Temperature.fromCelsius(25.0); // 25°C room temp
        System.out.printf("Celsius: %.2f°C%n", temp.getCelsius());
        System.out.printf("Fahrenheit: %.2f°F%n", temp.getFahrenheit());
        System.out.printf("Kelvin: %.2f K%n", temp.getKelvin());

        try {
            Temperature.fromCelsius(-300.0); // Below absolute zero (-273.15°C)
        } catch (IllegalArgumentException e) {
            System.out.println("Validation caught: " + e.getMessage());
        }
    }
}
