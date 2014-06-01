package otumin.com;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/05/30
 * Time: 1:30
 * To change this template use File | Settings | File Templates.
 */
public class Lane {

    private List<Card> cards;

    public Lane(){
        this.cards = new ArrayList<Card>();
    }

    public List<Card> getCardsAll(){
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

}
