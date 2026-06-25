// Practice 4 Buggy Hunt 3

public class BuggyMethods {
    public static int adjustPower(int motorPower) {
        motorPower = motorPower + 20;  // BUG: This looks right but...
        System.out.println("In method: " + motorPower);
        return motorPower;
    }
    
    public static void main(String[] args) {
        int power = 50;
        adjustPower(power);
        System.out.println("In main: " + adjustPower(power));
        
        // Predict: Does power change to 70? Why/why not?
        // No, the method doesn't affect the local variable power;
    }
}
