// Practice 3 Problem 2

public class RobotStatus {
    private String name;
    private double batteryVoltage;
    private int motorPower;
    private double percentage;
    private boolean needCharge;

    public RobotStatus(String name, double batteryVoltage, int motorPower){
        this.name = name;
        this.batteryVoltage = batteryVoltage;
        this.motorPower = motorPower;
    }

    public double getPercentage(){
        double minVoltage = 10.0;
        double maxVoltage = 13.2;
        double percentage = (batteryVoltage - minVoltage)/(maxVoltage - minVoltage) * 100;

        if (percentage < 0.0){
            percentage = 0.0;
        }
        if (percentage > 100.0){
            percentage = 100.0;
        }

        return percentage;
    }

    public boolean doNeedCharge(){
        if (percentage < 30){
            needCharge = true;
        } else {
            needCharge = false;
        }
        return needCharge;
    }

    public String getStatus(){
        percentage = getPercentage();
        needCharge = doNeedCharge();
        String status = name + " has " + batteryVoltage + "V left, with " + motorPower + "W.\n"
            + "The voltage percentage of " + name + " is " + percentage + "%.\n";
        if (needCharge){
            status += name + " needs to be charged.";
        } else {
            status += name + " doesn't need charge";
        }
        System.out.println(status);
        return status;
    }

    public static void main(String[] args) {
        RobotStatus robot1 = new RobotStatus("6941", 13.0, 50);
        robot1.getPercentage();
        robot1.doNeedCharge();
        robot1.getStatus();

        RobotStatus robot2 = new RobotStatus("10541", 2.4, 12);
        robot2.getPercentage();
        robot2.doNeedCharge();
        robot2.getStatus();
    }
}
