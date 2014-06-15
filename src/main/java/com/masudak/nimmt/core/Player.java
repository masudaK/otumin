package com.masudak.nimmt.core;

import java.util.*;

/**
 * Nimmtを遊ぶプレーヤー情報を表します。
 * プレーヤーに対する更新系の操作は{@link GameMaster}を通じて行います。
 *
 * @author tksmaru
 */
public class Player {

	/** プレーヤーを一意に識別するID */
	private int id;

	/** プレーヤーの手札 */
	private SortedMap<Integer, Card> hands;

	/** プレーヤーが引き取った牛の数 */
	private int cow;

	public Player(int id) {
		this.id = id;
		this.hands = new TreeMap<Integer, Card>();
	}

	/**
	 * プレーヤーのIDを取得します
	 *
	 * @return 一意に識別するID
	 */
	public int getId() {
		return id;
	}

	/**
	 * プレーヤーの手札を返しますが、削除はされません。<br />
	 * また、取得した一覧に対して変更を加えても元の情報には影響を与えません。
	 *
	 * @return プレーヤーの手
	 */
	public Collection<Card> getHands() {
		return Collections.unmodifiableCollection(hands.values());
	}

	/**
	 * プレーヤーが手札を持ってるかを判定します。
	 *
	 * @return プレーヤーの手札に1枚以上カードが残っている場合にtrueを返す。
	 */
	public boolean hasHand() {
		return !hands.isEmpty();
	}

	/**
	 * プレーヤーが指定した番号を手札に持っているかチェックします。
	 * @param number カードの番号
	 * @return 番号を手札に持っている場合はtrueを返す
	 */
	public boolean hasCard(int number) {
		return hands.containsKey(number);
	}

	/**
	 * プレーヤーの手札から1枚抜き取り、カードを返します。<br />
	 * 指定した番号を手札に持っていない場合はnullを返します。
	 *
	 * @param number 手札の番号
	 * @return カード（所持していない場合はnull）
	 */
	Card pickCard(int number) {
		return hands.remove(number);
	}

	/**
	 * プレーヤーの手札からランダムに1枚抜き取り、カードを返します。
	 *
	 * @return カード
	 */
	Card pickRandom() {
		Integer[] keys = hands.keySet().toArray(new Integer[hands.size()]);
		final int index = new Random().nextInt(keys.length);
		return hands.remove(keys[index]);
	}

	/**
	 * プレーヤーに手札をセットします。
	 *
	 * @param hands カードのリスト
	 */
	void setHands(Collection<Card> hands) {
		for (Card card : hands) {
			this.hands.put(card.getNumber(), card);
		}
	}

	/**
	 * プレーヤーが現在引き取った牛の数を取得します。
	 *
	 * @return 引き取った牛の数
	 */
	public int getCow() {
		return cow;
	}

	/**
	 * 引き取った牛を加算します。
	 *
	 * @param cow 牛の数
	 */
	void addCow(int cow) {
		this.cow += cow;
	}

	/**
	 * プレーヤーがNPCかどうかを判定します。
	 *
	 * @return NPCの場合trueを返す
	 */
	public boolean isNpc() {
		return id != Rule.PLAYER_ID;
	}
}
