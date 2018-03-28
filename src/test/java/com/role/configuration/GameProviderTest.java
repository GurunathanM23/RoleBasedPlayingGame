package com.role.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import com.role.game.configuration.GameProvider;
import com.role.game.dto.MissionDetails;
import com.role.game.dto.MissionState;
import com.role.game.dto.PlayerDetails;
import com.role.game.io.reader.InputReader;
import com.role.game.io.writer.OutputWriter;

public class GameProviderTest {

	private OutputWriter writter;

	private InputReader reader;

	private GameProvider provider;

	private MissionDetails missionDetails;

	private PlayerDetails playerdetails;

	@Before
	public void setUp() {
		writter = mock(OutputWriter.class);
		reader = mock(InputReader.class);
		provider = new GameProvider(reader, writter);
		missionDetails = mock(MissionDetails.class);
		playerdetails = mock(PlayerDetails.class);
	}

	@Test
	public void loadSavedGameTest() throws IOException {
		when(reader.getObjectInputStream(Matchers.anyString()))
				.thenReturn(new ObjectInputStream(new FileInputStream(new File("src/test/resources/save_shadowfight.ser"))));
		MissionState state = provider.loadSavedGame();
		assertNotNull(state);
		assertEquals("guru", state.getPlayerDetails().getName());
	}

	@Test
	public void saveGame() throws IOException {
		when(writter.getObjectOutputStream(Matchers.anyString()))
				.thenReturn(new ObjectOutputStream(new FileOutputStream(new File("src/test/resources/test.ser"))));
		provider.saveGame(missionDetails, playerdetails);

	}

}
