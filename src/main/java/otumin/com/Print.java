package otumin.com;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/06/11
 * Time: 0:06
 * To change this template use File | Settings | File Templates.
 */
public class Print {

    private UserMaster um;

    public Print(){
        this.um = new UserMaster();
    }


    public static void hands(Map<Integer, Card> userHands) {
        for(Card card : userHands.values()){
            System.out.print(card.getNumber() + ",");
        }
        System.out.println("\n");
    }
}
