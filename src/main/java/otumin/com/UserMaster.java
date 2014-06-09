package otumin.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/05/20
 * Time: 23:58
 * To change this template use File | Settings | File Templates.
 */
public class UserMaster {
    private List<User> users;
    private Validation validation;

    public UserMaster(){
        this.users = new ArrayList<User>();
        this.validation = new Validation();
    }

    public User getUser(int index){
        return users.get(index);
    }



    public void createMultipleUser(){
        int usersNum = determineUsersNum();

        //playerNumの数だけループさせる
        for(int i = 0; i < usersNum; i++){
            User user = new User(i);
            users.add(user);
        }
    }

    private int determineUsersNum(){
        AtomicInteger retryCount = new AtomicInteger(0);
        while(retryCount.get() <3){

            System.out.print("参加ユーザーの数を入力してください: ");
            int num = getNumFromInput();

            if(validation.isValidUsersNum(num)){
                return num;
            }
            retryCount.getAndIncrement();
        }
        //TODO: default値にする
        return 0;
    }

    private int getNumFromInput(){
        try{
            Terminal terminal = new Terminal();
            String input = terminal.input();
            return Integer.valueOf(input);
        } catch (Exception e) {
            // TODO: 理由をちゃんと出力する
            //e.printStackTrace();
            return -1;
        }
    }

    public void showHandsByUserIndex(int index) {
        System.out.println("ユーザ" + index + "の手札");
        System.out.println("");
        User user = getUser(index);
        System.out.println("所有数:" + user.showHands().size() + "枚");
        for(Card c : user.showHands()){
            System.out.print(c.getNumber() + ",");
        }
        System.out.println("");
    }

    public void printAllUserCards(int i) {
        if(i == 0){
            System.out.println("自分の手札:");
        }else{
            System.out.println(i + "番目のユーザの手札:");
        }
        for(Card card : getUser(i).showHands()){
            System.out.print(card.getNumber() + ",");
        }
    }

    public int  getUsersNum() {
        return users.size();
    }
}