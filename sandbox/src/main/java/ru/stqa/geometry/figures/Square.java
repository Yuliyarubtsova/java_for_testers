package ru.stqa.geometry.figures;

public class Square {

    public double side;

    public Square(double side) {
        this.side = side;
    }

    public static void printSquareArea(Square s) {
        String text = String.format("Площадь квадрата со стороной %f = %f", s.side, s.area());
        System.out.println(text);

    }
//    public static double area(double a) {
//        return a * a;
//    }

//    public static double perimeter(double a) {
//        return 4 * a;
//    }

    public double area() {
        return this.side * this.side;
    }

    public double perimeter() {
        return 4 * this.side;
    }
}



