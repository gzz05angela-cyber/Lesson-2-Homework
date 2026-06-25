// Problem 4 Buggy Hunt 2

public class BuggyStrings {
    public static void main(String[] args) {
        int score = 50;
        int bonus = 10;
        
        System.out.println("Score: " + score + bonus);  // What prints?
        System.out.println("Score: " + (score + bonus)); // Different?
        
        // Why are these different?
        // Predict both outputs before running
    }
}

// Output1:
// Score: 5010
// Output2:
// Score: 60