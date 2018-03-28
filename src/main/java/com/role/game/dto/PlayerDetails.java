package com.role.game.dto;

import java.io.Serializable;

public class PlayerDetails implements Serializable {

	private static final long serialVersionUID = -6597512212878790293L;

	private String name;

	private String gender;

	private String description;

	private int health;

	private int attackPower;

	private MissionMap missionMap;

	public PlayerDetails(String name, String gender, String description, int health, int attackPower) {
		this.name = name;
		this.gender = gender;
		this.description = description;
		this.health = health;
		this.attackPower = attackPower;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}

	public MissionMap getMissionMap() {
		return missionMap;
	}

	public void setMissionMap(MissionMap missionMap) {
		this.missionMap = missionMap;
	}

	@Override
	public String toString() {
		return "name=" + name + ", gender=" + gender + ", description=" + description + ", health=" + health
				+ ", attackPower=" + attackPower + ", missionMap=" + missionMap;
	}

}
