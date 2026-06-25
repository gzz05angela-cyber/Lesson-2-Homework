# Practie 2
```java
public class TraceMe {
    public static int mystery(int x) {
        int result = x * x - x;
        return result;
    }
    
    public static String makeLabel(String name, int value) {
        String output = name + ": " + value;
        return output;
    }
    
    public static void main(String[] args) {
        int a = 4;
        int b = mystery(a);
        int c = mystery(b);
        
        System.out.println(makeLabel("Result", c));
        
        // Predict: What value of c?
        // Trace: a=4, b=mystery(4), c=mystery(b)
        // Show your work on paper before running
    }

    // a = 4, b = 12, c = 132
    // Output:
    // Result: 132
}
```

**More Complex Version:**
```java
public class ComplexTrace {
    public static int calculate(int x, int y) {
        int temp = x + y;
        temp = temp * 2;
        return temp - 1;
    }
    
    public static void main(String[] args) {
        int m = 3;
        int n = 5;
        
        int result1 = calculate(m, n);
        int result2 = calculate(result1, m);
        
        System.out.println("Result 1: " + result1);
        System.out.println("Result 2: " + result2);
        
        // Trace this:
        // result1 = calculate(3, 5)
        //   temp = 3 + 5 = 8
        //   temp = 8 * 2 = 16
        //   return 16 - 1 = 15
        // result2 = calculate(15, 3)
        //   temp = 15 + 3 = 18
        //   temp = 18 * 2 = 36
        // return 36 - 1 = 35
        //Output:
        // Result 1: 15
        // Result 2: 35
    }
}
```