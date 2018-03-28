package com.role.game.configuration;

import static com.role.game.constants.RolePlayingGameConstants.MAP_INFO_DETAILS;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.role.game.dto.MissionDetails;
import com.role.game.dto.MissionMap;
import com.role.game.dto.PlayerDetails;

public class MapView {

	public String buildMap(PlayerDetails details, MissionDetails mission) {
		StringBuilder builder = new StringBuilder();
		List<MissionMap> missionMap = mission.getCharacters().stream().map(PlayerDetails::getMissionMap)
				.collect(Collectors.toList());
		missionMap.sort((MissionMap m1, MissionMap m2) -> m2.getY() - m1.getY());
		MissionMap yaxis = missionMap.stream().findFirst().orElse(null);
		int y = yaxis.getY();
		missionMap.sort((MissionMap m1, MissionMap m2) -> m2.getX() - m1.getX());
		MissionMap xaxis = missionMap.stream().findFirst().orElse(null);
		int x = xaxis.getX();
		for (int i = 0; i <= y; i++) {
			for (int j = 0; j <= x; j++) {
				if (i == details.getMissionMap().getX() && j == details.getMissionMap().getY()) {
					builder.append("P ");
				} else if (setEnemyCoordinates(mission, new MissionMap(j, i))) {
					builder.append("E ");
				} else {
					builder.append("  ");
				}
			}
			builder.append("\n");
		}
		return builder.toString();
	}

	public String showMapInfo() {
		return MAP_INFO_DETAILS;
	}

	public boolean setEnemyCoordinates(MissionDetails coordiantes, MissionMap missionMap) {
		PlayerDetails enemy = coordiantes.getCharacters().stream()
				.filter(mission -> mission.getMissionMap().equals(missionMap)).findAny().orElse(null);
		return Optional.ofNullable(enemy).isPresent();
	}

}
