package amidst.mojangapi.world.icon.locationchecker;

import java.util.Random;

import amidst.documentation.Immutable;
import amidst.mojangapi.minecraftinterface.MinecraftInterface;

@Immutable
public class StructureAlgorithm implements LocationChecker {
	private final long seed;
	private final long magicNumberForSeed1;
	private final long magicNumberForSeed2;
	private final long magicNumberForSeed3;
	protected final byte maxDistanceBetweenScatteredFeatures;
	private final int distanceBetweenScatteredFeaturesRange;
	private final boolean useTwoValuesForUpdate;
	private final boolean buggyStructureCoordinateMath;
	protected final boolean mersenneTwister;

	public StructureAlgorithm(
			long seed,
			long magicNumberForSeed1,
			long magicNumberForSeed2,
			long magicNumberForSeed3,
			byte maxDistanceBetweenScatteredFeatures,
			byte minDistanceBetweenScatteredFeatures,
			boolean useTwoValuesForUpdate,
			boolean mersenneTwister) {
		this(
				seed,
				magicNumberForSeed1,
				magicNumberForSeed2,
				magicNumberForSeed3,
				maxDistanceBetweenScatteredFeatures,
				minDistanceBetweenScatteredFeatures,
				useTwoValuesForUpdate,
				false,
				mersenneTwister);
	}

	public StructureAlgorithm(
			long seed,
			long magicNumberForSeed1,
			long magicNumberForSeed2,
			long magicNumberForSeed3,
			byte maxDistanceBetweenScatteredFeatures,
			byte minDistanceBetweenScatteredFeatures,
			boolean useTwoValuesForUpdate,
			boolean buggyStructureCoordinateMath,
			boolean mersenneTwister) {
		this.seed = seed;
		this.magicNumberForSeed1 = magicNumberForSeed1;
		this.magicNumberForSeed2 = magicNumberForSeed2;
		this.magicNumberForSeed3 = magicNumberForSeed3;
		this.maxDistanceBetweenScatteredFeatures = maxDistanceBetweenScatteredFeatures;
		this.distanceBetweenScatteredFeaturesRange = maxDistanceBetweenScatteredFeatures
				- minDistanceBetweenScatteredFeatures;
		this.useTwoValuesForUpdate = useTwoValuesForUpdate;
		this.buggyStructureCoordinateMath = buggyStructureCoordinateMath;
		this.mersenneTwister = mersenneTwister;
	}

	@Override
	public boolean isValidLocation(int x, int y) {
		int value1 = getInitialValue(x);
		int value2 = getInitialValue(y);
		Random random = mersenneTwister ? MinecraftInterface.createBedrockRandom(getSeed(value1, value2)) : new Random(getSeed(value1, value2));
		value1 = updateValue(random, value1);
		value2 = updateValue(random, value2);
		return x == value1 && y == value2;
	}

	protected final int getInitialValue(int coordinate) {
		return getModified(coordinate) / maxDistanceBetweenScatteredFeatures;
	}

	private int getModified(int coordinate) {
		if (coordinate < 0) {
			if (buggyStructureCoordinateMath) {
				// Bug MC-131462.
				return coordinate - maxDistanceBetweenScatteredFeatures - 1;
			} else {
				return coordinate - maxDistanceBetweenScatteredFeatures + 1;
			}
		} else {
			return coordinate;
		}
	}

	protected final long getSeed(int value1, int value2) {
		// @formatter:off
		return value1 * magicNumberForSeed1
		     + value2 * magicNumberForSeed2
		              + seed
		              + magicNumberForSeed3;
		// @formatter:on
	}

	protected final int updateValue(Random random, int value) {
		int result = value * maxDistanceBetweenScatteredFeatures;
		if (useTwoValuesForUpdate) {
			result += (random.nextInt(distanceBetweenScatteredFeaturesRange)
					+ random.nextInt(distanceBetweenScatteredFeaturesRange)) / 2;
		} else {
			result += random.nextInt(distanceBetweenScatteredFeaturesRange);
		}
		return result;
	}
}
