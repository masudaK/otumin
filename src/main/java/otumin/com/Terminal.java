package otumin.com;

import java.io.BufferedReader;
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

    public Terminal(){
    }

    public String input() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        return br.readLine();
    }

    public int inputNumber() {
        int n = 0;
        try {
            n = Integer.valueOf(input());
        } catch (Exception e) {
            System.out.println("入力時にエラーが発生しました");
        }
        return n;
    }
}
