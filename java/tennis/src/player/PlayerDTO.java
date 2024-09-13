package player;

import constants.Constants;
import util.Util;

public class PlayerDTO {
	private final int idCounter = Integer.parseInt(Util.getCurrentId(Constants.PLAYER_FILE_PATH));
	private int playerId = idCounter;
	private String name;
	private int wins;
	private int loses;
	private double oddsOfWinning;

	public PlayerDTO(int playerId, String name, int wins, int loses, double oddsOfWinning) {
		this.playerId = playerId;
		this.name = name;
		this.wins = wins;
		this.loses = loses;
		this.oddsOfWinning = oddsOfWinning;
	}

	public PlayerDTO(String name, int wins, int loses, double oddsOfWinning) {
		this.name = name;
		this.wins = wins;
		this.loses = loses;
		this.oddsOfWinning = oddsOfWinning;
	}

	public PlayerDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLoses() {
		return loses;
	}

	public void setLoses(int loses) {
		this.loses = loses;
	}

	public double getOddsOfWinning() {
		return oddsOfWinning;
	}

	public void setOddsOfWinning(double oddsOfWinning) {
		this.oddsOfWinning = oddsOfWinning;
	}

	@Override
	public String toString() {
		return String.format("PlayerDTO [playerId=%s, name=%s, wins=%s, loses=%s, oddsOfWinning=%s]", this.playerId,
				this.name, this.wins, this.loses, this.oddsOfWinning);
	}

}
