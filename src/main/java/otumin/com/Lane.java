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

    public List<Card> removeCardsAll(){

        List<Card> removeCards = new ArrayList<Card>();
        int cardsSize = cards.size();
        //System.out.println("カードのサイズ:" + cardsSize + "。この分ループします");
        for(int i = 0; i < cardsSize; i++){
            System.out.println("捨て札に加わります:" + cards.get(0).getNumber());
            removeCards.add(cards.remove(0));
        }
        return removeCards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getSize(){
        return cards.size();

    }
}
