package com.role.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.role.game.configuration.RealmConfigurationGenerator;
import com.role.game.dto.PlayerDetails;

public class RealmConfigurationGeneratorTest {

	@Test
	public void testShadowFightRealm() {
		assertNotNull(RealmConfigurationGenerator.shadowFightRealm());
	}

	@Test
	public void testbuildEnemy() {
		PlayerDetails details = RealmConfigurationGenerator.buildEnemy("guru", 100, 10, "male");
		assertNotNull(details);
		assertEquals("guru", details.getName());
	}

	@Test
	public void testbuildEnemyNegative() {
		PlayerDetails details = RealmConfigurationGenerator.buildEnemy(null, 0, 0, null);
		assertNotNull(details);
		assertNotEquals("guru", details.getName());
	}

	@Test
	public void testbuildRealmConfiguration() {
		assertNotNull(RealmConfigurationGenerator.buildRealmConfiguration("GTA", getPlayerDetails()));
	}

	public PlayerDetails getPlayerDetails() {
		return new PlayerDetails("guru", "male", "good", 100, 10);
	}
}
