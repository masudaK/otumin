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


    public Lane getLane(int laneIndex) {
        return lanes.get(laneIndex);
    }

    public void addCard(int laneIndex, Card card){
        Lane lane = getLane(laneIndex);
        lane.addCard(card);
    }

    public List<Integer> collectLastIndexCard(){
        List<Integer> lastNumbers = new ArrayList<Integer>(4);

        for(int i = 0; i < Config.MAX_LANE_NUM; i++ ){
            List<Card> cards =  getLane(i).getCardsAll();
            //System.out.println("列にあるカードのサイズは" + cards.size() + "です。（実際はここからマイナス1します）");
            Card lastCard = cards.get(cards.size() - 1);
            //System.out.println(lastCard.getNumber());
            lastNumbers.add(lastCard.getNumber());
        }
        return lastNumbers;
    }

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
                distance.add(Integer.MAX_VALUE);
            }
        }

        if(canPlaced == false){
            System.out.println(Message.CANNOT_PLACE_CARD_WITH_SMALL_NUMBER);
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

    public List<Card> getAllLaneCards(int i){
        return getLane(i).getCardsAll();
    }

    public int getLaneWithMinimumMinusPoint(){
        List<Integer> minusOfLanes = new ArrayList<Integer>();
        minusOfLanes.add(lanes.get(0).calculateMinusPoint());
        minusOfLanes.add(lanes.get(1).calculateMinusPoint());
        minusOfLanes.add(lanes.get(2).calculateMinusPoint());
        minusOfLanes.add(lanes.get(3).calculateMinusPoint());

        return minusOfLanes.indexOf(Collections.min(minusOfLanes));
    }
}
