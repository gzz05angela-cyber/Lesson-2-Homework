// Practice 5 Problem 2
public class CommandParser {
    
    public static boolean isValidCommand(String command) {
        if (command.equals("STOP")) {
            return true;
        }
        if (!command.contains(":")) {
            return false;  // Commands except STOP need ":"
        }

        int colonPos = command.indexOf(":");
        String commandType = command.substring(0, colonPos);
        String valueStr = command.substring(colonPos + 1);

        if (!commandType.equals("MOVE") && !commandType.equals("TURN")) {
            return false;
        }

        try {
            int value = Integer.parseInt(valueStr);
            
            // Validate ranges
            if (commandType.equals("MOVE")) {
                return value >= 0 && value <= 100;
            } else if (commandType.equals("TURN")) {
                return value >= -180 && value <= 180;
            }
        } catch (NumberFormatException e) {
            return false;  // Not a valid number
        }
        
        return false;
    }
    
    public static String getCommandType(String command) {
        if (command.equals("STOP")){
            return "Stop";
        }

        int colonPos = command.indexOf(":");
        if (colonPos == -1) {
            return "";  // Invalid command
        }
        
        return command.substring(0, colonPos);
    }
    
    public static int getCommandValue(String command) {
        if (command == null || command.isEmpty() || command.equals("STOP")) {
        return 0;
        }

        int colonPos = command.indexOf(":");
        if (colonPos == -1) {
            return 0;
        }

        String valueStr = command.substring(colonPos + 1);
        try {
            int value = Integer.parseInt(valueStr);
            // Optionally validate range here too
            return value;
         } catch (NumberFormatException e) {
             return 0;
         }
    }
    
    public static void main(String[] args) {
        // Test cases
        String[] commands = {
            "MOVE:50",      // Valid
            "TURN:90",      // Valid
            "STOP",         // Valid
            "MOVE:150",     // Invalid (out of range)
            "TURN:-200",    // Invalid (out of range)
            "JUMP:20",      // Invalid (unknown command)
            "MOVE:abc",     // Invalid (not a number)
            "MOVEFAST"      // Invalid (no colon)
        };
        
        for (String cmd : commands) {
            boolean valid = isValidCommand(cmd);
            System.out.println(cmd + " -> " + (valid ? "VALID" : "INVALID"));
            
            if (valid) {
                System.out.println("  Type: " + getCommandType(cmd));
                System.out.println("  Value: " + getCommandValue(cmd));
            }
        }
    }
}
