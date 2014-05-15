package com.masudak.nimmt.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: maruyama_takashi
 * Date: 14/05/12
 * Time: 16:20
 */
public class Deck {

	private List<Card> cards;

	public Deck() {
		cards = new ArrayList<Card>(104);
		for (int i = 1; i <= 104; i++) {
			cards.add(new Card(i, -1)); // あとで直す
		}
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public List<Card> getCards(int number) {
		List<Card> hand = new ArrayList<Card>(cards.subList(0, number));
		cards.subList(0, number).clear();
		return hand;
	}

	public List<Card> getHands() {
		return getCards(Rule.PLAYER_HANDS);
	}

	public int size() {
		return cards.size();
	}
}
