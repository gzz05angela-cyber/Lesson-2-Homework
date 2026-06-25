// Practice 5 Problem 3
import java.util.*;

public class LogParser {
    
    // Log format: "[2026-06-24 14:35:22] ROBOT-Alpha: Battery voltage dropped to 10.5V"
    // Extract: timestamp, robot name, message
    
    public static String extractTimestamp(String logEntry) {
        // Extract timestamp from log entry (between [ and ])
        int start = logEntry.indexOf("[");
        int end = logEntry.indexOf("]");
        if (start == -1 || end == -1 || start >= end) {
            return ""; // Invalid format
        }
        return logEntry.substring(start + 1, end);
    }
    
    public static String extractRobotName(String logEntry) {
        // Extract robot name (after ROBOT- until :)
        // Find "ROBOT-" in the string
        int robotStart = logEntry.indexOf("ROBOT-");
        if (robotStart == -1) {
            return "";
        }
        
        // Find the colon after the robot name
        int colonPos = logEntry.indexOf(":", robotStart);
        if (colonPos == -1) {
            return "";
        }
        
        // Extract from "ROBOT-" to before the colon
        return logEntry.substring(robotStart, colonPos);
    }
    
    public static String extractMessage(String logEntry) {
        // Extract the message (everything after robot name:)
        // Find the first colon after the timestamp
        int timestampEnd = logEntry.indexOf("]");
        if (timestampEnd == -1) {
            return "";
        }
        
        // Find the next colon after the timestamp
        int colonPos = logEntry.indexOf(":", timestampEnd + 1);
        if (colonPos == -1) {
            return "";
        }
        
        // Everything after the colon
        String message = logEntry.substring(colonPos + 1).trim();
        return message;
    }
    
    public static boolean isErrorMessage(String logEntry) {
        // Check if message contains error keywords
        // Keywords: "Error", "Failed", "dropped", "Low", "Fault"
        // Hint: Use .toLowerCase() and .contains()
        
        String message = extractMessage(logEntry).toLowerCase();
        return message.contains("error") || 
               message.contains("failed") ||
               message.contains("dropped") ||
               message.contains("low") ||
               message.contains("fault");
    }
    
    // Task 3: Count total errors
    public static int countTotalErrors(String[] logs) {
        int errorCount = 0;
        for (String log : logs) {
            if (isErrorMessage(log)) {
                errorCount++;
            }
        }
        return errorCount;
    }
    
    // Task 4: Find which robot has most errors
    public static String findRobotWithMostErrors(String[] logs) {
        Map<String, Integer> errorCounts = new HashMap<>();
        
        for (String log : logs) {
            if (isErrorMessage(log)) {
                String robotName = extractRobotName(log);
                if (!robotName.isEmpty()) {
                    errorCounts.put(robotName, errorCounts.getOrDefault(robotName, 0) + 1);
                }
            }
        }
        
        // Find robot with most errors
        String worstRobot = "";
        int maxErrors = 0;
        for (Map.Entry<String, Integer> entry : errorCounts.entrySet()) {
            if (entry.getValue() > maxErrors) {
                maxErrors = entry.getValue();
                worstRobot = entry.getKey();
            }
        }
        
        return worstRobot.isEmpty() ? "No errors found" : worstRobot + " (" + maxErrors + " errors)";
    }
    
    // Task 5: Extract logs within specific time range
    public static List<String> extractLogsWithinTimeRange(String[] logs, String startTime, String endTime) {
        List<String> filteredLogs = new ArrayList<>();
        
        for (String log : logs) {
            String timestamp = extractTimestamp(log);
            if (timestamp.isEmpty()) {
                continue;
            }
            
            // Compare timestamps lexicographically (they're in ISO format)
            if (timestamp.compareTo(startTime) >= 0 && timestamp.compareTo(endTime) <= 0) {
                filteredLogs.add(log);
            }
        }
        
        return filteredLogs;
    }
    
    // Bonus: Get all unique robot names
    public static Set<String> getUniqueRobots(String[] logs) {
        Set<String> robots = new HashSet<>();
        for (String log : logs) {
            String robot = extractRobotName(log);
            if (!robot.isEmpty()) {
                robots.add(robot);
            }
        }
        return robots;
    }
    
    // Bonus: Count messages by severity
    public static String getSeverityStats(String[] logs) {
        int total = logs.length;
        int errors = countTotalErrors(logs);
        int warnings = 0;
        int info = 0;
        
        for (String log : logs) {
            String message = extractMessage(log).toLowerCase();
            if (!isErrorMessage(log)) {
                if (message.contains("warning") || message.contains("caution")) {
                    warnings++;
                } else {
                    info++;
                }
            }
        }
        
        return String.format("Total: %d, Errors: %d, Warnings: %d, Info: %d", 
                            total, errors, warnings, info);
    }
    
    public static void main(String[] args) {
        String[] logs = {
            "[2026-06-24 14:35:22] ROBOT-Alpha: Battery voltage dropped to 10.5V",
            "[2026-06-24 14:36:15] ROBOT-Beta: All systems operational",
            "[2026-06-24 14:37:03] ROBOT-Alpha: Motor 2 failed to respond",
            "[2026-06-24 14:38:44] ROBOT-Gamma: Competition mode activated",
            "[2026-06-24 14:39:10] ROBOT-Alpha: Low battery warning - 9.8V",
            "[2026-06-24 14:40:55] ROBOT-Beta: Sensor fault detected on arm",
            "[2026-06-24 14:41:30] ROBOT-Gamma: Movement completed successfully",
            "[2026-06-24 14:42:15] ROBOT-Beta: Error: Communication timeout"
        };
        
        System.out.println("=== Log Analysis ===");
        for (String log : logs) {
            System.out.println("\nLog entry: " + log);
            System.out.println("  Time: " + extractTimestamp(log));
            System.out.println("  Robot: " + extractRobotName(log));
            System.out.println("  Message: " + extractMessage(log));
            System.out.println("  Error? " + (isErrorMessage(log) ? "YES" : "NO"));
        }
        
        // Task 3: Count total errors
        System.out.println("\n=== Error Statistics ===");
        System.out.println("Total errors: " + countTotalErrors(logs));
        
        // Task 4: Find robot with most errors
        System.out.println("Robot with most errors: " + findRobotWithMostErrors(logs));
        
        // Bonus: Unique robots
        System.out.println("Unique robots: " + getUniqueRobots(logs));
        
        // Bonus: Severity stats
        System.out.println("Severity stats: " + getSeverityStats(logs));
        
        // Task 5: Time range extraction
        System.out.println("\n=== Time Range Filtering ===");
        List<String> timeRangeLogs = extractLogsWithinTimeRange(
            logs, 
            "2026-06-24 14:36:00", 
            "2026-06-24 14:40:00"
        );
        System.out.println("Logs between 14:36 and 14:40:");
        for (String log : timeRangeLogs) {
            System.out.println("  " + log);
        }
        
        // Test edge cases
        System.out.println("\n=== Edge Case Testing ===");
        System.out.println("Extract timestamp from empty: '" + extractTimestamp("") + "'");
        System.out.println("Extract robot from invalid: '" + extractRobotName("Invalid log") + "'");
        System.out.println("Extract message from invalid: '" + extractMessage("No colon here") + "'");
        
        // Additional edge test
        String edgeLog = "[2026-06-24] ROBOT-Test: Message with colon: inside it";
        System.out.println("\nTesting complex message:");
        System.out.println("  Log: " + edgeLog);
        System.out.println("  Message: " + extractMessage(edgeLog));
    }
}