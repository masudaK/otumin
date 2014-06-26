package otumin.com;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/06/27
 * Time: 0:40
 * To change this template use File | Settings | File Templates.
 */
public class FieldTests {
    @Test
    public void testGetLane() throws Exception {
        Field field = new Field();
        assertEquals(1, field.getLane(0));

    }

    @Test
    public void testAddCard() throws Exception {
        Field field = new Field();
        field.addCard(Card);
        // addのテストって。。。
    }

    @Test
    public void testCollectLastIndexCard() throws Exception {
        // TODO: test

    }

    @Test
    public void testGetMinimumDistanceIndex() throws Exception {
        // TODO: test
    }

    @Test
    public void testPrintAllLaneCards() throws Exception {
        // TODO: test
    }

    @Test
    public void testGetAllLaneCards() throws Exception {
        // TODO: test
    }

    @Test
    public void testGetLaneWithMinimumMinusPoint() throws Exception {
        // TODO: test
    }
}
