package otumin.com;

import java.util.ArrayList;
import java.util.Iterator;
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
    private List<Card> hands;
    private Lane lane;


    public User(int id){
        this.id = id;
        this.hands = new ArrayList<Card>(10);
        this.lane = new Lane();
    }

    public void receiveCard(Card card) {
        hands.add(card);
    }

    public void receiveCards(List<Card> cards) {
        // ConcurrentModificationExceptionになってしまう
        hands.addAll(cards);
        // 以下もConcurrentModificationExceptionになってしまう
        // TODO: size()の戻り値理解する
        //for(int i = 0; i < cards.size(); i++){
        //    hands.add(cards.remove(i));
        //}
        // 以下もConcurrentModificationExceptionになってしまう
        //Iterator<Card> iter = cards.iterator();
        //while (iter.hasNext()) {
        //    iter.remove();
        //}
        // 以下もConcurrentModificationExceptionになってしまう
        //Iterator<Card> iter = cards.iterator();
        //while (iter.hasNext()) {
        //    iter.remove();
        //}
        // 以下もConcurrentModificationExceptionになってしまう
        //Object[] keys = cards.toArray();
        //for(int i = 0; i < keys.length; i++){
        //    cards.remove(i);
        //}
        // 以下もConcurrentModificationExceptionになってしまう
        //int size = cards.size();
        //for(int i = 0; i < size; i++){
        //    hands.add(cards.remove(i));
        //}
        //    Iterator<Card> iter = cards.iterator();
        //    while (iter.hasNext()) {
        //        iter.remove();
        //    }
    }

    public List<Card> showHands(){
        return hands;
    }

    public Card fixSubmittedCardInTern() {
        System.out.print("提示するカードを自分の手札から選択してください: ");
        int n = 0;
        Card userCard = null;
        try{
            Terminal terminal = new Terminal();
            String input = terminal.input();
            n = Integer.valueOf(input);

            // 手札に該当の値が存在していれば、そのあたいをnumberとして持ったカードオブジェクトを返す
            if( isExistsNumberInHands(n) ){
                for(Card card : hands){
                    //System.out.println(card.getNumber() + "を探索中です");
                    if( card.getNumber() == n ){
                        userCard = card;
                        break;
                    }
                }
            }else{
                System.out.println("インデックスが見つけられません");
                System.exit(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("提出するカードの数は「" + n + "」です");
        return userCard;
    }

    private Boolean isExistsNumberInHands(int number){
        Boolean matchFlg = false;
        for(Card card : hands){
            if( number == card.getNumber()){
                System.out.println("手札にありました:" + number);
                matchFlg = true;
                break;
            }
        }
        return matchFlg;
    }

}
