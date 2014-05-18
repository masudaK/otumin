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
		cards = new ArrayList<Card>(Rule.NUMBER_OF_CARDS);
		for (int i = 1; i <= Rule.NUMBER_OF_CARDS; i++) {
			cards.add(new Card(i, 1)); // TODO あとで直す
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

	// TODO 必要？
	public int size() {
		return cards.size();
	}
}
