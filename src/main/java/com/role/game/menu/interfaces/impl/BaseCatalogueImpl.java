package com.role.game.menu.interfaces.impl;

import static com.role.game.constants.RolePlayingGameConstants.NO_OPTION_SELECTED;
import static com.role.game.constants.RolePlayingGameConstants.OPTION_SELECT;
import static com.role.game.constants.RolePlayingGameConstants.TAB;

import java.util.List;
import java.util.Optional;

import com.role.game.io.reader.InputReader;
import com.role.game.io.writer.OutputWriter;
import com.role.game.menu.interfaces.BaseCatalogue;

public class BaseCatalogueImpl<T> implements BaseCatalogue<T> {

	private final InputReader reader;

	private final OutputWriter writter;

	private final List<T> mainMenuItems;

	public BaseCatalogueImpl(InputReader reader, OutputWriter writter, List<T> mainMenuItems) {
		this.reader = reader;
		this.writter = writter;
		this.mainMenuItems = mainMenuItems;
	}

	@Override
	public void showMenu() {
		printOptions(mainMenuItems);
	}

	@Override
	public void printOptions(List<T> mainMenuItems) {
		writter.showMessage(OPTION_SELECT);
		Optional.ofNullable(mainMenuItems).ifPresent(menuItems -> {
			menuItems.forEach(value -> {
				String message = String.join("", TAB, value.toString());
				writter.showMessage(message);
			});
		});
	}

	@Override
	public void showMessage(String message) {
		Optional.ofNullable(message).ifPresent(msg -> writter.showMessage(msg));

	}

	@Override
	public T readUserChoice() {
		Integer userOption = null;
		try {
			userOption = reader.readIntegerValueFromConsole();
		} catch (Exception e) {
			writter.showMessage(NO_OPTION_SELECTED);
		}
		return userOption != null ? mainMenuItems.get(userOption - 1) : null;
	}

}
