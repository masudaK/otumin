package otumin.com;

import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/05/10
 * Time: 20:12
 * To change this template use File | Settings | File Templates.
 */
public class User {

    private int id;
    // 手札はCard.getNumberで値を見た上での処理をすることが多い。
    // そのため、NumberをkeyとしたMapを使うことにする
    // また、先頭を取得するなどをしやすくするため、TreeMapを使う
    // ただ、この状態だと必ず一番小さい数から出してしまうので、ランダムで出すように修正しないといけない
    private TreeMap<Integer, Card> hands;
    private LinkedList<Card> discards; //捨て札の山


    public User(int id){
        this.id = id;
        this.hands = new TreeMap<Integer, Card>();
        this.discards = new LinkedList<Card>();
    }

    public void receiveCard(Card card) {
        hands.put(card.getNumber(), card);
    }

    public void receiveCards(List<Card> cards) {
        //keyをセットしないといけないので、以下はできない
        //hands.addAll(cards);
        // そのため、以下のように一つ一つputする
        for(Card card : cards){
            hands.put(card.getNumber(), card);
        }
    }

    public void receiveDiscard(Card card){
        discards.add(card);
    }

    public void receiveDiscardsAll(LinkedList<Card> cards){
        discards.addAll(cards);
    }

    public Map<Integer, Card> showHands(){
        return hands;
    }

    public List<Card> showDiscards(){
        return discards;
    }

    // インデックスが見当たらない場合は、空のCardオブジェクトを返す
    public Card findCardInHands(int n){
        if(hands.containsKey(n)){
            return hands.get(n);
        }else{
            System.out.println(Message.CANNOT_FIND_INDEX);
            // ユーザに優しい例外処理を設定する
            return new Card(0, 0);
        }
    }

    public Card getCardRandom() {
        // TODO: 今は先頭から取ってるけど、ランダムに実装を変える
        // get(0)は0の数字を持ったカードなので、ヌルポになってしまう。
        // なので、先頭を取得する必要がある。
        return hands.get(hands.firstKey());
    }

    public void receiveAllCardsByLane(int laneIndex){
        Field field = new Field();
        List<Card> cardsOfLane = field.getLane(laneIndex).getCardsAll();
        receiveCards(cardsOfLane);
    }
}
