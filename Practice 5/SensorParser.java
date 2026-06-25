// Practice 5 Problem 1


public class SensorParser {

    public static String extractValue(String data, String key) {
        // Find the start of the key
        int keyStart = data.indexOf(key + ":");
        if (keyStart == -1) {
            return null; // Key not found
        }
        
        // Find the colon position after the key
        int colonP = data.indexOf(":", keyStart);
        if (colonP == -1) {
            return null; // No colon found
        }
        
        // Find the next comma after the colon (end of value)
        int commaP = data.indexOf(",", colonP + 1);
        
        // Extract value: if no comma found, take rest of string
        if (commaP == -1) {
            return data.substring(colonP + 1);
        } else {
            return data.substring(colonP + 1, commaP);
        }
    }


    public static void main(String[] args) {
        // Sensor sends data like: "TEMP:25.5,HUMIDITY:60"
        String sensorData = "TEMP:25.5,HUMIDITY:60";

        int tempStart = sensorData.indexOf("TEMP:");
        int colonPos = sensorData.indexOf(":", tempStart);
        int commaPos = sensorData.indexOf(",", colonPos);
        String tempString = sensorData.substring(colonPos + 1, commaPos);
        double temperature = Double.parseDouble(tempString);
        System.out.println("Temperature: " + temperature + "°C");
        
        // TODO: Now extract humidity yourself

        int humidityStart = sensorData.indexOf("HUMIDITY:");
        int colonPOS = sensorData.indexOf(":", humidityStart);
        int commaPOS = sensorData.indexOf(",", colonPOS);
        String humidityString;
        if (commaPOS == -1) {
            humidityString = sensorData.substring(colonPOS + 1);
        } else {
            humidityString = sensorData.substring(colonPOS + 1, commaPOS);
        }
        int humidity = Integer.parseInt(humidityString);
        System.out.println("Humidity: " + humidity);
        // TODO: Write a method extractValue(String data, String key) that works for both

        String tempStringHelped = extractValue(sensorData, "TEMP");
        double temperatureHelped = Double.parseDouble(tempStringHelped);
        System.out.println("Temperature: " + temperatureHelped + "°C");
        
        // Extract humidity using the helper method
        String humidityStringHelped = extractValue(sensorData, "HUMIDITY");
        int humidityHelped = Integer.parseInt(humidityStringHelped);
        System.out.println("Humidity: " + humidityHelped + "%");
    }
}
