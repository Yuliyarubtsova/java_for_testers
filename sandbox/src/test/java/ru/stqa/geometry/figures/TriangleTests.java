package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculatePerimeterTriangle() {
        var p = new Triangle(5, 10, 10);
        double result = p.trianglePerimeter();
        Assertions.assertEquals(25, result);
    }

    @Test
    void canCalculateAreaTriangle() {
        var q = new Triangle(5.0, 5.0, 6.0);
        double result = q.triangleArea();
        Assertions.assertEquals(12, result);
    }

    @Test
    void cannotCreateTriangleWithNegativeSide() {
        try {
            new Triangle(5.0, 3.0, -6.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
    }

    @Test
    void checkSumOfTwoSidesIncorrect() {
        try {
            new Triangle(5.0, 3.0, 55.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
    }

    @Test
    void testEquality() {
        var t1 = new Triangle(5.0,7.0, 6.0);
        var t2 = new Triangle(5.0,7.0,6.0);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality2() {
        var t1 = new Triangle(3.0,4.0,5.0);
        var t2 = new Triangle(5.0,4.0,3.0);
        Assertions.assertEquals(t1, t2);
    }
}





