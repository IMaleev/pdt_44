package ru.stqa.pft.sandbox;

public class Task2 {

    public static void main(String[] args) {
        Point p1 = new Point(-1, 2);
        Point p2 = new Point(2, -2);
        System.out.println(String.format("Расстояние между точками (%d, %d) и (%d, %d) равно %.2f", p1.getX(), p1.getY(), p2.getX(), p2.getY(), p1.getDistanceFrom(p2)));
    }
}
