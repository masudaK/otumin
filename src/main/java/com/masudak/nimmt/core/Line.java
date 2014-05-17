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

	public void addLast(Card card) {
		cards.addLast(card);
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

	public boolean isEmpty() {
		return cards.isEmpty();
	}

	/**
	 * 先頭のカードを抜きます。
	 *
	 * @return
	 */
	public Card remove() {
		return cards.remove();
	}

	/**
	 * LINEにこれ以上カードをおけるかどうかを判定する
	 *
	 * @return
	 */
	public boolean isFull() {
		if (cards.size() >= 5) {
			return true;
		} else {
			return false;
		}
	}
}
