package ru.stqa.geometry.figures;

import java.util.Objects;

public record Triangle(double x, double y, double z) {

    public Triangle {
        if (x < 0 || y < 0 || z < 0) {
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }
        if (x + y < z || x + z < y || z + y < x) {
            throw new IllegalArgumentException("Triangle sides are incorrect");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Double.compare(triangle.x, this.x) == 0 && Double.compare(triangle.y, this.y) == 0 && Double.compare(triangle.z, this.z) == 0
            || Double.compare(triangle.x, this.y) == 0 && Double.compare(triangle.y, this.z) == 0 && Double.compare(triangle.z, this.x) == 0
            || Double.compare(triangle.x, this.x) == 0 && Double.compare(triangle.y, this.z) == 0 && Double.compare(triangle.z, this.y) == 0
            || Double.compare(triangle.x, this.z) == 0 && Double.compare(triangle.y, this.y) == 0 && Double.compare(triangle.z, this.x) == 0
            || Double.compare(triangle.x, this.y) == 0 && Double.compare(triangle.y, this.x) == 0 && Double.compare(triangle.z, this.z) == 0
            || Double.compare(triangle.x, this.y) == 0 && Double.compare(triangle.y, this.x) == 0 && Double.compare(triangle.z, this.y) == 0;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    public static void printTrianglePerimeter(double x, double y, double z) {
        var text = String.format("Периметр треугольника со сторонами %f и %f и %f = %f", x, y, z, trianglePerimeter(x, y, z));
        System.out.println(text);
    }

    public static double trianglePerimeter(double x, double y, double z) {
        return x + y + z;
    }


    public double trianglePerimeter() {
        return this.x + this.y + this.z;
    }

    public static void printTriangleArea(double x, double y, double z) {
        var text = String.format("Площадь треугольника со сторонами %f и %f и %f = %f", x, y, z, triangleArea(x, y, z));
        System.out.println(text);
    }
    public static double triangleArea(double x, double y, double z) {
        double p = (x + y + z ) / 2 ;
        return Math.sqrt(p * (p - x) * (p - y) * (p - z));
    }

    public double triangleArea() {
        double p = (this.x + this.y + this.z) / 2;
        return Math.sqrt(p * (p - this.x) * (p - this.y) * (p - this.z));
    }
}

