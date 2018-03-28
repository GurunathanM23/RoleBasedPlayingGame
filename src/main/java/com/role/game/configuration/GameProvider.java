package com.role.game.configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.role.game.dto.MissionDetails;
import com.role.game.dto.MissionState;
import com.role.game.dto.PlayerDetails;
import com.role.game.io.reader.InputReader;
import com.role.game.io.writer.OutputWriter;

public class GameProvider {

	private static final Logger log = LogManager.getLogger(RealmProvider.class);

	public static final String savedGameName = "save_shadowfight.ser";

	public InputReader reader;
	public OutputWriter writter;

	public GameProvider(InputReader reader, OutputWriter writter) {
		this.reader = reader;
		this.writter = writter;
	}

	public MissionState loadSavedGame() {
		ObjectInputStream inputStream = reader.getObjectInputStream(savedGameName);
		MissionState state = null;
		try {
			state = (MissionState) inputStream.readObject();
		} catch (ClassNotFoundException e) {
			log.error("error occurred in fetching savedGame");
		} catch (FileNotFoundException e) {
			log.error("error occurred in fetching savedGame");
		} catch (IOException e) {
			log.error("error occurred in fetching savedGame");
		}

		return state;
	}

	public void saveGame(MissionDetails mission, PlayerDetails player) {
		MissionState state = new MissionState(mission, player);
		ObjectOutputStream outputStream = writter.getObjectOutputStream(savedGameName);
		try {
			outputStream.writeObject(state);
		} catch (IOException e) {
			log.error("error occurred in saving game");
		}
	}
}
