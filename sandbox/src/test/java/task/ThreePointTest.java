package task;

import org.testng.Assert;

public class ThreePointTest {
    @org.testng.annotations.Test
    public void testDistance() {
        Point p5 = new Point(0, 44.5);
        Point p6 = new Point(12, -8);
        Assert.assertEquals(p5.distance(p6), 53.85396921304873);
    }
}
