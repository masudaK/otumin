package otumin.com;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/06/05
 * Time: 0:11
 * To change this template use File | Settings | File Templates.
 */
public class Validation {

    public boolean isValidUsersNum(int num){
        if(1 < num && num <= 10){
            return true;
        }else{
            return false;
        }
    }


    //public void checkValidUsersNum(int i) {
    //
    //}
}
