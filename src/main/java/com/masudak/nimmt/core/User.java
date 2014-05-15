package com.masudak.nimmt.core;

import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: maruyama_takashi
 * Date: 14/05/12
 * Time: 16:13
 */
public class User {

	/** ユーザを一意に識別するID */
	private int id;

	/** ユーザの手 */
	private List<Card> hands;

	/** ユーザの現在のマイナスポイント */
	private int minus;

	private boolean npc;

	public User (int id, boolean npc) {
		this.id = id;
		this.npc = npc;
	}

	public int getId() {
		return id;
	}

//	public void setId(int id) {
//		this.id = id;
//	}

	public List<Card> listHands() {
		return hands;
	}

	public int handsSize() {
		return hands.size();
	}

	public Card pickHand(int index) {
		return hands.remove(index);
	}

	public Card pickRandom() {
		return hands.remove(new Random().nextInt(hands.size()));
	}

	public void setHands(List<Card> hands) {
		this.hands = hands;
	}

	public int getMinus() {
		return minus;
	}

	public void setMinus(int minus) {
		this.minus = minus;
	}

	public boolean isNpc() {
		return npc;
	}

	public void setNpc(boolean npc) {
		this.npc = npc;
	}
}
