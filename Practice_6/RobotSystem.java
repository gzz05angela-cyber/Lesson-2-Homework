package Lesson_2_Homework.Practice_6;

import java.util.Scanner;

public class RobotSystem {
    
    private static final int NUM_ROBOTS = 3;
    private static final int NUM_CATEGORIES = 4;
    private static final String[] CATEGORY_NAMES = {
        "Motor Efficiency", 
        "Battery Management", 
        "Autonomous Performance", 
        "Reliability"
    };
    
    static class Robot {
        String name;
        float[] scores = new float[NUM_CATEGORIES];
        float overallScore;
        int rank;
        String analysis;
        
        Robot(String name) {
            this.name = name;
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Robot[] robots = new Robot[NUM_ROBOTS];
        
        String[] robotNames = {"Alpha", "Beta", "Gamma"};
        
        for (int i = 0; i < NUM_ROBOTS; i++) {
            robots[i] = new Robot(robotNames[i]);
            System.out.println("\n=== Entering data for Robot " + robotNames[i] + " ===");
            
            for (int j = 0; j < NUM_CATEGORIES; j++) {
                float score = getValidatedScore(scanner, CATEGORY_NAMES[j]);
                robots[i].scores[j] = score;
            }

            robots[i].overallScore = calculateOverallScore(robots[i].scores);
        }

        rankRobots(robots);

        for (Robot robot : robots) {
            robot.analysis = analyzeRobot(robot);
        }        

        displayResults(robots);
        
        scanner.close();
    }

    public static float getValidatedScore(Scanner scanner, String categoryName) {
        float score = -1;
        boolean valid = false;
        
        while (!valid) {
            System.out.print("Enter " + categoryName + " (0-100): ");
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter a number.");
                continue;
            }
            
            try {
                score = Float.parseFloat(input);
                
                if (score < 0) {
                    System.out.println("Negative value detected. Setting to 0.");
                    score = 0;
                    valid = true;
                } else if (score > 100) {
                    System.out.println("Value exceeds 100. Capping at 100.");
                    score = 100;
                    valid = true;
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }
        
        return score;
    }

    public static float calculateOverallScore(float[] scores) {
        float sum = 0;
        for (float score : scores) {
            sum += score;
        }
        return sum / NUM_CATEGORIES;
    }

    public static void rankRobots(Robot[] robots) {
        int n = robots.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (robots[j].overallScore < robots[j + 1].overallScore) {
                    Robot temp = robots[j];
                    robots[j] = robots[j + 1];
                    robots[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (i > 0 && robots[i].overallScore == robots[i-1].overallScore) {
                robots[i].rank = robots[i-1].rank;
            } else {
                robots[i].rank = i + 1;
            }
        }
    }

    public static String analyzeRobot(Robot robot) {
        float[] scores = robot.scores;
        
        float maxScore = scores[0];
        float minScore = scores[0];
        int maxIndex = 0;
        int minIndex = 0;
        
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > maxScore) {
                maxScore = scores[i];
                maxIndex = i;
            }
            if (scores[i] < minScore) {
                minScore = scores[i];
                minIndex = i;
            }
        }
        
        String analysis = robot.name;
        
        if (maxScore >= 85 && minScore >= 70) {
            analysis += " is a top performer in all categories";
        } else if (maxScore >= 85 && minScore < 70) {
            analysis += " excels at " + CATEGORY_NAMES[maxIndex] + 
                       " but needs better " + CATEGORY_NAMES[minIndex];
        } else if (minScore < 50) {
            analysis += " needs significant improvement in " + CATEGORY_NAMES[minIndex];
        } else {
            analysis += " shows balanced performance with room for improvement in " + 
                       CATEGORY_NAMES[minIndex];
        }
        
        analysis += " (Best: " + String.format("%.1f", maxScore) + 
                   "%, Worst: " + String.format("%.1f", minScore) + "%)";
        
        return analysis;
    }

    public static void displayResults(Robot[] robots) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                    ROBOT SCORING SYSTEM - FINAL RESULTS");
        System.out.println("=".repeat(80));
        
        // 打印表头
        System.out.println("Rank  Robot        Motor Eff.  Battery Mgmt Auto Perform Reliability Overall  Analysis");
        System.out.println("-".repeat(80));
        
        // 打印每个机器人的数据
        for (Robot robot : robots) {
            System.out.print(robot.rank + "     " + robot.name + "        ");
            
            for (float score : robot.scores) {
                System.out.print(String.format("%-12.1f", score));
            }
            
            System.out.print(String.format("%-9.1f", robot.overallScore));
            System.out.println("  " + robot.analysis);
        }
        
        System.out.println("=".repeat(80));

        float total = 0;
        float highest = robots[0].overallScore;
        float lowest = robots[0].overallScore;
        String highestName = robots[0].name;
        String lowestName = robots[0].name;
        
        for (Robot robot : robots) {
            total += robot.overallScore;
            if (robot.overallScore > highest) {
                highest = robot.overallScore;
                highestName = robot.name;
            }
            if (robot.overallScore < lowest) {
                lowest = robot.overallScore;
                lowestName = robot.name;
            }
        }
        
        float average = total / robots.length;
        
        System.out.println("\nSTATISTICS:");
        System.out.println("  Average Score: " + String.format("%.1f", average));
        System.out.println("  Highest Score: " + String.format("%.1f", highest) + " (" + highestName + ")");
        System.out.println("  Lowest Score: " + String.format("%.1f", lowest) + " (" + lowestName + ")");
        System.out.println("=".repeat(80));
    }
}