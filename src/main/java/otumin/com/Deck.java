package otumin.com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/05/10
 * Time: 20:10
 * To change this template use File | Settings | File Templates.
 */
public class Deck {

    public List<Integer> deck;

    // コンストラクタ
    public Deck(){
        ArrayList<Integer> deck = new ArrayList<Integer>();
    }

    public List<Integer> create(){
        //List<Integer> deck = new ArrayList<Integer>(3){{
        //    add(1); add(2); add(3);
        //}};
        // ヌルポになってしまう。。。
        deck.add(1);
        deck.add(2);
        deck.add(3);
        return deck;
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }
}

