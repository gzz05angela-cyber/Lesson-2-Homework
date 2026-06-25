// Practice 3 Problem 1
public class MotorSpeedAnalyzer {
    public static void main(String[] args) {
        int[] motorSpeed = {100, 95, 88, 110};
        int s = motorSpeed.length;
        int total = 0;
        for (int i = 0; i < s; i++){
            total = total + motorSpeed[i];
        }
        double average = (double)(total/s);

        int fastest = motorSpeed[0];
        for (int i = 1; i < s; i++) {
            if (motorSpeed[i] > fastest) {
                fastest = motorSpeed[i];
            }
        }

        int slowest = motorSpeed[0];
        for (int i = 1; i < s; i++){
            if (motorSpeed[i] < slowest){
                slowest = motorSpeed[i];
            }
        }
        
        System.out.println("\n=== Professional Report ===");
        System.out.println("The average motor speed is " + average);
        System.out.println("The fastest moto speed is " + fastest);
        System.out.println("The slowest motor speed is " + slowest);

        for (int i = 0; i<s; i++){
            if (motorSpeed[i] > 255){
                System.out.println("Warning! The " + i + "motor Speed is over limit!");
            }
        }

    }
}
