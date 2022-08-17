package task;

import org.testng.Assert;

public class FourPointTest {
    @org.testng.annotations.Test
    public void testDistance() {
        Point p7 = new Point(0.02, 100);
        Point p8 = new Point(-5, 22.22);
        Assert.assertEquals(p7.distance(p8), 77.94182959104822);
    }
}
