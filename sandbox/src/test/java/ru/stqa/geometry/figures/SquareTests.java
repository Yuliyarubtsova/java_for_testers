package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {

    @Test
    void canCalculateArea() {
        var s = new Square(5.0);
        double result = s.area();
        Assertions.assertEquals(25.0, result);
    }

    @Test
    void canCalculatePerimeter() {
        Assertions.assertEquals(20.0, new Square(5.0).perimeter());
    }

    @Test
    void canCalculatePerimeterTriangle() {
        var p = new Triangle (5, 10, 10);
        double result = p.trianglePerimeter();
        Assertions.assertEquals(25, result);
    }

    @Test
    void canCalculateAreaTriangle() {
        var q = new Triangle (5.0, 5.0, 6.0);
        double result = q.triangleArea();
        Assertions.assertEquals(12, result);
    }

}
