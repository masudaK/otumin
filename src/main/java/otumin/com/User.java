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
            // カード番号が決まったら、そのカードオブジェクトを返さないといけない
            // カード番号を入力してもらう
            // たとえば、4
            // そのユーザの手札をすべて把握し、そのカードオブジェクトのリストから数を全て洗い出す
            // その数のリストに存在しているか確認する

            if( ! isExistsNumberInHands(n)){
                int handsIndex = hands.indexOf(n);
                userCard = hands.get(handsIndex);

                for(Card card : hands){
                    if( card.getNumber() == n ){
                        userCard = card;
                        break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("提出するカードの数は「" + n + "」です");
        return userCard;
    }

    private Boolean isExistsNumberInHands(int number){
        for(Card card : hands){
            if( number == card.getNumber()){
                return true;
            }
        }
        System.out.println("マッチしませんでした");
        return false;
    }

}
