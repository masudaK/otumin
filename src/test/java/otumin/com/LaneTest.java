package otumin.com;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/06/28
 * Time: 2:17
 * To change this template use File | Settings | File Templates.
 */
public class LaneTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void レーンにカードを配置しそのカードの数を取得する() throws Exception {
        Lane lane = new Lane();
        Card cardA = new Card(1,1);
        Card cardB = new Card(2,2);
        lane.addCard(cardA);
        lane.addCard(cardB);

        int i = 1;
        for(Card card : lane.getCardsAll()){
            assertThat(card.getNumber(), is(i));
            assertThat(card.getMinusPoint(), is(i));
            i += 1;
        }
    }

    @Test
    public void レーンにカードを配置しそのカードのマイナスポイントを取得する() throws Exception {
        Lane lane = new Lane();
        Card cardA = new Card(1,1);
        Card cardB = new Card(2,2);
        lane.addCard(cardA);
        lane.addCard(cardB);

        int i = 1;
        for(Card card : lane.getCardsAll()){
            assertThat(card.getMinusPoint(), is(i));
            i += 1;
        }
    }

    @Test
    public void レーンにカードを配置しそのカードの大きさを取得する() throws Exception {
        Lane lane = new Lane();
        Card cardA = new Card(1,1);
        lane.addCard(cardA);
        int expected = 1;
        assertThat(lane.getSize(), is(expected));
    }

    @Test
    public void レーンにカード全てのマイナスポイントを合算する() throws Exception {
        Lane lane = new Lane();
        Card cardA = new Card(1,1);
        Card cardB = new Card(2,2);
        lane.addCard(cardA);
        lane.addCard(cardB);
        int sum = 0;
        for(Card card : lane.getCardsAll()){
            sum += card.getNumber();
        }

        int expected = 3;
        assertThat(sum, is(expected));
    }

    @After
    public void tearDown() throws Exception {
    }

}
