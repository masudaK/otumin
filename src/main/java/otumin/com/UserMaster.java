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


    public Integer determineUsersNum(){
        System.out.print("参加ユーザーの数を入力してください: ");
        int n = 0;
        AtomicInteger retryCount = new AtomicInteger(0);
        while(retryCount.get() <3){
            //validation
            try{
                Terminal terminal = new Terminal();
                String input = terminal.input();
                n = Integer.valueOf(input);

                if(validation.isValidUsersNum(n)){
                    createMultipleUser(n);
                    System.out.println("参加ユーザの数は「" + n + "」人です");
                    return n;
                }else{
                    System.out.println("参加者の条件を満たしていません。再度入力してください。");
                    retryCount.getAndIncrement();
                    continue;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("条件を満たしていませんでした。ゲームを終了します。");
        System.exit(1);
        //TODO: default値にする
        return 0;
    }

    private void createMultipleUser(int num){
        //playerNumの数だけループさせる
        for(int i = 0; i < num; i++){
            User user = new User(i);
            users.add(user);
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
}
