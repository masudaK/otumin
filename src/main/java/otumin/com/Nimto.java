package otumin.com;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Nimto {
    public static void main(String[] args) {
        //ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        System.out.println("nimto Start!!");

		int number = getNumberOfPlayerFromInput();
		if (!Rule.isValidNumberOfPlayers(number)) {
			System.out.println("number is invalid");
			System.exit(1);
		}
		System.out.println("number of player : " + number );

        Deck deck =  new Deck();
//        deck.shuffle;

	}

	/**
	 * 標準入力からプレーヤーの人数(1-10)を取得します。<br />
	 * 入力値が不正の場合は3回までリトライさせます。<br />
	 * 3回リトライしても正しい値が入力されない場合は0を返します。
	 *
	 * @return プレーヤー人数（1-10）。リトライに3回失敗した場合は0を返します。
	 */
	private static int getNumberOfPlayerFromInput() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int number = 0;
		int retryCount = 0;
		while (retryCount < 3) {
			try {
				System.out.println("Input number of player (1-10)");
				number = Integer.parseInt(in.readLine());
				if (Rule.isValidNumberOfPlayers(number)) {
					break;
				}
			} catch (Exception e) {
				// ignore
			}
			System.out.println("Number of player should be in 1-10");
			retryCount++;
			number = 0; // 負の値が入力された場合の対策
		}
		return number;
	}
}
