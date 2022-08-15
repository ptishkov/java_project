package Task;

public class PointTest {

    public void testDistance() {
        Point a = new Point(7, 3);
        Point b = new Point(15, 12);
        assert a.distance(b) == 12;
    }
}
