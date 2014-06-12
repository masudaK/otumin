package otumin.com;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/05/21
 * Time: 0:14
 * To change this template use File | Settings | File Templates.
 */
public class Card {

    private int number;
    private int minusPoint;
    private int owner;

    public Card(int number, int minusPoint){
        this.number = number;
        this.minusPoint = minusPoint;
    }

    public int getNumber(){
        return number;
    }

    public int getMinusPoint(){
        return minusPoint;
    }

    public int getOwner(){
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
}
