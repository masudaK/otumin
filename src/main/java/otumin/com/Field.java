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

    private List<Card> lane1;
    private List<Card> lane2;
    private List<Card> lane3;
    private List<Card> lane4;

    public Field(){
        this.lane1 = new ArrayList<Card>();
        this.lane2 = new ArrayList<Card>();
        this.lane3 = new ArrayList<Card>();
        this.lane4 = new ArrayList<Card>();
    }

    public void receiveCard(Card card) {
        lane1.add(card);
    }
}
