package otumin.com;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/06/25
 * Time: 0:36
 * To change this template use File | Settings | File Templates.
 */
public class DeckTests {


    //@Test
    //public void testRemoveAndGetCardByFirst() throws Exception {
    //    Deck deck = new Deck();
    //    assertEquals(1, deck);
    //}

    @Test
    public void testGetCards() throws Exception {
        Deck deck = new Deck();
        //List<Card> internalCard = new ArrayList<Card>();
        deck.create();
        assertEquals(1, deck.getCards(1));
    }
}
