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
	private PlayerManager playerManager;

	/** 山札 */
	private Deck deck;

	/** 場 */
	private Field field;

	private GameMaster(int number) {
		this.deck = Deck.getInstance();
		this.playerManager = PlayerManager.getInstance();
		this.field = Field.getInstance();
		initPlayers(number);
		initField();
	}

	/**
	 * プレーヤーの人数を指定してゲームを初期化します。
	 *
	 * @param numberOfPlayers ゲームの参加人数
	 */
	public static GameMaster newGame(int numberOfPlayers) {
		return new GameMaster(numberOfPlayers);
	}

	/**
	 * ゲームの参加人数分のプレーヤー情報を初期化します。<br />
	 *
	 * @param number ゲームの参加人数
	 */
	private void initPlayers(int number) {
		for (int i = 0; i < number; i++) {
			Player player = playerManager.createPlayer();
			player.setHands(deck.getCards(Rule.PLAYER_HANDS));
		}
	}

	/**
	 * 場を初期化します。<br />
	 * 注意：この操作は山札を初期化した後に実行してください。
	 */
	private void initField() {
		for (Line line : field.getLines()) {
			line.clear();
			line.addLast(deck.get());
		}
	}

	/**
	 * プレーヤー情報を取得します。
	 *
	 * @param id プレーヤーを一意に識別するID
	 * @return IDに紐づくプレーヤー情報
	 */
	public Player getPlayer(int id) {
		return playerManager.findById(id);
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
	 * @param cardNumber プレーヤーが場に晒す手札のインデックス番号
	 * @return 場に並べるカードの一覧（カード番号の昇順）
	 */
	// TODO 何かしっくりこない
	public SortedMap<Card, Player> openHand(int cardNumber) {
		SortedMap<Card, Player> map = new TreeMap<Card, Player>();
		for (Player player : playerManager.getAllPlayers()) {
			if (player.isNpc()) {
				map.put(player.pickRandom(), player);
			} else {
				map.put(player.pickCard(cardNumber), player);
			}
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
	 * カードを配置する列の後ろにカードを追加できない場合、置かれている各カードの牛の数の合計をユーザに引き取らせ、<br />
	 * 列から全てのカードを取り除いた上でカードを並べます。
	 *
	 * @param lineIndex カードを置く列のID
	 * @param card 場に配置するカード
	 * @param userId カードを配置するユーザ
	 */
	public void putCardAndUpdate(int lineIndex, Card card, int userId) {
		if (! isAbleToAddLast(lineIndex, card.getNumber())) {
			int cows = field.clearLine(lineIndex);
			getPlayer(userId).addCow(cows);
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
	 * 指定した列の最後尾にカードを追加できるか判定します。<br />
	 * 列にすでに5枚カードが並んでいる場合はカードを追加することはできません。<br />
	 * また、入力した番号が指定した列の最後尾のカードよりも小さい場合も追加することはできません。
	 *
	 * @param lineIndex 列を一意に特定するID
	 * @param number プレーヤーが出した手札の番号
	 * @return カードが追加できる場合true、追加できない場合はfalseを返します。
	 */
	private boolean isAbleToAddLast(int lineIndex, int number) {
		Line line = field.getLine(lineIndex);
		return !line.isFull() && number > line.getLast().getNumber();
	}

	// TODO 仮実装
	public List<Score> getScores() {
		List<Score> scores = new ArrayList<Score>();
		for(Player player : playerManager.getAllPlayers()) {
			scores.add(player.getScore());
		}
		return scores;
	}
}
