package tests;

import graph.CapGraph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * Created by Damian on 11/2/2017.
 */
public class CapGraphTest {
    CapGraph graphEmpty;
    CapGraph graphSmall;

    @Before
    public void setUp() {
        graphEmpty = new CapGraph();
        graphSmall = new CapGraph();
        graphSmall.addVertex(1);
        graphSmall.addVertex(2);
        graphSmall.addEdge(1, 2);
    }

    @Test
    public void testSize() {
        Assert.assertEquals(graphEmpty.getGraphSize(), 0);
        Assert.assertEquals(graphSmall.getGraphSize(), 2);
    }

    @Test
    public void testHasVertex() {
        Assert.assertFalse(graphEmpty.isVertex(0));
        Assert.assertFalse(graphSmall.isVertex(0));
        Assert.assertTrue(graphSmall.isVertex(1));
        Assert.assertTrue(graphSmall.isVertex(2));
    }

    @Test
    public void testHasEdge() {
        Assert.assertFalse(graphSmall.isEdge(2, 1));
        Assert.assertTrue(graphSmall.isEdge(1, 2));
    }

    @Test
    public void testAddEdge() {
        try {
            graphEmpty.addEdge(1, 2);
            fail("There is no 1,2");
        } catch (IllegalArgumentException e) {
        }
    }
}
