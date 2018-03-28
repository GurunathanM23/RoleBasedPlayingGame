package com.role.game.menu.enums;

import java.util.stream.Stream;

public enum BattleConfirmationMenu {

	FIGHT("1", "Fight with the opponent"), FALLBACK("2", "Fall Back from this fight");

	private String key;
	private String desc;

	BattleConfirmationMenu(String key, String value) {
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
	public BattleConfirmationMenu getExplorationMenuValue(String userInput) {
		return Stream.of(BattleConfirmationMenu.values()).filter(item -> item.key.equals(userInput)).findAny()
				.orElse(null);
	}
}
