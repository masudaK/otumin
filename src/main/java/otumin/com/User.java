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
    // 手札はCard.getNumberで値を見た上での処理をすることが多い。そのため、NumberをkeyとしたMapを使うことにする
    private Map<Integer, Card> hands;
    //private List<Card> hands;
    private LinkedList<Card> discards; //捨て札の山


    public User(int id){
        this.id = id;
        //this.hands = new ArrayList<Card>(Config.DEFAULT_CARDS_NUM);
        this.hands = new HashMap<Integer, Card>(Config.DEFAULT_CARDS_NUM);
        this.discards = new LinkedList<Card>();
    }

    public void receiveCard(Card card) {
        //hands.add(card);
        hands.put(card.getNumber(), card);
    }

    //public void receiveCardsAll(List<Card> cards) {
    //    hands.addAll(cards);
    //}

    public void receiveDiscard(Card card){
        discards.add(card);
    }

    public void receiveDiscardsAll(LinkedList<Card> cards){
        discards.addAll(cards);
    }

    public void receiveCards(List<Card> cards) {
        //keyをセットしないといけないので、以下はできない
        //hands.addAll(cards);
        // そのため、以下のように一つ一つputする
        for(Card card : cards){
            //System.out.println("key:" + card.getNumber());
            hands.put(card.getNumber(), card);
        }
    }

    //public List<Card> showHands(){
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

    //public Card findCardInHands(int n){
    //    Card userCard = null;
    //    // 手札に該当の値が存在していれば、そのあたいをnumberとして持ったカードオブジェクトを返す
    //    if( isExistsNumberInHands(n) ){
    //        for(Card card : hands){
    //            if( card.getNumber() == n ){
    //                userCard = card;
    //                break;
    //            }
    //        }
    //    }else{
    //        System.out.println(Message.CANNOT_FIND_INDEX);
    //        //TODO: 強制終了させないで、繰り返し入力させる
    //        System.exit(1);
    //    }

    //    System.out.println("提出するカードの数は「" + n + "」です");
    //    return userCard;
    //}

    //private boolean isExistsNumberInHands(int number){
    //    boolean matchFlg = false;
    //    for(Card card : hands){
    //        if( number == card.getNumber()){
    //            System.out.println("手札にありました:" + number);
    //            matchFlg = true;
    //            break;
    //        }
    //    }
    //    return matchFlg;
    //}

    public Card getCardRandom() {
        //TODO: 今は先頭から取ってるけど、ランダムに実装を変える
        Card userCardRandom = hands.get(0);
        return userCardRandom;
    }

    public void receiveAllCardsByLane(int laneIndex){
        Field field = new Field();
        List<Card> cardsOfLane = field.getLane(laneIndex).getCardsAll();
        receiveCards(cardsOfLane);
    }
}
