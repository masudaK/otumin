package otumin.com;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/05/10
 * Time: 20:12
 * To change this template use File | Settings | File Templates.
 */
public class User {

    private int id;
    private List<Integer> hands;


    public User(int id){
        //デフォルト1
        this.id = id;
    }

    private int getId(int id){
        return id;
    }

    private void setId(int id){
        this.id = id;
    }

    public void recieveCard(int cardNumber){
        //todo
    }


}
