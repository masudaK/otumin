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


    public User(int id){
        this.id = id;
        this.hands = new ArrayList<Card>(10);
    }

    public void receiveCard(Card card) {
        hands.add(card);
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

    public Card fixSubmittedCardInTern() {
        //TODO: コマンドプロンプトでしか動かないし、色々やらせすぎてるので、分離
        // ex: ゲームマスターにやらせるとか
        // メソッド名がうーむという感じなので直す
        // ユーザがターンをする必要はないし
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

    public Card fixSubmittedCardRandomInTern() {
        //TODO: 今は先頭から取ってるけど、ランダムに実装を変える
        Card userCardRandom = hands.get(0);
        System.out.println(userCardRandom.getNumber());
        return userCardRandom;
    }
}
