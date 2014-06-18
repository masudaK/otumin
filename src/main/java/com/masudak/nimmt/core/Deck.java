package com.masudak.nimmt.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Nimmtの山札を表します。<br />
 * 山札に対する全ての操作は{@link GameMaster}を介して行われます。
 *
 * @author tksmaru
 */
class Deck {

	/** 山の先頭 */
	private static final int HEAD = 0;

	private static Deck instance;

	/** 山札に置かれているカード */
	private List<Card> cards;

	/**
	 * 山札を初期化します。<br />
	 */
	private Deck() {
		cards = new ArrayList<Card>(Rule.NUMBER_OF_CARDS);
		for (int i = 1; i <= Rule.NUMBER_OF_CARDS; i++) {
			cards.add(new Card(i));
		}
		Collections.shuffle(cards);
	}

	public static Deck getInstance() {
		if (instance == null) {
			instance = new Deck();
		}
		return instance;
	}

	/**
	 * 山札から指定した枚数カードを抜き取ります。
	 *
	 * @param number 抜き取るカードの枚数
	 * @return 山札から抜き取られたカードの一覧
	 */
	public List<Card> getCards(int number) {
		List<Card> hand = new ArrayList<Card>(cards.subList(HEAD, number));
		cards.subList(HEAD, number).clear();
		return hand;
	}

	/**
	 * 山札から1枚カードを抜き取ります。
	 *
	 * @return カード
	 */
	public Card get() {
		return cards.remove(HEAD);
	}
}
