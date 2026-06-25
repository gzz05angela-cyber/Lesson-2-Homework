// Practice 1

public class BatteryCalculator {
    public static void main(String[] args) {
        double currentVoltage = 12.5;
        double minVoltage = 10;
        double maxVoltage = 13.2;
        
        double rawpercentage = (double)((currentVoltage - minVoltage)/(maxVoltage - minVoltage) * 100);

        if (rawpercentage < 0){
            rawpercentage = 0;
        }

        if (rawpercentage > 100){
            rawpercentage = 100;
        }
        
        int percentage = (int)rawpercentage;
        int batteryStatus = percentage;

        System.out.println("Battery: " + percentage + "%");
        System.out.println("Status: " + batteryStatus);
        
        // Battery: 78%
        // Status: 78

        double result = (double)(5/2);
        System.out.println("5 / 2 = " + result);
    }
}