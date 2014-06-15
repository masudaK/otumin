package com.masudak.nimmt.core;

/**
 * Nimmtのゲームルール
 *
 * @author tksmaru
 */
public class Rule {

	/** 最小プレーヤー人数 */
	public static final int MIN_NUMBER_OF_PLAYER = 2;

	/** 最大プレーヤー人数 */
	public static final int MAX_NUMBER_OF_PLAYER = 10;

	/** プレーヤーに配られるカードの枚数 */
	public static final int PLAYER_HANDS = 10;

	/** フィールドの数 */
	public static final int FIELD_SIZE = 4;

	/** ラインに配置できる最大カード枚数 */
	public static final int LINE_MAX_SIZE = 5;

	/** Nimmtのカード枚数 */
	public static final int NUMBER_OF_CARDS = 104;

	/** プレーヤー（非NPC）のID */
	public static final int PLAYER_ID = 0;

	/**
	 * プレーヤー人数が正しいかを判定します。<br />
	 *
	 * @param number プレーヤー人数
	 * @return 2-10人であればtrue、それ以外の場合はfalseを返します。
	 */
	public static boolean isValidNumberOfPlayers(int number) {
		return MIN_NUMBER_OF_PLAYER <= number && number <= MAX_NUMBER_OF_PLAYER ? true : false;
	}

	/**
	 * 場のインデックス番号が妥当かどうかを判定します。
	 *
	 * @param index インデックス番号
	 * @return 0-3の間であればtrue、それ以外の場合はfalseを返します。
	 */
	public static boolean isValidFieldIndex(int index) {
		return 0 <= index && index < Rule.FIELD_SIZE;
	}
}
