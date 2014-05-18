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

    private List<User> users;

    public GameMaster(){
        this.users = new ArrayList<User>();
    }

    public void createMultipleUser(int num){
        //playerNumの数だけループさせる
        for(int i = 0; i < num; i++){
            User user = new User(i);
            users.add(user);
        }
    }

    public User getUser(int idx){
        return users.get(idx + 1);
    }

}
