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
		field = new Field();
		field.initalize(deck.getCards(Rule.FIELD_SIZE));
	}

	public User getUser(int id) {
		// TODO
		return players.get(id - 1);
	}

	public Field getField() {
		return field;
	}

	// judgeの粒度をもっと細かくしないとつながらないことがわかった

	public void judge(int id, int index) {
		// TODO
//		List<Card> tempField = new ArrayList<Card>(players.size());
		Map<Card, User> tempField = new TreeMap<Card, User>();
		tempField.put(getUser(id).pickHand(index), getUser(id));

		// NPC
		for (int i = 1; i <players.size(); i++) {
			tempField.put(getUser(i + 1).pickRandom(), getUser(id + 1));
		}

		int smallest = field.getSmallestInTheFirst();
		for (Card card : tempField.keySet()) {
			System.out.println(card.getNumber() + "の処理を行います");

			if (card.getNumber() < smallest) {
				// どこに置くか決めてもらってLineを更新してユーザのマイナスポイントを追加する
				User player = tempField.get(card);
				if (player.isNpc()) {
					// ランダムにおく
				} else {
					// ユーザに選択させる
				}
				// マイナスポイントとLineのカード情報を更新する

			} else {
				// 「そのカードの数字より小さいもののうちで最大のもの」の後ろに並べる
				// 並べた時点で列が6になったら、その前の5枚を取得してマイナスポイントを加算する
				card.getNumber();
			}
		}

	}
}
