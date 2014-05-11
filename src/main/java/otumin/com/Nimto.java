package otumin.com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

// このゲームの支配者的存在。
public class Nimto {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        System.out.println("nimto Start!!");

        Deck deck =  new Deck();
        deck.create;
        deck.shuffle;

    }
}
