package com.role.game.dto;

import java.io.Serializable;

public class MissionState implements Serializable{

	private static final long serialVersionUID = -7410706557414152599L;

	private MissionDetails missionDetails;
	
	private PlayerDetails playerDetails;
	
	public MissionState(MissionDetails missionDetails,PlayerDetails playerDetails) {
		this.missionDetails = missionDetails;
		this.playerDetails = playerDetails;
	}

	public MissionDetails getMissionDetails() {
		return missionDetails;
	}

	public void setMissionDetails(MissionDetails missionDetails) {
		this.missionDetails = missionDetails;
	}

	public PlayerDetails getPlayerDetails() {
		return playerDetails;
	}

	public void setPlayerDetails(PlayerDetails playerDetails) {
		this.playerDetails = playerDetails;
	}

	@Override
	public String toString() {
		return "MissionState [missionDetails=" + missionDetails + ", playerDetails=" + playerDetails + "]";
	}
	
}
