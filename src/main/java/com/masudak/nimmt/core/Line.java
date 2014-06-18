package com.masudak.nimmt.core;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Nimmtのフィールド内の各列を表します。
 * 列に対する各操作は{@link Field}を介して行います。
 *
 * @author tksmaru
 */
public class Line {

	/** 列におけるカードの最大枚数 */
	private static final int MAX_SIZE = Rule.LINE_MAX_SIZE;

	/** 列番号(0-3) */
	private int id;

	/** 列が保持するカード */
	private Deque<Card> cards;

	Line(int id) {
		this.id = id;
		this.cards = new LinkedList<Card>();
	}

	private Line(int id, Deque<Card> cards) {
		this.id = id;
		this.cards = new LinkedList<Card>(cards);
	}

	/**
	 * 列番号を取得します。
	 *
	 * @return 列番号
	 */
	public int getId() {
		return id;
	}

	/**
	 * 列の最後尾にカードを置きます。
	 *
	 * @throws IndexOutOfBoundsException 列にカードをこれ以上追加できない場合
	 * @param card カード
	 */
	void addLast(Card card) {
		if (isFull()) {
			throw new IndexOutOfBoundsException("Line is full.");
		}
		cards.addLast(card);
	}

	/**
	 * 列の最後尾のカードを取得します。<br />
	 * 但し、列からは削除しません。
	 *
	 * @return 列の最後尾のカード
	 */
	public Card getLast() {
		return cards.getLast();
	}

	/**
	 * 列内の全てのカードのリストを先頭順に取得しますが、削除はしません。<br />
	 * また、取得したリスト内のカードに対して変更を加えても列の情報が変更されることはありません。
	 *
	 * @return 列内の全てのカードのリスト（先頭順）
	 */
	public Deque<Card> getCards() {
		return new LinkedList<Card>(cards);
	}

	/**
	 * 列にカードが置かれていないかどうかを判定します。
	 *
	 * @return 列が空っぽの場合trueを返します。
	 */
	public boolean isEmpty() {
		return cards.isEmpty();
	}

	/**
	 * 列の先頭のカードを取得して、列から削除します。
	 *
	 * @return Card
	 */
	Card remove() {
		return cards.remove();
	}

	/**
	 * 自身のコピーを取得します。（deep copy）
	 * @return {@link Line} オブジェクトのコピー
	 */
	Line copy() {
		return new Line(id, cards);
	}

	/**
	 * 列が一杯かどうかを判定します。<br />
	 * 列にカードが5枚置いてある場合、列にこれ以上カードを置くことは出来ません。
	 *
	 * @return 列にカードが5枚置いてある場合にtrueを返す
	 */
	public boolean isFull() {
		if (cards.size() >= MAX_SIZE) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 列に並んでいる全カードの牛の数の合計を取得します。
	 *
	 * @return 牛の数の合計
	 */
	public int getTotalCows() {
		int totalCows = 0;
		for (Card card : cards) {
			totalCows += card.getCow();
		}
		return totalCows;
	}

	void clear() {
		cards.clear();
	}
}
