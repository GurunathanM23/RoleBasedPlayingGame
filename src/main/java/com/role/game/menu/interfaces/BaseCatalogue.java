package com.role.game.menu.interfaces;

import java.util.List;

public interface BaseCatalogue<T> {

	void showMenu();

	void printOptions(List<T> values);

	void showMessage(String message);

	T readUserChoice();

}
