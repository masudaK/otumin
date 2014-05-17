package com.masudak.nimmt.core;


import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: maruyama_takashi
 * Date: 14/05/12
 * Time: 20:43
 */
public class Field {

	/**
	 * size should be 4
	 */
	private List<Line> lines;

	public Field() {
		lines = new ArrayList<Line>(Rule.FIELD_SIZE);
		for (int i = 0; i < Rule.FIELD_SIZE; i++) {
			lines.add(new Line(i));
		}
	}

	public void initalize(List<Card> cards) { //throws GameException {
//		if (cards == null || cards.size() != Rule.FIELD_SIZE) {
//			throw new GameException("Cards should have " + Rule.FIELD_SIZE);
//		}
		for (int i = 0; i < Rule.FIELD_SIZE; i++) {
			lines.get(i).addFirst(cards.get(i));
		}
	}

	public List<Line> getLines() {
		return lines;
	}

	public int getSmallestInTheLast() {
		int smallest = 105;
		for (Line line : lines) {
			int head = line.getLast().getNumber();
			if (smallest > head) {
				smallest = head;
			}
		}
		return smallest;
	}

	public int clearLine(int index) {
		Line line = lines.get(index);
		int totalMintus = 0;
		while(!line.isEmpty()) {
			totalMintus += line.remove().getMinus();
		}
		return totalMintus;

	}

	public void put(int index, Card card) {
		lines.get(index).addLast(card);
	}

	public List<Card> getLasts() {
		List<Card> cards = new ArrayList<Card>();
		for (Line line : lines) {
			cards.add(line.getLast());
		}
		return cards;
	}
//	// 各Lineの先頭
//	public List<Integer> getFirsts() {
//		List<Integer> cards = new ArrayList<Integer>(Rule.FIELD_SIZE);
//		for (Line line: lines) {
//			cards.add(line.getFirst().getNumber());
//		}
//		Collections.sort(cards);
//		return cards;
//	}
//
//	public List<Integer> getLasts() {
//		List<Integer> cards = new ArrayList<Integer>(Rule.FIELD_SIZE);
//		for (Line line: lines) {
//			cards.add(line.getLast().getNumber());
//		}
//		Collections.sort(cards);
//		return cards;
//	}

}
