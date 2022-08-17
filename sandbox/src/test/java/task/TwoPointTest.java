package task;

import org.testng.Assert;

public class TwoPointTest {
    @org.testng.annotations.Test
    public void testDistance() {
        Point p3 = new Point(0, 0);
        Point p4 = new Point(11, 22);
        Assert.assertEquals(p3.distance(p4), 24.596747752497688);
    }
}
