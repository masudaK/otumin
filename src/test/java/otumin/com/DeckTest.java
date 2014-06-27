package otumin.com;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/06/25
 * Time: 0:36
 * To change this template use File | Settings | File Templates.
 */
public class DeckTest {

    @Before
    public void setup() throws Exception {
    }

    @Test
    public void 作成されたデッキを先頭から取得すると数は1から10までの連番になる() throws Exception {
        Deck deck = new Deck();
        deck.create();
        int i = 1;
        for(Card card : deck.getCards(10)){
            assertThat(card.getNumber(), is(i));
            i += 1;
        }
    }

    @After
    public void tearDown() {
    }
}
