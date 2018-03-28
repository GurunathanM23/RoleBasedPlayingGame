package com.role.game.manager;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.role.game.configuration.GameProvider;
import com.role.game.io.reader.InputReader;
import com.role.game.io.writer.OutputWriter;
import com.role.game.menu.enums.GameExplorationMenu;
import com.role.game.menu.enums.MainItemMenu;
import com.role.game.menu.interfaces.impl.GameExplorationImpl;
import com.role.game.menu.interfaces.impl.StartMenuImpl;

public class GameCatalogueManagerTest {

	private GameMenus gameMenu;

	private StartMenuImpl startMenu;

	private GameProvider provider;

	private GameExplorationImpl exploration;

	@Before
	public void setUp() {
		gameMenu = mock(GameMenus.class);
		provider = new GameProvider(new InputReader(), new OutputWriter());
		startMenu = mock(StartMenuImpl.class);
		exploration = mock(GameExplorationImpl.class);
	}

	@Test
	public void exitGame() throws Exception {
		when(gameMenu.getStartMenu()).thenReturn(startMenu);
		when(startMenu.readUserChoice()).thenReturn(MainItemMenu.EXIT);
		GameCatalogueManager manager = new GameCatalogueManager(gameMenu, provider);
		manager.showMenu();
		Mockito.verify(startMenu, times(1)).showMenu();
		Mockito.verify(startMenu, times(1)).readUserChoice();
	}

	@Test
	public void lauchsavedGame() throws Exception {
		when(gameMenu.getStartMenu()).thenReturn(startMenu);
		when(startMenu.readUserChoice()).thenReturn(MainItemMenu.LOAD);
		when(gameMenu.getGameExploration()).thenReturn(exploration);
		when(exploration.readUserChoice()).thenReturn(GameExplorationMenu.EXIT);
		GameCatalogueManager manager = new GameCatalogueManager(gameMenu, provider);
		manager.showMenu();
		Mockito.verify(exploration, times(1)).showMenu();
		Mockito.verify(exploration, times(1)).readUserChoice();
	}

	@Test(expected = NullPointerException.class)
	public void lauchsavedGameWithoutData() throws Exception {
		when(gameMenu.getStartMenu()).thenReturn(startMenu);
		when(startMenu.readUserChoice()).thenReturn(MainItemMenu.LOAD);
		GameCatalogueManager manager = new GameCatalogueManager(gameMenu, provider);
		manager.showMenu();
		Mockito.verify(startMenu, times(1)).showMenu();
		Mockito.verify(startMenu, times(1)).readUserChoice();
	}

}
