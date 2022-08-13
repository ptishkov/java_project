public class Point {

    public double p1;
    public double p2;

    public Point(double p1, double p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public double distance(Point a, Point b) {
        return Math.sqrt((a.p1 - b.p1) * (a.p1 - b.p1) + (a.p2 - b.p2) * (a.p2 - b.p2));
    }

}
