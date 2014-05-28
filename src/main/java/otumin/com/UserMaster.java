package otumin.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/05/20
 * Time: 23:58
 * To change this template use File | Settings | File Templates.
 */
public class UserMaster {
    private List<User> users;

    public UserMaster(){
        this.users = new ArrayList<User>();
    }

    public User getUser(int index){
        return users.get(index);
    }

    public void createMultipleUser(int num){
        //playerNumの数だけループさせる
        for(int i = 0; i < num; i++){
            User user = new User(i);
            users.add(user);
        }
    }

    public Integer determineUsersNum(){
        System.out.print("参加ユーザーの数を入力してください: ");
        int n = 0;
        try{
            Terminal terminal = new Terminal();
            String input = terminal.input();
            n = Integer.valueOf(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("参加ユーザの数は「" + n + "」人です");
        return n;
    }

    public User getUserById(int i) {
        //IDの存在有無マッチしたら返却
        return users.get(0);
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

}
