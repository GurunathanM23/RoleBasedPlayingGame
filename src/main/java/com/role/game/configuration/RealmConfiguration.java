package com.role.game.configuration;

import java.io.Serializable;
import java.util.List;

import com.role.game.dto.PlayerDetails;

public class RealmConfiguration implements Serializable {

	private static final long serialVersionUID = 5268273896837240212L;

	private String name;

	private int size;

	private List<PlayerDetails> characterts;

	RealmConfiguration(String name, int size, List<PlayerDetails> characterts) {
		this.name = name;
		this.size = size;
		this.characterts = characterts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<PlayerDetails> getCharacterts() {
		return characterts;
	}

	public void setCharacterts(List<PlayerDetails> characterts) {
		this.characterts = characterts;
	}

	public String realmDescriptionn() {
		return String.format("Battle till dead", name, size);
	}

	@Override
	public String toString() {
		return "RealmConfiguration [name=" + name + ", size=" + size + ", characterts=" + characterts + "]";
	}
	


}
