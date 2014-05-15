package com.masudak.nimmt.core;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: maruyama_takashi
 * Date: 14/05/12
 * Time: 21:30
 */
public class Line {
	private int id;
	private Deque<Card> cards;

	public Line(int id) {
		this.id = id;
		cards = new LinkedList<Card>();
	}

	public int getId() {
		return id;
	}

	public void addFirst(Card card) {
		cards.addFirst(card);
	}

	public Card getFirst() {
		return cards.getFirst();
	}

	public Card getLast() {
		return cards.getLast();
	}

	public Deque<Card> getCards() {
		return cards;
	}
}
