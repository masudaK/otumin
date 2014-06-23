package otumin.com;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/06/23
 * Time: 23:58
 * To change this template use File | Settings | File Templates.
 */
public class CardTest {

    @Test
    public void testGetNumber() throws Exception {
        Card card = new Card(1,1);
        assertEquals(1, card.getNumber());
    }

    @Test
    public void testGetMinusPoint() throws Exception {
        Card card = new Card(1,2);
        assertEquals(2, card.getMinusPoint());
    }

    @Test
    public void testSetOwner() throws Exception {
        // TODO: setterなのにgetterもテストしてるので、問題ないか確認する
        Card card = new Card(1,2);
        card.setOwner(0);
        assertEquals(0, card.getOwner());
    }

}
