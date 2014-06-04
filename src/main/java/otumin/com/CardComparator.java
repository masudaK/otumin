package otumin.com;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/06/04
 * Time: 23:45
 * To change this template use File | Settings | File Templates.
 */
public class CardComparator implements java.util.Comparator{
    public int compare(Object s, Object t) {
        //               + (x > y)
        // compare x y = 0 (x = y)
        //               - (x < y)
        return ((Card) s).getNumber() - ((Card) t).getNumber();
    }
}
