package otumin.com;

import javax.swing.text.LayoutQueue;
import java.util.ArrayList;
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
        this.lanes = new ArrayList<Lane>(4);
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

}
