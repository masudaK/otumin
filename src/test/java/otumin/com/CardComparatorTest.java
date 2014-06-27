package otumin.com;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/06/24
 * Time: 0:49
 * To change this template use File | Settings | File Templates.
 */
public class CardComparatorTest {

    @Before
    public void setup() throws Exception {
    }

    @Test
    public void 二つのカードの数を比較しその差を返す() throws Exception {
        CardComparator cc = new CardComparator();
        Card cardA = new Card(1,1);
        Card cardB = new Card(2,1);
        int expected = -1;
        assertThat(cc.compare(cardA, cardB), is(expected));
    }

    @After
    public void tearDown() {
    }
}
