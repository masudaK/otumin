package com.masudak.nimmt.core;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Nimmtのゲームマスターを表します。<br />
 * Nimmtのゲーム進行に関わる操作は全てこのクラスを介して行います。
 *
 * @author tksmaru
 */
public class GameMaster {

	/** ゲームのプレーヤー一覧 */
	private List<Player> players;

	/** 山札 */
	private Deck deck;

	/** 場 */
	private Field field;

	/**
	 * プレーヤーの人数を指定してゲームマスターを初期化します。
	 *
	 * @param number ゲームの参加人数
	 */
	public GameMaster(int number) {
		createDeck();
		createPlayers(number);
		createField();
	}

	/**
	 * 104枚のカードがシャッフルされた状態で山札を生成します。
	 */
	private void createDeck() {
		deck = new Deck();
	}

	/**
	 * ゲームの参加人数分のプレーヤー情報を初期化します。<br />
	 * 注意：この操作は山札を初期化した後に実行してください。
	 *
	 * @param number ゲームの参加人数
	 */
	private void createPlayers(int number) {
		players = new ArrayList<Player>(number);
		for (int i = 0; i < number; i++) {
			boolean npc = i == 0 ? false : true;
			Player player = new Player(i + 1, npc);
			player.setHands(deck.getHands());
			players.add(player);
		}
	}

	/**
	 * 場を初期化します。<br />
	 * 注意：この操作は山札を初期化した後に実行してください。
	 */
	private void createField() {
		field = new Field(deck.getCards(Rule.FIELD_SIZE));
	}

	/**
	 * プレーヤー情報を取得します。
	 *
	 * @param id プレーヤーを一意に識別するID
	 * @return IDに紐づくプレーヤー情報
	 */
	public Player getPlayer(int id) {
		// TODO
		return players.get(id - 1);
	}

	/**
	 * 場の情報を取得します。
	 *
	 * @return 場の情報
	 */
	public Field getField() {
		return field;
	}

	/**
	 * プレーヤーが場に晒す手札を選択します。<br />
	 * 実行した結果、他のNPCユーザが選択した手札と比較して、場に並べるカードの一覧を取得します。
	 *
	 * @param userId プレーヤーを一意に特定するID
	 * @param index プレーヤーが場に晒す手札のインデックス番号
	 * @return 場に並べるカードの一覧（カード番号の昇順）
	 */
	public SortedMap<Card, Player> openHand(int userId, int index) {

		SortedMap<Card, Player> map = new TreeMap<Card, Player>();
		map.put(getPlayer(userId).pickHand(index), getPlayer(userId));

		// NPC
		for (int i = 1; i <players.size(); i++) {
			map.put(getPlayer(i + 1).pickRandom(), getPlayer(i + 1));
		}
		return map;
	}

	/**
	 * 場を構成する各列の最後尾のカードのうち最小の番号を取得します。
	 *
	 * @return 場を構成する各列の最後尾のカードのうち最小の番号
	 */
	public int getMinimum() {
		return field.getSmallestInTheLast();
	}

	/**
	 * 場にカードを並べ、必要に応じて場の情報を更新します。<br />
	 * カードを配置する列にすでに5枚カードが置かれている場合、置かれている各カードの牛の数の合計をユーザに引き取らせ、<br />
	 * 列から全てのカードを取り除いた上でカードを並べます。
	 *
	 * @param lineIndex カードを置く列のID
	 * @param card 場に配置するカード
	 * @param userId カードを配置するユーザ
	 */
	public void putCardAndUpdate(int lineIndex, Card card, int userId) {
		if (isLineFull(lineIndex)) {
			int minus = field.clearLine(lineIndex);
			getPlayer(userId).addCow(minus);
		}
		field.put(lineIndex, card);
	}

	/**
	 * カードを追加する列番号を取得します。<br />
	 * 各列の最後尾のカードよりも大きく、且つ差が最小の列番号を返します。(0-3を想定）<br />
	 * もし入力したカード番号が各列の最後尾のカードよりも小さい場合は0を返します。
	 * // TODO 要テスト
	 *
	 * @param number カードの番号
	 * @return カードを追加する列の番号。
	 */
	public int getLineToAddLast(int number) {
		int difference = Rule.NUMBER_OF_CARDS;
		int index = 0;
		int i = 0;
		for (Card card : field.getLasts()) {
			int temp = number - card.getNumber();
			if (temp > 0 && temp < difference) {
				difference = temp;
				index = i;
			}
			i++;
		}
		return index;
	}

	/**
	 * 指定した列が一杯かどうかを判定します。（列には5枚以上カードを配置することができない）
	 *
	 * @param lineIndex 列を一意に特定するID
	 * @return 列にこれ以上カードを置けない場合trueを返します。
	 */
	private boolean isLineFull(int lineIndex) {
		return field.getLine(lineIndex).isFull();
	}

	// TODO 仮実装
	public List<Integer> showScore() {
		List<Integer> scores = new ArrayList<Integer>();
		for(Player player : players) {
			scores.add(player.getCow());
		}
		return scores;
	}
}
