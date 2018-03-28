package com.role.game.manager;

import static com.role.game.constants.RolePlayingGameConstants.GAME_SAVED;
import static com.role.game.constants.RolePlayingGameConstants.XAXIS;
import static com.role.game.constants.RolePlayingGameConstants.YAXIS;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.role.game.configuration.GameProvider;
import com.role.game.configuration.MapView;
import com.role.game.dto.MissionDetails;
import com.role.game.dto.MissionMap;
import com.role.game.dto.PlayerDetails;
import com.role.game.launcher.GameLauncher;
import com.role.game.menu.enums.GameExplorationMenu;
import com.role.game.util.DependencyInjector;

public class GameExplorationManager {

	public static final Logger log = LogManager.getLogger(GameExplorationManager.class);
	private final GameMenus gameMenus;
	private final GameProvider gameProvider;
	private final PlayerDetails player;
	private final MissionDetails mission;
	private static final MapView mapView = DependencyInjector.getObject(MapView.class);

	public GameExplorationManager(GameMenus gameMenu, GameProvider gameProvider, PlayerDetails player,
			MissionDetails mission) {
		this.gameMenus = gameMenu;
		this.gameProvider = gameProvider;
		this.player = player;
		this.mission = mission;
	}

	public void explore() {
		gameMenus.getGameExploration().showMenu();
		GameExplorationMenu userSelected = gameMenus.getGameExploration().readUserChoice();

		while (GameExplorationMenu.EXIT != userSelected) {
			MissionMap map = player.getMissionMap();
			switch (userSelected) {
			case UP:
				movePlayer(map.getX(), map.getY() - 1);
				break;
			case DOWN:
				movePlayer(map.getX(), map.getY() + 1);
				break;
			case LEFT:
				movePlayer(map.getX() - 1, map.getY());
				break;
			case RIGHT:
				movePlayer(map.getX() + 1, map.getY());
				break;
			case MAP:
				gameMenus.getGameExploration().showMessage("Here is the map of your mission");
				System.out.println(mapView.buildMap(player, mission));
				break;
			case PLAYER:
				gameMenus.getGameExploration().showMessage(player.toString());
				break;
			case SAVE:
				saveGame();
				break;
			case EXIT:
				saveGame();
				GameLauncher.launchGame();
				break;
			default:
				break;
			}
			userSelected = gameMenus.getGameExploration().readUserChoice();
		}

		if (userSelected == GameExplorationMenu.EXIT) {
			saveGame();
		}

	}

	private void movePlayer(int x, int y) {
		if (Math.signum(x) >= 0 && x <= checkValidSelection(XAXIS) && Math.signum(y) >= 0
				&& y <= checkValidSelection(YAXIS)) {
			MissionMap missionMap = new MissionMap(x, y);
			PlayerDetails enemy = mission.getCharacters().stream()
					.filter(mission -> mission.getMissionMap().equals(missionMap)).findAny().orElse(null);
			gameMenus.getGameExploration().showMessage(mapView.buildMap(player, mission));
			Optional.ofNullable(enemy).ifPresent(optionalEnemy -> {
				gameMenus.getGameExploration().showMessage("Enemy sighted");
				gameMenus.getGameExploration().showMessage(optionalEnemy.toString());
				new BattleManager(player, enemy, gameMenus).fight();
				gameMenus.getGameExploration().showMessage(mapView.buildMap(player, mission));
				gameMenus.getGameExploration().showMenu();
			});
		} else {
			gameMenus.getGameExploration().showMessage("Movement not posssible");
		}

	}

	void saveGame() {
		log.traceEntry();
		gameProvider.saveGame(mission, player);
		gameMenus.getGameExploration().showMessage(GAME_SAVED);

	}

	private int checkValidSelection(String sort) {
		int maxCoordinates = 5;
		List<MissionMap> missionMap = mission.getCharacters().stream().map(PlayerDetails::getMissionMap)
				.collect(Collectors.toList());
		switch (sort) {
		case YAXIS:
			missionMap.sort((MissionMap m1, MissionMap m2) -> m2.getY() - m1.getY());
			MissionMap yaxis = missionMap.stream().findFirst().orElse(null);
			maxCoordinates = yaxis.getY();
			break;
		case XAXIS:
			missionMap.sort((MissionMap m1, MissionMap m2) -> m2.getX() - m1.getX());
			MissionMap xaxis = missionMap.stream().findFirst().orElse(null);
			maxCoordinates = xaxis.getX();
		default:
			break;
		}
		return maxCoordinates;

	}
}
