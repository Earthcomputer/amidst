package amidst.mojangapi.world.icon.locationchecker;

import java.util.Random;

import amidst.documentation.Immutable;
import amidst.mojangapi.minecraftinterface.MinecraftInterface;

@Immutable
public abstract class MineshaftAlgorithm_Base implements LocationChecker {
	private final long seed;
	private final boolean mersenneTwister;

	public MineshaftAlgorithm_Base(long seed, boolean mersenneTwister) {
		this.seed = seed;
		this.mersenneTwister = mersenneTwister;
	}

	@Override
	public boolean isValidLocation(int chunkX, int chunkY) {
		Random random = mersenneTwister ? MinecraftInterface.createBedrockRandom(seed) : new Random(seed);

		long var13 = (long) chunkX * random.nextLong();
		long var15 = (long) chunkY * random.nextLong();

		random.setSeed(var13 ^ var15 ^ seed);
		random.nextInt();

		return getResult(chunkX, chunkY, random) && random.nextInt(80) < Math.max(Math.abs(chunkX), Math.abs(chunkY));
	}

	protected abstract boolean getResult(int chunkX, int chunkY, Random random);
}
