package com.masudak.nimmt.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Nimmtを遊ぶプレーヤー情報を表します
 *
 * @author tksmaru
 */
public class User {

	/** プレーヤーを一意に識別するID */
	private int id;

	/** プレーヤーの手札 */
	private List<Card> hands;

	/** プレーヤーが引き取った牛の数 */
	private int cow;

	/** プレーヤがNPCかどうか */
	private boolean npc;

	public User (int id, boolean npc) {
		this.id = id;
		this.npc = npc;
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
	 * プレーヤーの手札の情報を返しますが、削除はされません。<br />
	 * また、取得した一覧に対して変更を加えても元の情報には影響を与えません。
	 *
	 * @return プレーヤーの手
	 */
	public List<Card> listHands() {
		return new ArrayList<Card>(hands);
	}

	/**
	 * プレーヤーが持っている手札の現在の枚数を返します。
	 *
	 * @return 手札の枚数
	 */
	public int handsSize() {
		return hands.size();
	}

	/**
	 * プレーヤーの手札から1枚抜き取り、カードを返します。
	 *
	 * @param index 手札の何枚目のカードかを示すインデックス番号
	 * @return カード
	 */
	public Card pickHand(int index) {
		return hands.remove(index);
	}

	/**
	 * プレーヤーの手札からランダムに1枚抜き取り、カードを返します。
	 *
	 * @return カード
	 */
	public Card pickRandom() {
		return hands.remove(new Random().nextInt(hands.size()));
	}

	/**
	 * プレーヤーに手札をセットします。
	 *
	 * @param hands カードのリスト
	 */
	public void setHands(List<Card> hands) {
		this.hands = hands;
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
	public void addCow(int cow) {
		this.cow += cow;
	}

	/**
	 * プレーヤーがNPCかどうかを判定します。
	 *
	 * @return NPCの場合trueを返す
	 */
	public boolean isNpc() {
		return npc;
	}
}
