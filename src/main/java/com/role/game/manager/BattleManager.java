package com.role.game.manager;

import static com.role.game.constants.RolePlayingGameConstants.ATTACK_POWER;
import static com.role.game.constants.RolePlayingGameConstants.DEFAULT_HEALTH;

import com.role.game.dto.PlayerDetails;
import com.role.game.menu.enums.BattleConfirmationMenu;
import com.role.game.menu.enums.BattleMenu;
import com.role.game.menu.interfaces.Battle;
import com.role.game.menu.interfaces.BattleConfirmation;

public class BattleManager {

	private PlayerDetails player;
	private PlayerDetails enemy;
	private GameMenus gamemenus;
	private Battle battleMenu;
	private BattleConfirmation confirmation;
	private boolean gameEnds;

	public BattleManager(PlayerDetails player, PlayerDetails enemy, GameMenus gameMenus) {
		this.player = player;
		this.enemy = enemy;
		this.gamemenus = gameMenus;
		battleMenu = gameMenus.getBattle();
		confirmation = gameMenus.getBattleConfirmation();
	}

	public void fight() {
		if (userOptedTofight()) {
			startBattle();
		} else {
			gamemenus.getBattle().showMessage("Need to Train more");
		}
	}

	private void startBattle() {
		battleMenu.showMenu();
		BattleMenu battle = battleMenu.readUserChoice();
		while (!gameEnds) {
			switch (battle) {
			case ATTACK:
				attack(player, enemy);
				attack(enemy, player);
				userStatusMessage(player, enemy);
				break;
			case DEFENCE:
				battleMenu.showMessage("you have defended the attack by your enemy");
				attack(player, enemy);
				userStatusMessage(player, enemy);
				break;

			case FALLBACK:
				player.setHealth(player.getHealth() - 5);
				battleMenu.showMessage("Your health hase been reduced by 5 points");
				battleMenu.showMessage(player.toString());
				gameEnds = true;
				break;

			default:
				break;
			}
			if (!gameEnds) {
				battleMenu.showMenu();
				battle = battleMenu.readUserChoice();
			}

		}
	}

	private void userStatusMessage(PlayerDetails player, PlayerDetails enemy) {
		if (enemy.getHealth() <= 0) {
			player.setMissionMap(enemy.getMissionMap());
			resetPlayerHeath(player, DEFAULT_HEALTH + 5, ATTACK_POWER + 2);
			battleMenu
					.showMessage("It was an awesome fight.You have won the battle.You are gaining much mor experience");
			battleMenu.showMessage(player.toString());
			gameEnds = true;

		} else if (player.getHealth() <= 0) {
			resetPlayerHeath(player, DEFAULT_HEALTH, ATTACK_POWER);
			battleMenu.showMessage(
					"You need some more tarining.Please fight with enemies less powerful tha you and gain experience");
			gameEnds = true;

		} else {
			battleMenu.showMessage(player.toString());
			battleMenu.showMessage(enemy.toString());
		}
	}

	private void resetPlayerHeath(PlayerDetails player, int health, int attackPower) {
		player.setHealth(health);
		player.setAttackPower(attackPower);
	}

	private void attack(PlayerDetails attacker, PlayerDetails defender) {
		defender.setHealth(defender.getHealth() - attacker.getAttackPower());

	}

	private boolean userOptedTofight() {
		boolean startFight = false;
		confirmation.showMenu();
		BattleConfirmationMenu selectedValue = confirmation.readUserChoice();
		switch (selectedValue) {
		case FIGHT:
			startFight = true;
			break;
		case FALLBACK:
			startFight = false;
			break;
		default:
			break;
		}
		return startFight;
	}

}
