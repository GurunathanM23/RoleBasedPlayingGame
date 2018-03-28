package com.role.game.manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.role.game.configuration.GameProvider;
import com.role.game.configuration.RealmProvider;
import com.role.game.menu.enums.MainItemMenu;
import com.role.game.menu.interfaces.StartMenu;

/**
 * @author Guru This class is responsible for displaying the main menu when the
 *         user enters the game and from here user can start,load or close the
 *         game
 *
 */
public class GameCatalogueManager {

	private static final Logger log = LogManager.getLogger(GameCatalogueManager.class);

	private final GameMenus gameMenus;
	private final StartMenu startMenu;
	private final GameProvider gameProvider;

	public GameCatalogueManager(GameMenus gameMenus, GameProvider gameProvider) {
		this.gameMenus = gameMenus;
		this.startMenu = gameMenus.getStartMenu();
		this.gameProvider = gameProvider;
	}

	public void showMenu() throws Exception {
		log.info("Game Menus");
		startMenu.showMessage("Most welocme to play");
		MainItemMenu userSelected = fetchUserSelectedValue();

		switch (userSelected) {
		case START:
			GameManager gameManager = new GameManager(gameProvider, gameMenus, RealmProvider.loadRealms());
			gameManager.newGame();
			break;
		case LOAD:
			GameManager loadGameManager = new GameManager(gameProvider, gameMenus);
			loadGameManager.loadGame();
			break;
		case EXIT:
			startMenu.showMessage("you are welcome again");
			break;

		default:
			break;
		}

	}

	private MainItemMenu fetchUserSelectedValue() {
		startMenu.showMenu();
		MainItemMenu userSelected = startMenu.readUserChoice();
		return userSelected;
	}

}
