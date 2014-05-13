package otumin.com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// このゲームの支配者的存在。
public class Nimto {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        System.out.println("nimto Start!!");

        // プレイヤーの人数をinputで受け付けて決定する
        Terminal terminal = new Terminal();
        Integer playerNum =  terminal.determinePlayerNum();
        System.out.println("プレイヤーの人数は「" + playerNum + "」人です");

        //playerNumの数だけループさせる
        // 各ユーザオブジェクトは変数がいいのか、もっといい形があるかはわからぬm(__)m
        User user1 = new User();

        // ゲーム開始
        Deck deck =  new Deck();
        deck.create();
        deck.shuffle();

        System.out.println("デッキの中身:" + deck.getDeck());

    }
}
