public class Task2 {
    public static double distance(Point p1, Point p2) {
        double dx = p1.getX() - p2.getX();
        double dy = p1.getY() - p2.getY();
        return Math.sqrt(dx*dx + dy*dy);
    }

    public static void main(String[] args) {
        Point p1 = new Point(-1, 2);
        Point p2 = new Point(2, -2);
        System.out.println(String.format("Расстояние между точками (%d, %d) и (%d, %d) равно %.2f", p1.getX(), p1.getY(), p2.getX(), p2.getY(), distance(p1, p2)));
    }
}
