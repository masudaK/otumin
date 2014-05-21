package otumin.com;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/05/10
 * Time: 20:13
 * To change this template use File | Settings | File Templates.
 */
public class Field {

    private List<Card> lane;

    public Field(){
        this.lane = new ArrayList<Card>();
    }

    public void receiveCard(Card card) {
        lane.add(card);
    }
}
