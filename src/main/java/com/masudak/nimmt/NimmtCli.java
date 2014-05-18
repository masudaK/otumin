package com.masudak.nimmt;


import com.masudak.nimmt.core.*;
import com.masudak.nimmt.core.Field;
import com.masudak.nimmt.core.Line;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;


public class NimmtCli {

	private Prompt prompt = new Prompt();

	public static void main(String[] args) {
		System.out.println("nimto Start!!");
		new NimmtCli().play();
	}

	public void play() {

		int number = prompt.getNumberOfPlayerFromInput();
		if (!Rule.isValidNumberOfPlayers(number)) {
			System.out.println("number is invalid");
			System.exit(1);
		}
		System.out.println("number of player : " + number );

		GameMaster gm = new GameMaster(number);

		// 出力
		User player = gm.getUser(1);
		prompt.showHands(player);
		prompt.showField(gm.getField());

		while (player.handsSize() > 0) {

			int index = prompt.getIndex(player);
			SortedMap<Card, User> open = gm.openHand(player.getId(), index);

			// 1ターン
			for (Map.Entry<Card, User> entry : open.entrySet()) {
				Card card = entry.getKey();
				User user = entry.getValue();
				System.out.println(card.getNumber() + "の処理を行います。カードを出したユーザは" + user.getId() + "です。");
				if (card.getNumber() < gm.getMinimum()) {
					// どこに置くか決めてもらってLineを更新してユーザのマイナスポイントを追加する
					int line = -1;
					if (user.isNpc()) {
						// ランダムにおく
						line = new Random().nextInt(4);
					} else {
						// ユーザに選択させる
						prompt.showField(gm.getField());
//						prompt.showHands(user);
						line = prompt.getFieldIndex();
					}
					// マイナスポイントとLineのカード情報を更新する
					gm.updateFieldAndUser(line, user.getId());
					gm.putField(line, card);

				} else {
					int line = gm.getLineToAddLast(card.getNumber());
					if (gm.isLineFull(line)) {
						// マイナスポイントとLineのカード情報を更新する
						gm.updateFieldAndUser(line, user.getId());
						gm.putField(line, card);
					} else {
						// 後方に追加
						gm.putField(line, card);
					}
				}
			}

			System.out.println("1巡が終わった状態");
			prompt.showField(gm.getField());
			prompt.showHands(player);
		}

		System.out.println("全てのターンが終わった");
		prompt.showScore(gm.showScore());




		//gm.judge(player.getId(), index); // 結果をもらえる




	}

	class Prompt {

		private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		void showHands(User player) {
			System.out.println("id : " + player.getId() );
			System.out.println("minus : " + player.getCow() );
			System.out.println("your hands : ");
			System.out.println("index, card number, minus");
			List<Card> hands = player.listHands();
			for (int i = 0; i < hands.size(); i++) {
				Card card = hands.get(i);
				System.out.println(i + ", " + card.getNumber() + "(" + card.getCow() + ")");
			}
		}

		/**
		 * 標準入力からプレーヤーの人数(1-10)を取得します。<br />
		 * 入力値が不正の場合は3回までリトライさせます。<br />
		 * 3回リトライしても正しい値が入力されない場合は0を返します。
		 *
		 * @return プレーヤー人数（1-10）。リトライに3回失敗した場合は0を返します。
		 */
		int getNumberOfPlayerFromInput() {
//			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
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
			//close(in);
			return number;
		}

		/**
		 * プレーヤーの手札のうち、どれを出すか、カードのインデックス番号を取得します。
		 *
		 * @param player
		 * @return
		 */
		int getIndex(User player) {
//			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("choose your card. [index]");
			int index = -1;
			while(true) {
				try {
//					System.out.println("test");
					index = Integer.parseInt(in.readLine());
					if (0 <= index && index < player.handsSize()) {
						break;
					}
				} catch (Exception e) {
					// ignore
				}
				System.out.println("Input should be in 0-" + player.handsSize() + ". Choose again");
			}
			//close(in);
			System.out.println("You have choose [index]:" + index);
			return index;
		}

		int getFieldIndex() {
//			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("choose field. [index]");
			int index = -1;
			while(true) {
				try {
//					System.out.println("test");
					index = Integer.parseInt(in.readLine());
					if (0 <= index && index < 4) {
						break;
					}
				} catch (Exception e) {
					// ignore
				}
				System.out.println("Input should be in 0-3. Choose again");
			}
			//close(in);
			System.out.println("You have choose [index]:" + index);
			return index;
		}

		void closeStream() {
			try {
				in.close();
			} catch (IOException e) {
				// ignore
			}
		}

		public void showField(Field field) {
			System.out.println("↓Cards on field now.↓");
			int i = 0;
			for (Line line : field.getLines()) {
				System.out.print(i + ": ");
				for (Card card : line.getCards()) {
					System.out.print(card.getNumber() + "(" + card.getCow() + ")  ");
				}
				System.out.println();
				i++;
			}
		}

		void showScore(List<Integer> scores) {
			int i = 1;
			for (int score : scores) {
				System.out.println(i + ": " + score);
				i++;
			}
		}
	}
}
