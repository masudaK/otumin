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

    public List<Card> create(){
        Card card1 = new Card(1,1);
        Card card2 = new Card(2,1);
        Card card3 = new Card(3,1);
        deck.add(card1);
        deck.add(card2);
        deck.add(card3);
        return deck;
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }

    public Card getCardByNumber(int i) {
        // deckのなかで数値がマッチしたものを返す
        return deck.get(i);
    }

    public void removeCardByNumber(int i) {
        deck.remove(i);
    }
}

