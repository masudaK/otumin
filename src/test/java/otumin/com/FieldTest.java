package otumin.com;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
    public void 各列に置かれた最後のカードのリストを作成する() throws Exception {
        Field field = setupCardOfLanes();

        List<Integer> lastNumbers = new ArrayList<Integer>(4);
        lastNumbers.addAll(field.collectLastIndexCard());

        assertThat(lastNumbers, containsInAnyOrder(20,30,40,50));
        assertThat(lastNumbers.size(), is(4));
    }

    @Test
    public void 各列に置かれた最後のカードとの最短距離を計測する() throws Exception {
        Field field = setupCardOfLanes();
        Card card = new Card(21, 21);

        int minimumIndex = field.getMinimumDistanceIndex(card.getNumber(), field.collectLastIndexCard());
        assertThat(minimumIndex, is(0));
    }

    @Test
    public void 各列に置かれた最後のカードとの最短距離が計測できない場合はマイナス1を返す() throws Exception {
        Field field = setupCardOfLanes();
        Card card = new Card(3, 3);

        int minimumIndex = field.getMinimumDistanceIndex(card.getNumber(), field.collectLastIndexCard());
        assertThat(minimumIndex, is(-1));
    }

    private Field setupCardOfLanes(){
        Field field = new Field();
        Card card0OfLane0 = new Card(10,1);
        Card card1OfLane0 = new Card(20,2);
        Card card0OfLane1 = new Card(30,3);
        Card card0OfLane2 = new Card(40,4);
        Card card0OfLane3 = new Card(50,5);

        field.addCard(0, card0OfLane0);
        field.addCard(0, card1OfLane0);
        field.addCard(1, card0OfLane1);
        field.addCard(2, card0OfLane2);
        field.addCard(3, card0OfLane3);

        return field;
    }

    @After
    public void tearDown() {
    }
}
