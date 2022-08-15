package task;


import org.testng.Assert;

public class PointTest {
    @org.testng.annotations.Test
    public void testDistance() {
        Point p1 = new Point(7, 3);
        Point p2 = new Point(15, 12);
        Assert.assertEquals(p1.distance(p2), 12.041594578792296);

        Point p3 = new Point(20, 40);
        Point p4 = new Point(11, 22);
        Assert.assertEquals(p3.distance(p4), 20.12461179749811);

    }
}
