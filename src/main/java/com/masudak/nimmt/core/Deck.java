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

	/** 山札に置かれているカード */
	private List<Card> cards;

	/**
	 * 山札を初期化します。<br />
	 */
	public Deck() {
		cards = new ArrayList<Card>(Rule.NUMBER_OF_CARDS);
		for (int i = 1; i <= Rule.NUMBER_OF_CARDS; i++) {
			cards.add(new Card(i, 1)); // TODO あとで直す
		}
		Collections.shuffle(cards);
	}

	/**
	 * 山札から指定した枚数カードを抜き取ります。
	 *
	 * @param number 抜き取るカードの枚数
	 * @return 山札から抜き取られたカードの一覧
	 */
	public List<Card> getCards(int number) {
		List<Card> hand = new ArrayList<Card>(cards.subList(0, number));
		cards.subList(0, number).clear();
		return hand;
	}

	/**
	 * 山札からカードを10枚抜き取ります。
	 *
	 * @return 山札から抜き取られた10枚のカード
	 */
	public List<Card> getHands() {
		return getCards(Rule.PLAYER_HANDS);
	}
}
