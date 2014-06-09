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
        if(Config.MIN_USERS_NUM <= num && num <= Config.MAX_USERS_NUM){
            return true;
        }else{
            return false;
        }
    }


    //public void checkValidUsersNum(int i) {
    //
    //}
}
