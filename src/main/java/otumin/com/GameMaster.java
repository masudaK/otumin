package otumin.com;

import java.util.ArrayList;
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


    public GameMaster(){
        this.um = new UserMaster();
        this.deck = new Deck();
    }

    public void startGame(){
        // プレイヤーの人数をinputで受け付けて決定する
        int usersNum =  um.determineUsersNum();
        System.out.println("参加ユーザの数は「" + usersNum + "」人です");

        um.createMultipleUser(usersNum);
    }

    public void distributeCard(){
        deck.create();
        deck.shuffle();

        System.out.println("デッキの中身:" + deck.getDeck());

        //配布Before
        //int distributeNumber = deck.get(0);
        //user.hands.add(distributeNumber);
        //deck.remove(0)

        //配布After
        //User user = um.getUserById(0);
        //Card card = deck.getCardByIndex(0);
        //user.receiveCard(card);
        //deck.popCardByIndex(0);

        // Field field = new Field();
        // field.receiveCard(card); //Laneオブジェクトに置かれる
        //deck.popCardByIndex(0);
    }



}
