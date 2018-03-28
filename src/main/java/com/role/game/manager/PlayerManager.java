package com.role.game.manager;

import static com.role.game.constants.RolePlayingGameConstants.ABOUT;
import static com.role.game.constants.RolePlayingGameConstants.GENDER;
import static com.role.game.constants.RolePlayingGameConstants.NAME_QUESTION;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.role.game.configuration.MapView;
import com.role.game.dto.MissionDetails;
import com.role.game.dto.PlayerDetails;
import com.role.game.io.reader.InputReader;
import com.role.game.io.writer.OutputWriter;
import com.role.game.util.DependencyInjector;

public class PlayerManager {

	public static final Logger log = LogManager.getLogger(PlayerManager.class);

	private static final OutputWriter writter = DependencyInjector.getObject(OutputWriter.class);

	private static final MapView mapView = DependencyInjector.getObject(MapView.class);

	String GREETING = ("Wow, %s") + ", you got very good story\n" + "%s\n" + "You will fit perfectly in the world of "
			+ ("%s") + "\n\n" + "Here is the Map of current mission!\n" + "%s\n" + "%s";

	private static final InputReader reader = DependencyInjector.getObject(InputReader.class);

	public String getName() {
		writter.showMessage(NAME_QUESTION);
		return reader.readStringValueFromConsole();
	}

	public String getGender() {
		writter.showMessage(GENDER);
		return reader.readStringValueFromConsole();
	}

	public String getDescription() {
		writter.showMessage(ABOUT);
		return reader.readStringValueFromConsole();
	}

	public void welcomePlayer(PlayerDetails details, MissionDetails mission) {
		writter.showMessage(String.format(GREETING, details.getName(), details.toString(), mission.getName(),
				mapView.buildMap(details, mission), mapView.showMapInfo()));

	}

}
