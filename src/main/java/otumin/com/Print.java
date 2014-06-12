package otumin.com;

import java.util.List;
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

    public static void discards(List<Card> userDiscards) {
        for(Card card : userDiscards){
            System.out.print(card.getNumber() + ",");
        }
        System.out.println("\n");
    }

    public void allCardsInLane(List<Card> laneCards){
        for(Card card : laneCards){
            System.out.print(card.getNumber() + ",");
        }
        System.out.println("\n");
    }

    public void usersHand(Map<Integer, Card> hand){
        System.out.println("#########################");
        hands(hand);
        System.out.println("------------------------");

    }

    public void usersDiscard(List<Card> discard) {
        System.out.println("#########################");
        discards(discard);
        System.out.println("------------------------");
    }
}
