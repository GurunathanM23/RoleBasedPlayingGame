package com.role.game.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.role.game.configuration.RealmConfiguration;

public class MissionDetails implements Serializable {

	private static final long serialVersionUID = 1826612687770547986L;

	private String name;

	private List<PlayerDetails> characters;

	public List<PlayerDetails> getCharacters() {
		return characters;
	}

	public void setCharacters(List<PlayerDetails> characters) {
		this.characters = characters;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public MissionDetails(RealmConfiguration realmConfiguration) {
		this.characters = new ArrayList<>();
		this.name = realmConfiguration.getName();
		this.initMission(realmConfiguration.getCharacterts(), realmConfiguration.getSize());
	}

	private void initMission(List<PlayerDetails> characterts, int size) {

		int x = 1, y = 0;
		for (int i = 0; i < characterts.size(); i++) {
			MissionMap missionMap = randomCoordinates(x, y);
			characterts.get(i).setMissionMap(missionMap);
			characters.add(characterts.get(i));
			if (x > size / 2) {
				y = y + 1;
				x = 0;
			} else {
				x = x + 1;
			}
		}
	}

	private MissionMap randomCoordinates(int x, int y) {
		return new MissionMap(x, y);
	}

	@Override
	public String toString() {
		return "MissionDetails [name=" + name + ", characters=" + characters + "]";
	}

}
