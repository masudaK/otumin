package otumin.com;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/05/10
 * Time: 20:10
 * To change this template use File | Settings | File Templates.
 */
public class Deck {

    private List<Card> deck;

    private Map<Integer, Integer> map;

    // コンストラクタ
    public Deck(){
        this.deck = new ArrayList<Card>(104);
        map = new HashMap<Integer, Integer>();
        for(int i = 1; i <= 104; i++){
            map.put(i,1);
        }
    }

    public void create(){
        for(int i = 1; i <= 104; i++){
            Card card = new Card(i, map.get(i));
            deck.add(card);
        }
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }

    public Card removeAndGetCardByFirst() {
        return deck.remove(0);
    }

    public List<Card> getCards(int toLast){
        List<Card> distributeCards = new ArrayList<Card>(toLast);
        distributeCards.addAll(deck.subList(0, toLast));
        // 以下だと参照を持ち続けてしまう
        //List<Card> distributeCards = deck.subList(0, toLast);
        //for(Card card: distributeCards){
        //    System.out.println("debug:" + card.getNumber());
        //}
        deck.subList(0, toLast).clear();
        return distributeCards;
    }
}

