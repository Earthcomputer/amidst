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
		/**
		 * Note: even if this check succeeds, the mineshaft may fail to generate if the
		 * central room isn't in a suitable location (for example, if it spawns inside
		 * a cave or a ravine). We can't check these cases, so we will have to accept
		 * some false positives.
		 */
		Random random = mersenneTwister ? MinecraftInterface.createBedrockRandom(seed) : new Random(seed);

		long var13 = chunkX * (mersenneTwister ? random.nextInt() : random.nextLong());
		long var15 = chunkY * (mersenneTwister ? random.nextInt() : random.nextLong());

		random.setSeed(var13 ^ var15 ^ seed);
		if(doExtraCheck())
			random.nextInt();

		if(!getResult(chunkX, chunkY, random))
			return false;
		return !doExtraCheck() || random.nextInt(80) < Math.max(Math.abs(chunkX), Math.abs(chunkY));
	}

	protected abstract boolean getResult(int chunkX, int chunkY, Random random);

	protected boolean doExtraCheck() {
		return true;
	}
}
