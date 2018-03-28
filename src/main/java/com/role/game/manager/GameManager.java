package com.role.game.manager;

import static com.role.game.constants.RolePlayingGameConstants.ATTACK_POWER;
import static com.role.game.constants.RolePlayingGameConstants.DEFAULT_HEALTH;
import static com.role.game.constants.RolePlayingGameConstants.MISSION_WELCOME_MESSAGE;
import static com.role.game.constants.RolePlayingGameConstants.WELCOME_MESSAGE;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.role.game.configuration.GameProvider;
import com.role.game.configuration.RealmConfiguration;
import com.role.game.dto.MissionDetails;
import com.role.game.dto.MissionMap;
import com.role.game.dto.MissionState;
import com.role.game.dto.PlayerDetails;
import com.role.game.exception.NoSavedGameException;
import com.role.game.util.DependencyInjector;

public class GameManager {

	private static final Logger log = LogManager.getLogger(GameManager.class);

	private static final PlayerManager playerManager = DependencyInjector.getObject(PlayerManager.class);

	private final GameMenus gameMenu;
	private MissionDetails mission;
	private PlayerDetails player;
	private final GameProvider gameProvider;

	public GameManager(GameProvider gameProvider, GameMenus gameMenus, RealmConfiguration realmConfiguration) {
		this.gameMenu = gameMenus;
		this.mission = setUpMission(realmConfiguration);
		this.gameProvider = gameProvider;
	}

	public GameManager(GameProvider gameProvider, GameMenus gameMenus) {
		this.gameMenu = gameMenus;
		this.gameProvider = gameProvider;
	}

	public void newGame() {
		this.player = setUpPlayer();
		startGame();
	}

	public void loadGame() throws NoSavedGameException {
		MissionState state = gameProvider.loadSavedGame();
		if (!Optional.ofNullable(state).isPresent()) {
			throw new NoSavedGameException("No data saved");
		}
		this.player = state.getPlayerDetails();
		this.mission = state.getMissionDetails();
		playerManager.welcomePlayer(player, mission);
		startGame();

	}

	private MissionDetails setUpMission(RealmConfiguration realmConfiguration) {
		gameMenu.getStartMenu().showMessage(MISSION_WELCOME_MESSAGE);
		mission = new MissionDetails(realmConfiguration);
		return mission;
	}

	private PlayerDetails setUpPlayer() {
		gameMenu.getStartMenu().showMessage(WELCOME_MESSAGE);
		player = new PlayerDetails(playerManager.getName(), playerManager.getGender(), playerManager.getDescription(),
				DEFAULT_HEALTH, ATTACK_POWER);
		player.setMissionMap(new MissionMap(0, 0));
		playerManager.welcomePlayer(player, mission);
		return player;
	}

	public void startGame() {
		GameExplorationManager manager = new GameExplorationManager(gameMenu, gameProvider, player, mission);
		manager.explore();
	}

}
