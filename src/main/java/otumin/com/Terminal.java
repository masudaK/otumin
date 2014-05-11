package otumin.com;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/05/11
 * Time: 14:52
 * To change this template use File | Settings | File Templates.
 */
public class Terminal {
    public String input;


    public String inputPlayerNum() throws IOException {
        // ヌルポになってしまう。
        //Console console = System.console();
        //if (console == null) {
        //    System.out.println("Unable to fetch console");
        //}
        //System.out.print("プレイヤーの人数を入力してください");
        //String input = console.readLine();
        //return input;

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String input = br.readLine();
        return input;
    }

    public Integer determinePlayerNum(){
        System.out.print("プレイヤーの人数を入力してください: ");

        try{
          //入力がStringになってるので、Intergerにキャスト
          Integer n = Integer.valueOf(inputPlayerNum());
          return n;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
