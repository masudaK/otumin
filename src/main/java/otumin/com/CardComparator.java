package otumin.com;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/06/04
 * Time: 23:45
 * To change this template use File | Settings | File Templates.
 */
public class CardComparator implements Comparator<Card> {
    public int compare(Card s, Card t){
        // 以下はJava1.4の書き方
        //return ((Card) s).getNumber() - ((Card) t).getNumber();
        return s.getNumber() - t.getNumber();
    }
}
