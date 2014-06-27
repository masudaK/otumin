package otumin.com;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/06/23
 * Time: 23:58
 * To change this template use File | Settings | File Templates.
 */
public class CardTest {

    @Before
    public void setup() throws Exception {
    }

    @Test
    public void コンストラクタでセットしたカードの数を取得できる() throws Exception {
        Card card = new Card(1,1);
        int expected = 1;
        assertThat(card.getNumber(), is(expected));
    }

    @Test
    public void コンストラクタでセットしたカードのマイナスポイントの値を取得できる() throws Exception {
        Card card = new Card(1,2);
        int expected = 2;
        assertThat(card.getMinusPoint(), is(expected));
    }

    @Test
    public void オーナーをセットし取得もできる() throws Exception {
        Card card = new Card(1,2);
        card.setOwner(0);
        int expected = 0;
        assertThat(card.getOwner(), is(expected));

    }

    @After
    public void tearDown() {
    }

}
