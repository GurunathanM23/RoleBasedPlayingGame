package com.role.game.menu.enums;

import java.util.stream.Stream;

public enum BattleMenu {

	ATTACK("1", "Attack with your weapon"), DEFENCE("2", "Prevent the enemy attack by defencing"), FALLBACK("3",
			"Fallback from this fight");

	private String key;
	private String desc;

	BattleMenu(String key, String value) {
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

	public BattleMenu getExplorationMenuValue(String userInput) {
		return Stream.of(BattleMenu.values()).filter(item -> item.key.equals(userInput)).findAny().orElse(null);
	}

}
