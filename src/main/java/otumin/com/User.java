package otumin.com;

import java.util.ArrayList;
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
}
