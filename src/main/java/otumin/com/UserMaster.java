package otumin.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    private Config config;

    public UserMaster(){
        this.users = new ArrayList<User>();
        this.validation = new Validation();
        this.config = new Config();
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

            System.out.print(Message.INPUT_NUMBER_OF_USERS);
            int num = getNumFromInput();

            if(validation.isValidUsersNum(num)){
                return num;
            }
            retryCount.getAndIncrement();
        }
        return config.DEFAULT_NUM_OF_USERS;
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

    public Map<Integer, Card> getHandsByUserIndex(int userIndex) {
      System.out.println("ユーザ" + userIndex + "の手札");
      System.out.println("");
      return getUser(userIndex).showHands();
    }

    public List<Card> getDiscardsByUserIndex(int userIndex) {
        System.out.println("ユーザ" + userIndex + "の捨て札");
        System.out.println("");
        return getUser(userIndex).showDiscards();
    }

    public int  getUsersNum() {
        return users.size();
    }

}
