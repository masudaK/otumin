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

        // 各レーンに残りの札を配置する（ニムトではレーン1枚ずつ計4枚配置することになっている）
        field.addCard(0, deck.removeAndGetCardByFirst());
        field.addCard(1, deck.removeAndGetCardByFirst());
        field.addCard(2, deck.removeAndGetCardByFirst());
        field.addCard(3, deck.removeAndGetCardByFirst());

        // レーンにある現状のカードを出力
        print.allCardsInLane(field.getAllLaneCards(0));
        print.allCardsInLane(field.getAllLaneCards(1));
        print.allCardsInLane(field.getAllLaneCards(2));
        print.allCardsInLane(field.getAllLaneCards(3));

        // 各ターンを開始します。MAX_OF_TURNの数だけ繰り返します
        for(int i = 0; i < Config.MAX_OF_TURN; i++){
            startTurn(i);
        }


        // 試験的にユーザの捨て札を全て表示する
        for(int i = 0; i < Config.MAX_USERS_NUM; i++){
            print.usersDiscard(um.getDiscardsByUserIndex(i));
        }

    } // end of gameStart()

    private void startTurn(int turnCount){
        // keyはカードの数。そして、小さい順にソートされるようTreeMapを使う
        SortedMap<Integer, Card> submitUsersCardsAll = new TreeMap<Integer, Card>();


        // ユーザの数の分、順番をループさせる
        for(int userIndex = 0; userIndex< Config.MAX_USERS_NUM; userIndex++){

            System.out.println("================");
            System.out.println(userIndex + "番目のプレイヤーの順番になります");

            // どのカードを出すか判断(judge)します
            // NPCの場合はランダムでカードを選択します
            Card submitCard =  judgeSubmitCard(userIndex);
            submitUsersCardsAll.put(submitCard.getNumber(), submitCard);

            // 判断したら、そのカードを手札から取り除き(draw)ます
            um.getUser(userIndex).removeCardInHands(submitCard.getNumber());

        }

        //全ユーザが手札を出し終わったら、その手札の山を下に操作を行う
        for(Card submitted : submitUsersCardsAll.values()){

            System.out.print("\n");
            System.out.println("===========");
            System.out.println("ターンのカードナンバー:" + submitted.getNumber());
            System.out.println("ターンのカード所有者:" + submitted.getOwner());
            System.out.println("===========");

            // その取り除いたカードの数を元に、どの列のカードが近いかを調べます（最小距離の列インデックスを調べる
            // 近いカードがない場合は-1が返ります
            int laneIndexDistanceMinimum = calculateDistanceOfFieldCards(submitted);

            // どの列のカードよりも小さいカードを出したかどうかをチェックします
            boolean isSmallestCard = isSmallestCard(submitted);

           // 最も小さい数を出した場合は、最もマイナス値が少ない列を選択し、マイナスポイントを受け取ります
           // そして、その列にカードを置きます
           int laneIndexSelected = 0;
           if(isSmallestCard) {
               System.out.println("smallestFlagが経ちました");
               laneIndexSelected =  selectLaneOfGettingMinusPoint(submitted);

               // そして、列からカードをすべて受け取り、自分の捨て札に加えます
               System.out.println("以下の列が選択されました:" + laneIndexSelected);
               List<Card> cardReceived =  field.getLane(laneIndexSelected).removeCardsAll();
               System.out.println("カードの所有者:" + submitted.getOwner());
               um.getUser(submitted.getOwner()).receiveDiscardsAll(cardReceived);

               // そのマイナスポイントを受け取った列に対して、カードをsubmitします
               field.getLane(laneIndexSelected).addCard(submitted);

           // もし、レーンにおける最大数を超えていた場合は、その列にあるカードをすべて受け取ります
           // そして、その列にカードを置きます
           }else if(isOverOfLimitOfCapacity(laneIndexDistanceMinimum)){

               List<Card> cardReceived =  field.getLane(laneIndexDistanceMinimum).removeCardsAll();
               System.out.println("カードの所有者:" + submitted.getOwner());
               um.getUser(submitted.getOwner()).receiveDiscardsAll(cardReceived);

               field.getLane(laneIndexDistanceMinimum).addCard(submitted);

           // 上記2つの分岐にマッチしない場合は、マイナスポイントを受け取る必要がないので、
           // そのユーザのターンを追えます
           }else{
               field.getLane(laneIndexDistanceMinimum).addCard(submitted);
           }

            // レーンにある現状のカードを出力
            print.allCardsInLane(field.getAllLaneCards(0));
            print.allCardsInLane(field.getAllLaneCards(1));
            print.allCardsInLane(field.getAllLaneCards(2));
            print.allCardsInLane(field.getAllLaneCards(3));
        }



        // ターン終了
        turnCount++;

    }



    private void distributeCardAllUser(int usersNum){
        for(int i = 0; i < usersNum; i++){
            User user = um.getUser(i);
            user.receiveCards(deck.getCards(Config.DEFAULT_CARDS_NUM));
            setOwnerByUserIndex(i, um.getUser(i).showHands());
        }
    }

    private void setOwnerByUserIndex(int ownerIndex, Map<Integer,Card> userCards) {
        for(Card card : userCards.values()){
            card.setOwner(ownerIndex);
        }
    }

    private Card judgeSubmitCard(int userIndex){
        Card userCard;
        if(userIndex == Config.OWN_USER_INDEX){
            // ユーザの持ってるカードを出力 ※0番目を自分にしてる
            print.hands(um.getHandsByUserIndex(Config.OWN_USER_INDEX));

            // どのカードをどの列に出すか選択する
            System.out.print("\n" + Message.CHOICE_OWN_CARD_BY_HANDS);
            int submitNumber = tm.inputNumber();
            userCard = um.getUser(Config.OWN_USER_INDEX).getCardInHands(submitNumber);
        }else{
            userCard = um.getUser(userIndex).getCardRandomInHands();
        }
        return userCard;

    }

    // 列にあるカードとの距離を計測し、最小値の列インデックスを返します
    private int calculateDistanceOfFieldCards(Card userCard) {
        // その小さい順に並んだカードを、距離が最小距離の列に配置する
        List<Integer> lastNumbers = field.collectLastIndexCard();
        int minimumDistanceIndex =  field.getMinimumDistanceIndex(userCard.getNumber(), lastNumbers);
        System.out.println("最小距離の列は" + minimumDistanceIndex + "列目のレーンになります");
        return minimumDistanceIndex;
    }

    private boolean isOverOfLimitOfCapacity(int laneIndex) {
        boolean isOverOfLimitOfCapacity = false;
        System.out.println("debug isOverLimit" + field.getLane(laneIndex).getSize() + ":::" + Config.CAPACITY_OF_CARDS);
        // ユーザがカードを置く時点でキャパシティと同数あったら、その時点がフラグが立つ
        if(field.getLane(laneIndex).getSize() >= Config.CAPACITY_OF_CARDS){
            isOverOfLimitOfCapacity = true;
        }
        return isOverOfLimitOfCapacity;
    }

    private boolean isSmallestCard(Card submitted) {
        boolean isSmallestCard = false;
        List<Integer> lastNumbers = field.collectLastIndexCard();
        int smallestNumber =  Collections.min(lastNumbers);

        if(smallestNumber > submitted.getNumber()){
            isSmallestCard = true;
        }
        return isSmallestCard;
    }

    private int selectLaneOfGettingMinusPoint(Card card) {
        int laneIndex = 0;
        //if(userIndex == Config.OWN_USER_INDEX){
        if(card.getOwner() == Config.OWN_USER_INDEX){
            System.out.println("\n" + Message.CHOICE_LANE_OF_GETTIMG_MINUS_POINT);
            laneIndex = tm.inputNumber();
        }else{
            //NPCの場合
            laneIndex = field.getLaneWithMinimumMinusPoint();
        }
        System.out.println("選択されたレーンは以下になります:" + laneIndex);
        return laneIndex;

    }


}
