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

    // 結果Mapにする
    //private TreeMap<Integer, Card> hands;
    private HashMap<Integer, Card> hands;
    private ArrayList<Card> discards; //捨て札の山


    public User(int id){
        this.id = id;
        //this.hands = new TreeMap<Integer, Card>();
        this.hands = new HashMap();
        this.discards = new ArrayList<Card>();
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

    public void receiveDiscardsAll(List<Card> cards){
        discards.addAll(cards);
    }

    public Map<Integer, Card> showHands(){
        return hands;
    }

    public List<Card> showDiscards(){
        return discards;
    }

    // インデックスが見当たらない場合は、空のCardオブジェクトを返す
    public Card getCardInHands(int n){
        if(hands.containsKey(n)){
            return hands.get(n);
        }else{
            System.out.println(Message.CANNOT_FIND_INDEX);
            // ユーザに優しい例外処理を設定する
            return new Card(0, 0);
        }
    }

    // インデックスが見当たらない場合は、空のCardオブジェクトを返す
    public Card removeCardInHands(int key){
    //public Card removeCardInHands(Card submitCard){
        //System.out.println("対象key:" + key);
        if(hands.containsKey(key)){
            return hands.remove(key);
        }else{
            System.out.println(Message.CANNOT_FIND_INDEX);
            // ユーザに優しい例外処理を設定する
            return new Card(0, 0);
        }
    }

    public Card getCardRandomInHands() {
        Random random = new Random();
        List<Integer> keys = new ArrayList<Integer>(hands.keySet());
        int randomKey = keys.get( random.nextInt(keys.size()) );

        return hands.get(randomKey);
    }

    public Card removeCardRandomInHands() {
        Random random = new Random();
        List<Integer> keys = new ArrayList<Integer>(hands.keySet());
        int randomKey = keys.get( random.nextInt(keys.size()) );

        return hands.remove(randomKey);
    }
}
