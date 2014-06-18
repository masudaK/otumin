package com.masudak.nimmt.core;

public class Score {

	private int playerId;
	private int cow;

	Score(int playerId, int cow) {
		this.playerId = playerId;
		this.cow = cow;
	}

	public int getPlayerId() {
		return playerId;
	}

	public int getCow() {
		return cow;
	}
}
