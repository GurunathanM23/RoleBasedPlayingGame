package com.role.game.configuration;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.role.game.dto.PlayerDetails;
import com.role.game.io.writer.OutputWriter;

public class RealmConfigurationGenerator {

	private static final String realmName = "shadowfight.ser";

	public static void main(String[] args) {
		generateRealms();
	}

	public static void generateRealms() {
		RealmConfiguration realmConfiguration = shadowFightRealm();
		OutputWriter outputWriter = new OutputWriter();
		ObjectOutputStream outputStream = outputWriter.getObjectOutputStream(realmName);
		try {
			outputStream.writeObject(realmConfiguration);
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static RealmConfiguration shadowFightRealm() {
		return buildRealmConfiguration("Shadow fight", buildEnemy("Lynx", 100, 5, "Male"),
				buildEnemy("Hermit", 100, 5, "Female"), buildEnemy("Butcher", 100, 5, "Male"),
				buildEnemy("butler", 100, 10, "Male"), buildEnemy("bull", 100, 10, "Male"),
				buildEnemy("jack", 100, 15, "Male"), buildEnemy("killer", 100, 15, "Male"),
				buildEnemy("legend", 100, 20
						, "Male"));

	}

	public static PlayerDetails buildEnemy(String name, int health, int attackPower, String gender) {
		return new PlayerDetails(name, gender, "Be careful on your steps", health, attackPower);
	}

	public static RealmConfiguration buildRealmConfiguration(String name, PlayerDetails... definedEnemies) {
		List<PlayerDetails> enemies = new ArrayList<>();
		enemies.addAll(Arrays.asList(definedEnemies));
		return new RealmConfiguration(name, enemies.size(), enemies);
	}

}
