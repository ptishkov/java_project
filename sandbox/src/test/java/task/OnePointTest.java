package task;

import org.testng.Assert;

public class OnePointTest {
    @org.testng.annotations.Test
    public void testDistance() {
        Point p1 = new Point(7, 3);
        Point p2 = new Point(15, 12);
        Assert.assertEquals(p1.distance(p2), 12.041594578792296);
    }
}
