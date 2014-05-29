package otumin.com;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/05/10
 * Time: 20:12
 * To change this template use File | Settings | File Templates.
 */
public class User {

    private int id;
    private List<Card> hands;


    public User(int id){
        this.id = id;
        this.hands = new ArrayList<Card>(10);
    }

    public void receiveCard(Card card) {
        hands.add(card);
    }

    public void receiveCards(List<Card> cards) {
        // ConcurrentModificationExceptionになってしまう
        hands.addAll(cards);
        // 以下もConcurrentModificationExceptionになってしまう
        // TODO: size()の戻り値理解する
        //for(int i = 0; i < cards.size(); i++){
        //    hands.add(cards.remove(i));
        //}
        // 以下もConcurrentModificationExceptionになってしまう
        //Iterator<Card> iter = cards.iterator();
        //while (iter.hasNext()) {
        //    iter.remove();
        //}
        // 以下もConcurrentModificationExceptionになってしまう
        //Iterator<Card> iter = cards.iterator();
        //while (iter.hasNext()) {
        //    iter.remove();
        //}
        // 以下もConcurrentModificationExceptionになってしまう
        //Object[] keys = cards.toArray();
        //for(int i = 0; i < keys.length; i++){
        //    cards.remove(i);
        //}
        // 以下もConcurrentModificationExceptionになってしまう
        //int size = cards.size();
        //for(int i = 0; i < size; i++){
        //    hands.add(cards.remove(i));
        //}
        //    Iterator<Card> iter = cards.iterator();
        //    while (iter.hasNext()) {
        //        iter.remove();
        //    }
    }

    public List<Card> showHands(){
        return hands;
    }
}
