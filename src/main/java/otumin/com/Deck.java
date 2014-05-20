package otumin.com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/05/10
 * Time: 20:10
 * To change this template use File | Settings | File Templates.
 */
public class Deck {

    private List<Card> deck;

    // コンストラクタ
    public Deck(){
        this.deck = new ArrayList<Card>(104);
    }

    public List<Card> getDeck(){
        return deck;
    }

    public List<Card> create(){
        Card card = new Card(1,1);
        deck.add(card);
        return deck;
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }
}

