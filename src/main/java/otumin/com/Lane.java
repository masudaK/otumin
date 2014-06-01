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

    public void addCard(Card card) {
        cards.add(card);
    }

    public int fixLaneWithCard() {
        System.out.print("カードを配置するレーンを選択してください: ");
        int n = 0;
        try{
            Terminal terminal = new Terminal();
            String input = terminal.input();
            n = Integer.valueOf(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("カードを配置するレーンは「" + n + "」番目のレーンです");
        return n;
    }
}
