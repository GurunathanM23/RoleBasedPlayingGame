package com.role.game.dto;

import java.io.Serializable;

public class MissionMap implements Serializable {

	private static final long serialVersionUID = -7038605768765213359L;

	private final int x;
	private final int y;

	public MissionMap(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "MissionMap [x=" + x + ", y=" + y + "]";
	}

	@Override
	public boolean equals(Object o) {
		MissionMap mission = (MissionMap) o;
		return x == mission.x && y == mission.y;
	}

}
