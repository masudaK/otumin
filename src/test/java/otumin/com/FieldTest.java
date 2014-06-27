package otumin.com;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/06/27
 * Time: 0:40
 * To change this template use File | Settings | File Templates.
 */
public class FieldTest {

    @Before
    public void setup() throws Exception {
    }

    @Test
    public void 列に置かれたカードを先頭から順番に取得できる() throws Exception {
        Field field = new Field();
        Card card1 = new Card(1,1);
        Card card2 = new Card(2,2);
        field.addCard(0, card1);
        field.addCard(0, card2);
        int i = 1;
        for(Card card : field.getLane(0).getCardsAll()){
            assertThat(card.getNumber(), is(i));
            i += 1;
        }
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

    @After
    public void tearDown() {
    }
}
