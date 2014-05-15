package com.masudak.nimmt.core;

/**
 * Created with IntelliJ IDEA.
 * User: maruyama_takashi
 * Date: 14/05/12
 * Time: 16:16
 */
public class Card implements Comparable<Card> {
	private int number;
	private int minus;

	public Card(int number, int minus) {
		this.number = number;
		this.minus = minus;
	}

	public int getNumber() {
		return number;
	}

	public int getMinus() {
		return minus;
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
