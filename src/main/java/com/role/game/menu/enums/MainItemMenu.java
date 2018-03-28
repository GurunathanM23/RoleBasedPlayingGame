package com.role.game.menu.enums;

import java.util.Optional;

public enum MainItemMenu {

	START("1", "Start a game"), LOAD("2", "Load a saved status of game"), EXIT("3", "Close the game");

	private final String description;
	private final String key;

	MainItemMenu(String key, String description) {
		this.description = description;
		this.key = key;
	}

	@Override
	public String toString() {
		return key+": "+description;
	}

	public String getValue(String label) {
		return Optional.ofNullable(MainItemMenu.valueOf(label)).map(String::valueOf).orElse(null);
	}
}
