package otumin.com;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/06/24
 * Time: 0:49
 * To change this template use File | Settings | File Templates.
 */
public class CardComparatorTests {

    @Test
    public void testCompare() throws Exception {
        CardComparator cc = new CardComparator();
        Card cardA = new Card(1,1);
        Card cardB = new Card(2,1);
        assertEquals(1, cc.compare(cardA, cardB));
    }
}
