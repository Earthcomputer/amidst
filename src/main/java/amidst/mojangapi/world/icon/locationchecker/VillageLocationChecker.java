package amidst.mojangapi.world.icon.locationchecker;

import java.util.List;

import amidst.documentation.ThreadSafe;
import amidst.mojangapi.world.biome.Biome;
import amidst.mojangapi.world.oracle.BiomeDataOracle;

@ThreadSafe
public class VillageLocationChecker extends AllValidLocationChecker {
	private static final long MAGIC_NUMBER_FOR_SEED_1 = 341873128712L;
	private static final long MAGIC_NUMBER_FOR_SEED_2 = 132897987541L;
	private static final long MAGIC_NUMBER_FOR_SEED_3 = 10387312L;
	private static final byte MAX_DISTANCE_BETWEEN_SCATTERED_FEATURES = 32;
	private static final byte MIN_DISTANCE_BETWEEN_SCATTERED_FEATURES = 8;
	private static final boolean USE_TWO_VALUES_FOR_UPDATE = false;
	private static final int STRUCTURE_SIZE = 0;

	public VillageLocationChecker(
			BiomeDataOracle biomeDataOracle,
			LocationChecker base,
			List<Biome> validBiomesForStructure,
			boolean doComplexVillageCheck) {
		super(getLocationCheckers(
				biomeDataOracle,
				base,
				validBiomesForStructure,
				doComplexVillageCheck));
	}
	
	private static LocationChecker[] getLocationCheckers(
			BiomeDataOracle biomeDataOracle,
			LocationChecker base,
			List<Biome> validBiomesForStructure,
			boolean doComplexVillageCheck) {
		LocationChecker biome = new StructureBiomeLocationChecker(biomeDataOracle, STRUCTURE_SIZE, validBiomesForStructure);

		if(doComplexVillageCheck) {
			return new LocationChecker[] { base, biome, new VillageAlgorithm(biomeDataOracle, validBiomesForStructure) };
		} else {
			return new LocationChecker[] { base, biome };
		}
	}

}
