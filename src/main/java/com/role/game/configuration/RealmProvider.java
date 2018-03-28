package com.role.game.configuration;

import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.role.game.io.reader.InputReader;
import com.role.game.util.DependencyInjector;

public class RealmProvider {

	private static final Logger log = LogManager.getLogger(RealmProvider.class);

	public static final String FILENAME = "shadowfight.ser";

	private static final InputReader reader = DependencyInjector.getObject(InputReader.class);

	public static RealmConfiguration loadRealms() {
		RealmConfiguration realmconfiguration = null;
		ObjectInputStream inputStream = reader.getObjectInputStream(FILENAME);
		try {
			realmconfiguration = (RealmConfiguration) inputStream.readObject();
		} catch (ClassNotFoundException e) {
			log.error("error occurred in fetching realms");
			realmconfiguration = RealmConfigurationGenerator.shadowFightRealm();
		} catch (FileNotFoundException e) {
			log.error("error occurred in fetching realms");
			realmconfiguration = RealmConfigurationGenerator.shadowFightRealm();
		} catch (Exception e) {
			log.error("error occurred in fetching realms");
			realmconfiguration = RealmConfigurationGenerator.shadowFightRealm();
		}

		return realmconfiguration;
	}

}
