package task;

import org.testng.annotations.Test;

public class PointTest {
    @Test
    public void testDistance() {
        Point p1 = new Point(7, 3);
        Point p2 = new Point(15, 12);
        assert p1.distance(p2) == 12;
    }
}
