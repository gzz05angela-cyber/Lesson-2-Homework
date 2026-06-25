// Practice 4 Buggy Hunt 1

public class BuggyCalculator {
    public static double convertCelsius(double tempF) {
        double tempC = (double)((tempF - 32.0) * 5 / 9);  // BUG: Wrong type
        return Math.round(tempC * 10.0) / 10.0;
    }
    
    public static void main(String[] args) {
        double celsius = convertCelsius(72.0);  // Room temperature
        System.out.println("72°F = " + celsius + "°C");  // What's wrong?
    }
}

