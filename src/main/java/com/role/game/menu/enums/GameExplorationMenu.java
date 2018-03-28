package com.role.game.menu.enums;

import java.util.stream.Stream;

public enum GameExplorationMenu {
	UP("1", "Move up"), DOWN("2", "Move Down"), LEFT("3", "Move left"), RIGHT("4", "Move Right"), MAP("5",
			"Show map"), PLAYER("6", "player info"), SAVE("7", "Save the game"), EXIT("8", "Exit the game");
	private String key;
	private String desc;

	GameExplorationMenu(String key, String value) {
		this.key = key;
		this.desc = value;
	}

	public String getKey() {
		return key;
	}

	public String getDesc() {
		return desc;
	}

	@Override
	public String toString() {
		return key+": "+desc;
	}
	
	public GameExplorationMenu getExplorationMenuValue(String userInput) {
		return Stream.of(GameExplorationMenu.values()).filter(item -> item.key.equals(userInput)).findAny()
				.orElse(null);
	}

}
