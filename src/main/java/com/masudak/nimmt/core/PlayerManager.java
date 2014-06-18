package com.masudak.nimmt.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ゲームに参加するプレーヤーを管理します。
 *
 * @author tksmaru
 */
public class PlayerManager {

	private static final int INITIAL = 0;

	private Map<Integer, Player> players;

	private AtomicInteger idManager;

	private static PlayerManager instance;

	private PlayerManager() {
		this.players = new HashMap<Integer, Player>();
		this.idManager = new AtomicInteger(INITIAL);
	}

	public static PlayerManager getInstance() {
		if (instance == null) {
			instance = new PlayerManager();
		}
		return instance;
	}

	Player createPlayer() {
		Player player = new Player(idManager.getAndIncrement());
		players.put(player.getId(), player);
		return player;
	}

	public Player findById(int id) {
		return players.get(id);
	}

	public Collection<Player> getAllPlayers() {
		return players.values();
	}
}
