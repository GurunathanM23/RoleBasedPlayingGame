
package com.role.game.launcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.role.game.manager.GameCatalogueManager;
import com.role.game.util.DependencyInjector;

/**
 * This class is responsible for triggering the game.
 *
 */
public class GameLauncher {

	private final static Logger log = LogManager.getLogger(GameLauncher.class);

	private final static GameCatalogueManager catalogueManager = DependencyInjector
			.getObject(GameCatalogueManager.class);

	public static void launchGame() {
		log.trace("Starting the game");
		try {
			catalogueManager.showMenu();
		} catch (Exception e) {
			log.info("Something unexpected happened in System.Please restart the game");
			shutdown();
		}
	}

	public static void shutdown() {
		log.info("Shutting down the application");
		System.exit(1);

	}

}
