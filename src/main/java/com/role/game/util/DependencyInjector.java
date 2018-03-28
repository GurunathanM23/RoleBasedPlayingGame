
package com.role.game.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.role.game.configuration.GameProvider;
import com.role.game.configuration.MapView;
import com.role.game.exception.DependencyInjectionException;
import com.role.game.io.reader.InputReader;
import com.role.game.io.writer.OutputWriter;
import com.role.game.manager.GameCatalogueManager;
import com.role.game.manager.GameMenus;
import com.role.game.manager.PlayerManager;
import com.role.game.menu.interfaces.Battle;
import com.role.game.menu.interfaces.BattleConfirmation;
import com.role.game.menu.interfaces.GameExploration;
import com.role.game.menu.interfaces.StartMenu;
import com.role.game.menu.interfaces.impl.BattleConfirmationImpl;
import com.role.game.menu.interfaces.impl.BattleImpl;
import com.role.game.menu.interfaces.impl.GameExplorationImpl;
import com.role.game.menu.interfaces.impl.StartMenuImpl;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class DependencyInjector {

	private static final Map<Class, Object> objectMap = new HashMap<>();
	static {
		createObjects();
	}

	private static void createObjects() {
		consoleIOObjects();
		frameMenuObjects();
		frameManagerObjects();

	}

	private static void frameManagerObjects() {
		GameProvider gameProvider = new GameProvider(getObject(InputReader.class), getObject(OutputWriter.class));
		frameObjectMap(GameProvider.class, gameProvider);
		GameCatalogueManager gamecatalogueManger = new GameCatalogueManager(getObject(GameMenus.class), gameProvider);
		frameObjectMap(GameCatalogueManager.class, gamecatalogueManger);
		PlayerManager questionManager = new PlayerManager();
		frameObjectMap(PlayerManager.class, questionManager);
	}

	private static void frameMenuObjects() {
		StartMenu startMenu = new StartMenuImpl(getObject(InputReader.class), getObject(OutputWriter.class));
		frameObjectMap(StartMenu.class, startMenu);
		Battle battle = new BattleImpl(getObject(InputReader.class), getObject(OutputWriter.class));
		frameObjectMap(Battle.class, battle);
		BattleConfirmation battleConfirmation = new BattleConfirmationImpl(getObject(InputReader.class),
				getObject(OutputWriter.class));
		frameObjectMap(BattleConfirmation.class, battleConfirmation);
		GameExploration gameExploration = new GameExplorationImpl(getObject(InputReader.class),
				getObject(OutputWriter.class));
		frameObjectMap(GameExploration.class, gameExploration);
		GameMenus gameMenus = new GameMenus(startMenu, gameExploration, battleConfirmation, battle);
		frameObjectMap(GameMenus.class, gameMenus);
		MapView mapView = new MapView();
		frameObjectMap(MapView.class, mapView);
	}

	private static void consoleIOObjects() {
		OutputWriter writter = new OutputWriter();
		frameObjectMap(OutputWriter.class, writter);
		InputReader reader = new InputReader(System.in);
		frameObjectMap(InputReader.class, reader);
	}

	private static void frameObjectMap(Class className, Object object) {
		objectMap.put(className, object);
	}

	public static <T> T getObject(Class className) {
		return (T) Optional.ofNullable(objectMap.get(className)).orElseThrow(() -> new DependencyInjectionException());
	}

}
