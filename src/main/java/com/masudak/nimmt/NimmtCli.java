package com.masudak.nimmt;


import com.masudak.nimmt.core.Card;
import com.masudak.nimmt.core.Field;
import com.masudak.nimmt.core.GameMaster;
import com.masudak.nimmt.core.Line;
import com.masudak.nimmt.core.Rule;
import com.masudak.nimmt.core.Player;
import com.masudak.nimmt.core.GameException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

/**
 * Nimmtをコマンドラインで遊ぶためのユーザインタフェースです
 *
 * @author tksmaru
 */
public class NimmtCli {

	private Prompt prompt = new Prompt();

	public static void main(String[] args) {
		System.out.println("nimmt Start!!");
		try {
			new NimmtCli().play();
		} catch (GameException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

	public void play() throws GameException {

		int number = prompt.getNumberOfPlayer();
		GameMaster gameMaster = new GameMaster(number);

		// 出力
		Player player = gameMaster.getPlayer(1);
		prompt.showHands(player);
		prompt.showField(gameMaster.getField());

		while (player.hasHand()) {

			int index = prompt.getIndexOfHands(player);
			SortedMap<Card, Player> open = gameMaster.openHand(player.getId(), index);

			// 1ターン
			for (Map.Entry<Card, Player> entry : open.entrySet()) {
				Card card = entry.getKey();
				Player user = entry.getValue();
				System.out.println(card.getNumber() + "の処理を行います。カードを出したユーザは" + user.getId() + "です。");
				int line;
				if (card.getNumber() < gameMaster.getMinimum()) {
					line = getLineByPlayerChoice(user, gameMaster.getField());
					gameMaster.putCardAndAddCow(line, card, user.getId());
				} else {
					line = gameMaster.getLineToAddLast(card.getNumber());
					gameMaster.putCardAndUpdate(line, card, user.getId());
				}
			}

			System.out.println("1巡が終わった状態");
			prompt.showField(gameMaster.getField());
			prompt.showHands(player);
		}

		System.out.println("全てのターンが終わった");
		prompt.showScore(gameMaster.showScore());
		prompt.closeStream();
	}

	/**
	 * プレーヤーの選択でカードを置く列の列番号を取得します。
	 *
	 * @param user プレーヤー
	 * @param field 場
	 * @return 列番号
	 */
	private int getLineByPlayerChoice(Player user, Field field) {
		if (user.isNpc()) {
			// NPCは牛の合計数が最も小さい列を選択する
			return field.getLineWithMinCows();
		} else {
			// ユーザに選択させる
			prompt.showField(field);
			return prompt.getIndexOfLine();
		}
	}

	/**
	 * コマンドプロンプト
	 */
	class Prompt {

		private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		/**
		 * 標準入力からプレーヤーの人数(2-10)を取得します。<br />
		 * 入力値が不正の場合は3回までリトライさせます。<br />
		 * 3回リトライしても正しい値が入力されない場合は{@link GameException}をスローします。
		 *
		 * @return プレーヤー人数（2-10）。
		 * @throws GameException リトライに3回失敗した場合
		 *
		 */
		int getNumberOfPlayer() throws GameException {
			int retryCount = 0;
			while (retryCount < 3) {
				System.out.println("Input number of player (2-10)");
				int number = getNumberFromInput();
				if (Rule.isValidNumberOfPlayers(number)) {
					return number;
				}
				System.out.println("Number of player should be in 2-10");
				retryCount++;
			}
			throw new GameException("プレーヤーの人数が正しく入力されませんでした。");
		}

		/**
		 * @return 標準入力から得られた数字。数字以外を入力した場合は-1(無効な数)を返す。
		 */
		private int getNumberFromInput() {
			try {
				return Integer.parseInt(in.readLine());
			} catch (Exception e) {
				return -1;
			}
		}

		/**
		 * プレーヤーの手札のうち、どれを出すか、カードのインデックス番号を取得します。
		 *
		 * @param player プレーヤー
		 * @return ユーザの手札のインデックス番号（0-{その時点のユーザの手札の枚数-1}）
		 */
		int getIndexOfHands(Player player) {
			System.out.println("choose your card. [index]");
			while(true) {
				int index = getNumberFromInput();
				if (0 <= index && index < player.handsSize()) {
					System.out.println("You have choose [index]:" + index);
					return index;
				}
				System.out.println("Input should be in 0-" + (player.handsSize() - 1) + ". Choose again");
			}
		}

		/**
		 * ユーザが手札を置く列を取得します。(0-3を想定）<br />
		 * 正しい値が入力されるまでプロンプトは繰り返し表示されます。
		 *
		 * @return 列のインデックス番号（0-3を想定）
		 */
		int getIndexOfLine() {
			System.out.println("choose line. [index]");
			while(true) {
				int index = getNumberFromInput();
				if (0 <= index && index < Rule.FIELD_SIZE) {
					System.out.println("You have choose [index]:" + index);
					return index;
				}
				System.out.println("Input should be in 0-3. Choose again");
			}
		}

		void closeStream() {
			try {
				in.close();
			} catch (IOException e) {
				// ignore
			}
		}

		void showHands(Player player) {
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

		void showField(Field field) {
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
