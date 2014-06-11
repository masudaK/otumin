package otumin.com;

import java.util.*;

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
    private Print print;


    public GameMaster(){
        this.um = new UserMaster();
        this.deck = new Deck();
        this.field = new Field();
        this.tm = new Terminal();
        this.print = new Print();
    }


    public void gameStart(){
        deck.create();
        deck.shuffle();

        // ユーザ作成
        um.createMultipleUser();

        // 作成した全ユーザにカード配布
        int usersNum =  um.getUsersNum();
        distributeCardAllUser(usersNum);

        // 試験的にユーザの手札を全て表示する
        for(int i = 0; i < usersNum; i++){
            System.out.println("#########################");
            Map<Integer, Card> userHands = um.getHandsByUserIndex(i);
            Print.hands(userHands);
        }

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
            user.receiveCards(deck.getCards(Config.DEFAULT_CARDS_NUM));
        }
    }

    private void startTurn(int turnCount){
        // keyはカードの数。そして、小さい順にソートされるようTreeMapを使う
        TreeMap<Integer, Card> submitUsersCardsAll = new TreeMap<Integer, Card>();

        // レーンにある現状のカードを出力
        field.printAllLaneCards(0);
        field.printAllLaneCards(1);
        field.printAllLaneCards(2);
        field.printAllLaneCards(3);


        // ユーザの持ってるカードを出力 ※0番目を自分にしてる
        // TODO: 0番目を自分とし、それ以降を他のユーザにする
        Print.hands(um.getHandsByUserIndex(Config.OWN_USER_INDEX));


        // どのカードをどの列に出すか選択する
        System.out.print("\n" + Message.CHOICE_OWN_CARD_BY_HANDS);
        int submitNumber = tm.inputNumber();
        Card userCard = um.getUser(Config.OWN_USER_INDEX).findCardInHands(submitNumber);
        submitUsersCardsAll.put(userCard.getNumber(), userCard);
        //int laneNumber = askLaneNumberWithCard();

        // 他のユーザはランダムでカードを渡す
        for(int i = 1; i < 3; i++){
            Card npcCard = um.getUser(i).getCardRandom();
            System.out.println("test:" + npcCard.getNumber());
            //submitUsersCardsAll.add(npcCard);
            submitUsersCardsAll.put(npcCard.getNumber(), npcCard);
        }

        // 小さい順に並べる
        //Collections.sort(submitUsersCardsAll, new CardComparator());
        // その小さい順に並んだカードを、距離が最小距離の列に配置する
        List<Integer> lastNumbers = field.collectLastIndexCard();
        int minimumDistanceIndex =  field.getMinimumDistanceIndex(userCard.getNumber(), lastNumbers);
        System.out.println("最小距離の列は" + minimumDistanceIndex + "列目のレーンになります");

        // 置ける場所がなかったら、マイナスポイントを受け取る処理に入る
        if(minimumDistanceIndex == -1){
            System.out.println("\n" + Message.CHOICE_LANE_OF_GETTIMG_MINUS_POINT);
            int laneIndex = tm.inputNumber();
            um.getUser(Config.OWN_USER_INDEX).receiveAllCardsByLane(laneIndex);
        }else{
            //置ける場所が決まったので、カード配置

        }


        // 様々な判定


        // ターン終了
        turnCount++;

        // Debug
        field.printAllLaneCards(0);
        field.printAllLaneCards(1);
        field.printAllLaneCards(2);
        field.printAllLaneCards(3);
    }

}
