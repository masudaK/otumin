package otumin.com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// このゲームの支配者的存在。
public class Nimto {

    private static List<User> usersList;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        System.out.println("nimto Start!!");

        // プレイヤーの人数をinputで受け付けて決定する
        Terminal terminal = new Terminal();
        Integer playerNum =  terminal.determinePlayerNum();
        System.out.println("プレイヤーの人数は「" + playerNum + "」人です");

        //ユーザ作成
        createMultipleUser(3);


        // ゲーム開始
        Deck deck =  new Deck();
        deck.create();
        deck.shuffle();

        System.out.println("デッキの中身:" + deck.getDeck());

    }


    private static void createMultipleUser(int num){
        //playerNumの数だけループさせる
        for(int i = 0; i < num; i++){
            //users.clear();
            User user = new User();
            usersList.add(user);
        }
    }

}
