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
        terminal.determinePlayerNum();

        // ゲーム開始
        Deck deck =  new Deck();
        deck.create();
        deck.shuffle();

    }
}
