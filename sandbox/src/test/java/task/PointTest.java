package task;

import org.testng.Assert;

public class PointTest {
    @org.testng.annotations.Test
    public void testDistance1() {
        Point p1 = new Point(7, 3);
        Point p2 = new Point(15, 12);
        Assert.assertEquals(p1.distance(p2), 12.041594578792296);
    }
    @org.testng.annotations.Test
    public void testDistance2() {
        Point p3 = new Point(0, 0);
        Point p4 = new Point(11, 22);
        Assert.assertEquals(p3.distance(p4), 24.596747752497688);
    }
    @org.testng.annotations.Test
    public void testDistance3() {
        Point p5 = new Point(0, 44.5);
        Point p6 = new Point(12, -8);
        Assert.assertEquals(p5.distance(p6), 53.85396921304873);
    }
    @org.testng.annotations.Test
    public void testDistance4() {
        Point p7 = new Point(0.02, 100);
        Point p8 = new Point(-5, 22.22);
        Assert.assertEquals(p7.distance(p8), 77.94182959104822);
    }
}
