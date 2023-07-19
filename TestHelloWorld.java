import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestHelloWorld {
    public static void main(String[] args) {
        // Save the original System.out for later restoration
        PrintStream originalOut = System.out;

        // Redirect standard output to a ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Run the main method of HelloWorld class (contained in the JAR)
        HelloWorld.main(new String[0]);

        // Restore the original System.out
        System.setOut(originalOut);

        // Get the output from the ByteArrayOutputStream
        String output = outputStream.toString();

        // Check if the output contains "Hello, World!"
        if (output.contains("Hello, World!")) {
            System.out.println("Test Passed: Hello, World! was printed successfully.");
        } else {
            System.out.println("Test Failed: Hello, World! was not printed.");
        }
    }
}
