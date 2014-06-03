package otumin.com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/05/19
 * Time: 0:37
 * To change this template use File | Settings | File Templates.
 */
public class GameMaster {

    private UserMaster um;
    private Deck deck;
    private Terminal tm;
    private int turnCount = 0;
    private Field field;


    public GameMaster(){
        this.um = new UserMaster();
        this.deck = new Deck();
        this.field = new Field();
        this.tm = new Terminal();
    }


    public void gameStart(){
        deck.create();
        deck.shuffle();

        int usersNum =  um.determineUsersNum();

        // ユーザ作成
        um.createMultipleUser(usersNum);

        // 作成した全ユーザにカード配布
        distributeCardAllUser(usersNum);

        // ここまででユーザには配り終わってる。
        // ので、その状態の山札の情報を表示する
        //System.out.println("----------------");
        //System.out.println("ユーザへの配布後の山札の状態");
        //System.out.println("");
        //List<Card> deckobj = deck.getDeck();
        //for(Card c : deckobj){
        //    System.out.print(c.getNumber() + ",");
        //}
        //System.out.println("");
        //System.out.println("----------------");


        //// 試験的にユーザの手札を全て表示する
        //for(int i = 0; i < usersNum; i++){
        //    System.out.println("#########################");
        //    um.showHandsByUserIndex(i);
        //}

        // 各レーンに残りの札を配置する（ニムトではレーン1枚ずつ計4枚配置することになっている）
        field.addCard(0, deck.removeAndGetCardByFirst());
        field.addCard(1, deck.removeAndGetCardByFirst());
        field.addCard(2, deck.removeAndGetCardByFirst());
        field.addCard(3, deck.removeAndGetCardByFirst());

        // ここまでで全ユーザと全列にカードの配置が終わってるので、デッキの中身を表示してみる
        //System.out.println("----------------");
        //System.out.println("最終的なデッキの中身:");
        //System.out.println("");
        //for(Card c : deckobj){
        //    System.out.print(c.getNumber() + ",");
        //}
        //System.out.println("");
        //System.out.println("----------------");



        startTurn(0); //0ターン目開始



    }

    private void distributeCardAllUser(int usersNum){
        for(int i = 0; i < usersNum; i++){
            User user = um.getUser(i);
            //TODO: 必ず10枚配るわけではないし、マジックナンバーは必ず外に出して扱いやすくする
            user.receiveCards(deck.getCards(10));
        }
    }

    private void startTurn(int turnCount){
        // レーンにある現状のカードを出力
        field.printAllLaneCards(0);
        field.printAllLaneCards(1);
        field.printAllLaneCards(2);
        field.printAllLaneCards(3);


        // ユーザの持ってるカードを出力 ※0番目を自分にしてる
        // TODO: 0番目を自分とし、それ以降を他のユーザにする
        um.printAllUserCards(0);

        // どのカードをどの列に出すか選択する
        Card userCard = um.getUser(0).fixSubmittedCardInTern();
        int laneNumber = askLaneNumberWithCard();
        field.addCard(laneNumber, userCard);

        // 他のユーザはランダムでカードを渡す
        for(int i = 1; i < 3; i++){
            Card npcCard = um.getUser(i).fixSubmittedCardRandomInTern();
            field.addCard(i, npcCard);
        }

        // 決定するか確認（実装はあとでもいいかも

        // 小さい順に列に渡す

        // 様々な判定

        // ターン終了
        turnCount++;

        // Debug
        field.printAllLaneCards(0);
        field.printAllLaneCards(1);
        field.printAllLaneCards(2);
        field.printAllLaneCards(3);
    }

    private int askLaneNumberWithCard() {
        //TODO: 0-3以外の数だったら例外を投げる
        System.out.print("どのレーンにカードを置くかレーン番号を選択してください: ");
        int n = 0;
        try{
            String input = tm.input();
            n = Integer.valueOf(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("カードを置くレーンは「" + n + "」番目のレーンです。");
        return n;
    }


}