package com.masudak.nimmt.core;

/**
 * Nimmtにおける1枚のカードを表します
 *
 * @author tksmaru
 */
public class Card implements Comparable<Card> {
	/** カードの番号 */
	private int number;
	/** 牛の数 */
	private int cow;

	public Card(int number) {
		this.number = number;
		this.cow = getCow(number);
	}

	/**
	 * カード番号に伴う牛の数を取得します。
	 *
	 * @param number カード番号
	 * @return 牛の数。
	 */
	private int getCow(int number) {
		int cow = 0;
		if (number % 11 == 0) cow += 5;
		if (number % 10 == 5) cow += 2;
		if (number % 10 == 0) cow += 3;
		if (cow == 0) cow = 1;
		return cow;
	}

	/**
	 * カードの番号を取得します。
	 *
	 * @return カードの番号
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * カードの牛の数を取得します。
	 *
	 * @return 牛の数
	 */
	public int getCow() {
		return cow;
	}

	@Override
	public int compareTo(Card card) {
		if (this.number > card.getNumber()) {
			return 1;
		} else if (this.number < card.getNumber()) {
			return -1;
		} else {
			return 0;
		}
	}
}
