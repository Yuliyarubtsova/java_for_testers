import org.junit.jupiter.api.Test;

public class MathTests {

    @Test
    void testDivideByZero() {
        var x = 1;
        var y = 0;
        int z = x / y;
        System.out.println(z);
    }
}
