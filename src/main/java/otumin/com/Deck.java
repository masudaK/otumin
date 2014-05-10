package otumin.com;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/05/10
 * Time: 20:10
 * To change this template use File | Settings | File Templates.
 */
public class Deck {

    private List<Integer> deck;

    // コンストラクタ
    public Deck(){
        List<Integer> deck = new ArrayList<Integer>();
    }

    public static void shuffle(){
        //do something
        //Collections.shuffle();
    }
}

