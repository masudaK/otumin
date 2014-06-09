package otumin.com;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/05/10
 * Time: 20:12
 * To change this template use File | Settings | File Templates.
 */
public class User {

    private int id;
    private List<Card> hands; //手札の山
    private LinkedList<Card> discards; //捨て札の山


    public User(int id){
        this.id = id;
        this.hands = new ArrayList<Card>(Config.DEFAULT_CARDS_NUM);
        this.discards = new LinkedList<Card>();
    }

    public void receiveCard(Card card) {
        hands.add(card);
    }

    public void receiveCardsAll(List<Card> cards) {
        hands.addAll(cards);
    }

    public void receiveDiscard(Card card){
        discards.add(card);
    }

    public void receiveDiscardsAll(LinkedList<Card> cards){
        discards.addAll(cards);
    }

    public void receiveCards(List<Card> cards) {
        hands.addAll(cards);
        // 以下もConcurrentModificationExceptionになってしまう
        // TODO: size()の戻り値理解する
        //for(int i = 0; i < cards.size(); i++){
        //    hands.add(cards.remove(i));
        //}
    }

    public List<Card> showHands(){
        return hands;
    }

    public List<Card> showDiscards(){
        return discards;
    }

    public Card findCardInHands(int n){
        Card userCard = null;
        // 手札に該当の値が存在していれば、そのあたいをnumberとして持ったカードオブジェクトを返す
        if( isExistsNumberInHands(n) ){
            for(Card card : hands){
                if( card.getNumber() == n ){
                    userCard = card;
                    break;
                }
            }
        }else{
            System.out.println("インデックスが見つけられません");
            System.exit(1);
        }

        System.out.println("提出するカードの数は「" + n + "」です");
        return userCard;
    }

    private boolean isExistsNumberInHands(int number){
        boolean matchFlg = false;
        for(Card card : hands){
            if( number == card.getNumber()){
                System.out.println("手札にありました:" + number);
                matchFlg = true;
                break;
            }
        }
        return matchFlg;
    }

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
