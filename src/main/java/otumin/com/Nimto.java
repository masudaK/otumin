package otumin.com;


public class Nimto {

    public static void main(String[] args) {
        System.out.println(Config.START_MESSAGE);

        GameMaster gm = new GameMaster();
        gm.gameStart();


    }
}
