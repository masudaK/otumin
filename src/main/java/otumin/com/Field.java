package otumin.com;

import javax.swing.text.LayoutQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/05/10
 * Time: 20:13
 * To change this template use File | Settings | File Templates.
 */
public class Field {

    private List<Lane> lanes;

    public Field(){
        this.lanes = new ArrayList<Lane>(Config.MAX_LANE_NUM);
        lanes.add(new Lane());
        lanes.add(new Lane());
        lanes.add(new Lane());
        lanes.add(new Lane());
    }


    public Lane getLane(int lane_index) {
        return lanes.get(lane_index);
    }

    public void addCard(int lane_index, Card card){
        Lane lane = getLane(lane_index);
        lane.addCard(card);
    }

    public List<Integer> collectLastIndexCard(){
        List<Integer> lastNumbers = new ArrayList<Integer>(4);

        for(int i = 0; i < Config.MAX_LANE_NUM; i++ ){
            List<Card> cards =  getLane(i).getCardsAll();
            Card lastCard = cards.get(cards.size() - 1);
            System.out.println(lastCard.getNumber());
            lastNumbers.add(lastCard.getNumber());
        }
        return lastNumbers;
    }

    //TODO: もしこのメソッドですべてのカードとの距離が999のような場合は、
    // どの列かを選んでマイナスポイントを受け取らないといけない
    public int getMinimumDistanceIndex(int cardNumber, List<Integer> lists){
        ArrayList<Integer> distance = new ArrayList<Integer>(4);
        boolean canPlaced = false;

        for(int num : lists){
            int difference = cardNumber - num;
            //差分が正のものはそのままリストに入れ、負のものは距離999として設定する
            if(difference > 0){
                distance.add(difference);
                canPlaced = true;
            }else{
                distance.add(Config.MAX_DIFFERENCE);
            }
        }

        if(canPlaced == false){
            System.out.println(Message.CANNOT_PLACE_CARD_WITH_BIG_NUMBER);
            return -1;
        }

        return distance.indexOf(Collections.min(distance));
    }


    public void printAllLaneCards(int i) {
        System.out.println(i + "列目:");
        for(Card card : getLane(i).getCardsAll()){
            System.out.println(card.getNumber());
        }
    }
}
