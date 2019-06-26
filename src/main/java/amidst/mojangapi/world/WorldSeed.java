package amidst.mojangapi.world;

import java.util.Random;

import amidst.documentation.Immutable;

@Immutable
public class WorldSeed {
	public static enum WorldSeedType {
		// @formatter:off
		TEXT           ("Text Seed"),
		NUMERIC     ("Numeric Seed"),
		SAVE_GAME ("Save Game Seed"),
		RANDOM       ("Random Seed");
		// @formatter:on

		private final String labelPrefix;

		private WorldSeedType(String labelPrefix) {
			this.labelPrefix = labelPrefix;
		}

		private String getLabel(long seed, String text) {
			if (this == TEXT) {
				return labelPrefix + ": '" + text + "' (" + seed + ")";
			} else {
				return labelPrefix + ": " + seed;
			}
		}
	}

	public static WorldSeed random() {
		// BEDROCK: integer seed
		return new WorldSeed(new Random().nextInt(), null, WorldSeedType.RANDOM);
	}

	public static WorldSeed fromUserInput(String input) {
		if (input.isEmpty()) {
			return random();
		}
		try {
			int seed = Integer.parseInt(input); // BEDROCK: integer seed
			return new WorldSeed(seed, null, WorldSeedType.NUMERIC);
		} catch (NumberFormatException err) {
			int seed = input.hashCode();
			return new WorldSeed(seed, input, WorldSeedType.TEXT);
		}
	}

	public static WorldSeed fromSaveGame(long seed) {
		return new WorldSeed((int) seed, null, WorldSeedType.SAVE_GAME);
	}

	private final int seed; // BEDROCK: integer seed
	private final String text;
	private final WorldSeedType type;
	private final String label;

	private WorldSeed(int seed, String text, WorldSeedType type) {
		this.seed = seed;
		this.text = text;
		this.type = type;
		this.label = type.getLabel(seed, text);
	}

	public long getLong() {
		return Integer.toUnsignedLong(seed);
	}

	public String getText() {
		return text;
	}

	public WorldSeedType getType() {
		return type;
	}

	public String getLabel() {
		return label;
	}
}
