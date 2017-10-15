package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance() {
        Point p1 = new Point(1, -2);
        Assert.assertEquals(p1.getDistanceFrom(new Point(-2, 2)), 5.0);
    }

    @Test
    public void testDistanceToZero() {
        Point p1 = new Point(3, 4);
        Assert.assertEquals(p1.getDistanceFrom(new Point(0, 0)), 6.0);
    }

    @Test
    public void testDistanceToSelf() {
        Point p1 = new Point(1, 2);
        Assert.assertEquals(p1.getDistanceFrom(p1), 0.0);
    }

}
