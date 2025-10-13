import java.io.File;

public class Hello {
    public static void main(String[] args) {
//        var x = 6;
//        var y = 2;
//        if (y == 0) {
//            System.out.println("Division by zero is not allowed");
//        } else {
//            var z = divide(x, y);
//            System.out.println("Hello, world!");
//            }

        System.out.println("Hello, world!");

        var configFile = new File("sandbox/build.gradle");
        System.out.println(configFile.getAbsolutePath());
        System.out.println(configFile.exists());

        System.out.println(new File("").getAbsolutePath());
        }


    private static int divide(int x, int y) {
        var z = x / y;
        return z;
    }


}
