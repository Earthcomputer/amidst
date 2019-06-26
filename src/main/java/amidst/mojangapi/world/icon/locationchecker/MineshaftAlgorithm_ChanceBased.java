package amidst.mojangapi.world.icon.locationchecker;

import java.util.Random;

import amidst.documentation.Immutable;

/**
 * As of v1.4.2 Minecraft switched to this version of the algorithm
 */
@Immutable
public class MineshaftAlgorithm_ChanceBased extends MineshaftAlgorithm_Base {
	private final double chancePerChunk;
	private final boolean extraCheck;

	public MineshaftAlgorithm_ChanceBased(long seed, double chancePerChunk, boolean extraCheck, boolean mersenneTwister) {
		super(seed, mersenneTwister);
		this.chancePerChunk = chancePerChunk;
		this.extraCheck = extraCheck;
	}

	@Override
	protected boolean getResult(int chunkX, int chunkY, Random random) {
		return random.nextDouble() < chancePerChunk;
	}

	@Override
	protected boolean doExtraCheck() {
		return extraCheck;
	}
}
