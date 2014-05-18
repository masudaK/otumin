package com.masudak.nimmt.core;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: maruyama_takashi
 * Date: 14/05/12
 * Time: 16:55
 */
public class GameMaster {

	private List<User> players;
	private Deck deck;
	private Field field;

	public GameMaster(int number) {
		createDeck();
		createPlayers(number);
		createField();
	}

	private void createDeck() {
		deck = new Deck();
		deck.shuffle();
	}

	private void createPlayers(int number) {
		players = new ArrayList<User>(number);
		for (int i = 0; i < number; i++) {
			boolean npc = i == 0 ? false : true;
			User player = new User(i + 1, npc);
			player.setHands(deck.getHands());
			players.add(player);
		}
	}

	private void createField() {
		field = new Field(deck.getCards(Rule.FIELD_SIZE));
//		field.initalize(deck.getCards(Rule.FIELD_SIZE));
	}

	public User getUser(int id) {
		// TODO
		return players.get(id - 1);
	}

	public Field getField() {
		return field;
	}

	// judgeの粒度をもっと細かくしないとつながらないことがわかった

	public SortedMap<Card, User> openHand(int userId, int index) {

		SortedMap<Card, User> map = new TreeMap<Card, User>();
		map.put(getUser(userId).pickHand(index), getUser(userId));

		// NPC
		for (int i = 1; i <players.size(); i++) {
			map.put(getUser(i + 1).pickRandom(), getUser(i + 1));
		}
		return map;

	}

	/**
	 * 各LINEの先頭で最も小さい数を返す
	 *
	 * @return
	 */
	public int getMinimum() {
		return field.getSmallestInTheLast();
	}

	public void updateFieldAndUser(int lineIndex, int userId) {
		int minus = field.clearLine(lineIndex);
		getUser(userId).addCow(minus);
	}

	public void putField(int lineIndex, Card card) {
		field.put(lineIndex, card);
	}

	/**
	 * 追加するライン番号を取得する
	 *
	 * @param number
	 * @return
	 */
	public int getLineToAddLast(int number) {
		int difference = 104;
		int index = 0;
		int i = 0;
		//List<Card> lasts = field.getLasts();
		for (Card card : field.getLasts()) {
			int temp = number - card.getNumber();
			if (temp > 0 && temp < difference) {
				difference = temp;
				index = i;
			}
			i++;
		}
		return index;
	}

	public boolean isLineFull(int lineIndex) {
		return field.getLines().get(lineIndex).isFull();
	}

	public List<Integer> showScore() {
		List<Integer> scores = new ArrayList<Integer>();
		for(User user : players) {
			scores.add(user.getCow());
		}
		return scores;
	}
}
