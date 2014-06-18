package com.masudak.nimmt;


import com.masudak.nimmt.core.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

		final int numberOfPlayer = prompt.getNumberOfPlayer();
		GameMaster gameMaster = GameMaster.newGame(numberOfPlayer);

		// 出力
		Player nonNpcPlayer = gameMaster.getPlayer(Rule.PLAYER_ID);
		prompt.showStatus(nonNpcPlayer);
		prompt.showField(gameMaster.getField());

		while (nonNpcPlayer.hasHand()) {

			int cardNumber = prompt.getCardFromHands(nonNpcPlayer);
			SortedMap<Card, Player> open = gameMaster.openHand(cardNumber);

			prompt.showOpen(open.keySet());

			// 1ターン
			for (Map.Entry<Card, Player> entry : open.entrySet()) {
				Card card = entry.getKey();
				Player player = entry.getValue();
				System.out.println("Dealing => playerId : " + player.getId() + ", card : " + card.getNumber());
				int line;
				if (card.getNumber() < gameMaster.getMinimum()) {
					line = getLineByPlayerChoice(player, gameMaster.getField());
				} else {
					line = gameMaster.getLineToAddLast(card.getNumber());
				}
				gameMaster.putCardAndUpdate(line, card, player.getId());
			}

			System.out.println("1巡が終わった状態");
			prompt.showField(gameMaster.getField());
			prompt.showStatus(nonNpcPlayer);
		}

		System.out.println("============= Game is Over. =============");
		prompt.showScore(gameMaster.getScores());
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
		 * プレーヤーの手札にあるカードのうち、場に出すカードの番号を取得します。
		 *
		 * @param player プレーヤー
		 * @return ユーザの手札のインデックス番号（0-{その時点のユーザの手札の枚数-1}）
		 */
		int getCardFromHands(Player player) {
			System.out.println("choose your card in you hand.");
			while(true) {
				int number = getNumberFromInput();
				if (player.hasCard(number)) {
					System.out.println("You have choose " + number);
					return number;
				}
				System.out.println("Player does not have a card you have chosen. Choose again");
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
				if (Rule.isValidFieldIndex(index)) {
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

		void showStatus(Player player) {
			System.out.println("----- status now. -----");
			StringBuilder builder = new StringBuilder();
			builder.append("playerId   : " + player.getId() + "\n");
			builder.append("cows       : " + player.getCow() + "\n");
			builder.append("your hands : \n");
			for (Card card : player.getHands()) {
				builder.append(card.getNumber() + "(" + card.getCow() + ") ");
			}
			System.out.println(builder.toString());
			System.out.println("-----------------------");
		}

		void showField(Field field) {
			System.out.println("----- Cards on field now. -----");
			for (Line line : field.showLines()) {
				System.out.print(line.getId() + ": ");
				for (Card card : line.getCards()) {
					System.out.print(card.getNumber() + "(" + card.getCow() + ")  ");
				}
				System.out.println();
			}
			System.out.println("-------------------------------");
		}

		void showScore(List<Score> scores) {
			for (Score score : scores) {
				System.out.println(score.getPlayerId() + ": " + score.getCow());
			}
		}

		public void showOpen(Set<Card> cards) {
			System.out.println("Cards to deal.");
			for (Card card: cards) {
				System.out.print(card.getNumber() + " ");
			}
			System.out.println();
		}
	}
}
