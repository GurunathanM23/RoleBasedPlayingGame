package com.role.game.manager;

import com.role.game.menu.interfaces.Battle;
import com.role.game.menu.interfaces.BattleConfirmation;
import com.role.game.menu.interfaces.GameExploration;
import com.role.game.menu.interfaces.StartMenu;

public class GameMenus {

	private final StartMenu startMenu;

	private final GameExploration gameExploration;

	private final BattleConfirmation battleConfirmation;

	private final Battle battle;

	public GameMenus(StartMenu startMenu, GameExploration gameExploration, BattleConfirmation battleConfirmation,
			Battle battle) {
		this.startMenu = startMenu;
		this.gameExploration = gameExploration;
		this.battleConfirmation = battleConfirmation;
		this.battle = battle;
	}

	public StartMenu getStartMenu() {
		return startMenu;
	}

	public GameExploration getGameExploration() {
		return gameExploration;
	}

	public BattleConfirmation getBattleConfirmation() {
		return battleConfirmation;
	}

	public Battle getBattle() {
		return battle;
	}

}
