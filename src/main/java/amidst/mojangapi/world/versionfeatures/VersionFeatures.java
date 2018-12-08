package amidst.mojangapi.world.versionfeatures;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import amidst.documentation.Immutable;
import amidst.mojangapi.world.biome.Biome;
import amidst.mojangapi.world.icon.locationchecker.LocationChecker;
import amidst.mojangapi.world.icon.locationchecker.MineshaftAlgorithm_Base;
import amidst.mojangapi.world.icon.locationchecker.StructureAlgorithm;
import amidst.mojangapi.world.icon.locationchecker.VillageLocationChecker;
import amidst.mojangapi.world.icon.producer.StrongholdProducer_Base;
import amidst.mojangapi.world.icon.producer.WorldIconProducer;
import amidst.mojangapi.world.oracle.BiomeDataOracle;
import amidst.mojangapi.world.oracle.SlimeChunkOracle;
import amidst.mojangapi.world.oracle.WorldSpawnOracle;

@Immutable
public class VersionFeatures {
	private final List<Integer> enabledLayers;
	private final List<Biome> validBiomesForStructure_Spawn;
	private final TriFunction<Long, BiomeDataOracle, List<Biome>, WorldSpawnOracle> worldSpawnAlgorithmFactory;
    private final Function<Long, SlimeChunkOracle> slimeChunkOracleFactory;
	private final List<Biome> validBiomesAtMiddleOfChunk_Stronghold;
	private final QuadFunction<Long, BiomeDataOracle, List<Biome>, VillageLocationChecker, WorldIconProducer<Void>> strongholdProducerFactory;
	private final Byte maxDistanceScatteredFeatures_Village;
	private final Byte minDistanceScatteredFeatures_Village;
	private final List<Biome> validBiomesForStructure_Village;
	private final Boolean doComplexVillageCheck;
	private final List<Biome> validBiomesAtMiddleOfChunk_DesertTemple;
	private final List<Biome> validBiomesAtMiddleOfChunk_Igloo;
	private final List<Biome> validBiomesAtMiddleOfChunk_JungleTemple;
	private final List<Biome> validBiomesAtMiddleOfChunk_WitchHut;
	private final List<Biome> validBiomesAtMiddleOfChunk_OceanRuins;
	private final List<Biome> validBiomesAtMiddleOfChunk_Shipwreck;
	private final QuadFunction<Long, Byte, Byte, Boolean, StructureAlgorithm> villageStructureAlgorithmFactory;
	private final BiFunction<Long, Boolean, MineshaftAlgorithm_Base> mineshaftAlgorithmFactory;
	private final Function5<Long, BiomeDataOracle, List<Biome>, List<Biome>, Boolean, LocationChecker> oceanMonumentLocationCheckerFactory;
	private final List<Biome> validBiomesAtMiddleOfChunk_OceanMonument;
	private final List<Biome> validBiomesForStructure_OceanMonument;
	private final List<Biome> validBiomesForStructure_WoodlandMansion;
	private final Long seedForStructure_DesertTemple;
	private final Long seedForStructure_Igloo;
	private final Long seedForStructure_JungleTemple;
	private final Long seedForStructure_WitchHut;
	private final Long seedForStructure_OceanRuins;
	private final Byte maxDistanceScatteredFeature_OceanRuins;
	private final Byte minDistanceScatteredFeature_OceanRuins;
	private final Boolean useTwoValuesForUpdate_OceanRuins;
	private final Long seedForStructure_Shipwreck;
	private final Byte maxDistanceScatteredFeatures_Shipwreck;
	private final Byte minDistanceScatteredFeatures_Shipwreck;
	private final Boolean buggyStructureCoordinateMath;
	private final Boolean mersenneTwister;

	public VersionFeatures(
			List<Integer> enabledLayers,
			List<Biome> validBiomesForStructure_Spawn,
			TriFunction<Long, BiomeDataOracle, List<Biome>, WorldSpawnOracle> worldSpawnAlgorithmFactory,
			Function<Long, SlimeChunkOracle> slimeChunkOracleFactory,
			List<Biome> validBiomesAtMiddleOfChunk_Stronghold,
			QuadFunction<Long, BiomeDataOracle, List<Biome>, VillageLocationChecker, WorldIconProducer<Void>> strongholdProducerFactory,
			Byte maxDistanceScatteredFeatures_Village,
			Byte minDistanceScatteredFeatures_Village,
			List<Biome> validBiomesForStructure_Village,
			Boolean doComplexVillageCheck,
			List<Biome> validBiomesAtMiddleOfChunk_DesertTemple,
			List<Biome> validBiomesAtMiddleOfChunk_Igloo,
			List<Biome> validBiomesAtMiddleOfChunk_JungleTemple,
			List<Biome> validBiomesAtMiddleOfChunk_WitchHut,
			List<Biome> validBiomesAtMiddleOfChunk_OceanRuins,
			List<Biome> validBiomesAtMiddleOfChunk_Shipwreck,
			QuadFunction<Long, Byte, Byte, Boolean, StructureAlgorithm> villageStructureAlgorithmFactory,
			BiFunction<Long, Boolean, MineshaftAlgorithm_Base> mineshaftAlgorithmFactory,
			Function5<Long, BiomeDataOracle, List<Biome>, List<Biome>, Boolean, LocationChecker> oceanMonumentLocationCheckerFactory,
			List<Biome> validBiomesAtMiddleOfChunk_OceanMonument,
			List<Biome> validBiomesForStructure_OceanMonument,
			List<Biome> validBiomesForStructure_WoodlandMansion,
			Long seedForStructure_DesertTemple,
			Long seedForStructure_Igloo,
			Long seedForStructure_JungleTemple,
			Long seedForStructure_WitchHut,
			Long seedForStructure_OceanRuins,
			Byte maxDistanceScatteredFeature_OceanRuins,
			Byte minDistanceScatteredFeatures_OceanRuins,
			Boolean useTwoValuesForUpdate_OceanRuins,
			Long seedForStructure_Shipwreck,
			Byte maxDistanceScatteredFeatures_Shipwreck,
			Byte minDistanceScatteredFeatures_Shipwreck,
			Boolean buggyStructureCoordinateMath,
			Boolean mersenneTwister) {
		this.enabledLayers = enabledLayers;
		this.validBiomesForStructure_Spawn = validBiomesForStructure_Spawn;
		this.worldSpawnAlgorithmFactory = worldSpawnAlgorithmFactory;
		this.slimeChunkOracleFactory = slimeChunkOracleFactory;
		this.validBiomesAtMiddleOfChunk_Stronghold = validBiomesAtMiddleOfChunk_Stronghold;
		this.strongholdProducerFactory = strongholdProducerFactory;
		this.maxDistanceScatteredFeatures_Village = maxDistanceScatteredFeatures_Village;
		this.minDistanceScatteredFeatures_Village = minDistanceScatteredFeatures_Village;
		this.validBiomesForStructure_Village = validBiomesForStructure_Village;
		this.doComplexVillageCheck = doComplexVillageCheck;
		this.validBiomesAtMiddleOfChunk_DesertTemple = validBiomesAtMiddleOfChunk_DesertTemple;
		this.validBiomesAtMiddleOfChunk_Igloo = validBiomesAtMiddleOfChunk_Igloo;
		this.validBiomesAtMiddleOfChunk_JungleTemple = validBiomesAtMiddleOfChunk_JungleTemple;
		this.validBiomesAtMiddleOfChunk_WitchHut = validBiomesAtMiddleOfChunk_WitchHut;
		this.validBiomesAtMiddleOfChunk_OceanRuins = validBiomesAtMiddleOfChunk_OceanRuins;
		this.validBiomesAtMiddleOfChunk_Shipwreck = validBiomesAtMiddleOfChunk_Shipwreck;
		this.villageStructureAlgorithmFactory = villageStructureAlgorithmFactory;
		this.mineshaftAlgorithmFactory = mineshaftAlgorithmFactory;
		this.oceanMonumentLocationCheckerFactory = oceanMonumentLocationCheckerFactory;
		this.validBiomesAtMiddleOfChunk_OceanMonument = validBiomesAtMiddleOfChunk_OceanMonument;
		this.validBiomesForStructure_OceanMonument = validBiomesForStructure_OceanMonument;
		this.validBiomesForStructure_WoodlandMansion = validBiomesForStructure_WoodlandMansion;
		this.seedForStructure_DesertTemple = seedForStructure_DesertTemple;
		this.seedForStructure_Igloo = seedForStructure_Igloo;
		this.seedForStructure_JungleTemple = seedForStructure_JungleTemple;
		this.seedForStructure_WitchHut = seedForStructure_WitchHut;
		this.seedForStructure_OceanRuins = seedForStructure_OceanRuins;
		this.maxDistanceScatteredFeature_OceanRuins = maxDistanceScatteredFeature_OceanRuins;
		this.minDistanceScatteredFeature_OceanRuins = minDistanceScatteredFeatures_OceanRuins;
		this.useTwoValuesForUpdate_OceanRuins = useTwoValuesForUpdate_OceanRuins;
		this.seedForStructure_Shipwreck = seedForStructure_Shipwreck;
		this.maxDistanceScatteredFeatures_Shipwreck = maxDistanceScatteredFeatures_Shipwreck;
		this.minDistanceScatteredFeatures_Shipwreck = minDistanceScatteredFeatures_Shipwreck;
		this.buggyStructureCoordinateMath = buggyStructureCoordinateMath;
		this.mersenneTwister = mersenneTwister;
	}

	public boolean hasLayer(int layerId) {
		return enabledLayers.contains(layerId);
	}

	public List<Biome> getValidBiomesForStructure_Spawn() {
		return validBiomesForStructure_Spawn;
	}

	public TriFunction<Long, BiomeDataOracle, List<Biome>, WorldSpawnOracle> getWorldSpawnAlgorithmFactory() {
		return worldSpawnAlgorithmFactory;
	}

    public Function<Long, SlimeChunkOracle> getSlimeChunkOracleFactory() {
        return slimeChunkOracleFactory;
    }

	public List<Biome> getValidBiomesAtMiddleOfChunk_Stronghold() {
		return validBiomesAtMiddleOfChunk_Stronghold;
	}

	public QuadFunction<Long, BiomeDataOracle, List<Biome>, VillageLocationChecker, WorldIconProducer<Void>> getStrongholdProducerFactory() {
		return strongholdProducerFactory;
	}

	public Byte getMaxDistanceScatteredFeatures_Village() {
		return maxDistanceScatteredFeatures_Village;
	}

	public Byte getMinDistanceScatteredFeatures_Village() {
		return minDistanceScatteredFeatures_Village;
	}

	public List<Biome> getValidBiomesForStructure_Village() {
		return validBiomesForStructure_Village;
	}
	
	public Boolean getDoComplexVillageCheck() {
		return doComplexVillageCheck;
	}

	public List<Biome> getValidBiomesAtMiddleOfChunk_DesertTemple() {
		return validBiomesAtMiddleOfChunk_DesertTemple;
	}

	public List<Biome> getValidBiomesAtMiddleOfChunk_Igloo() {
		return validBiomesAtMiddleOfChunk_Igloo;
	}

	public List<Biome> getValidBiomesAtMiddleOfChunk_JungleTemple() {
		return validBiomesAtMiddleOfChunk_JungleTemple;
	}

	public List<Biome> getValidBiomesAtMiddleOfChunk_WitchHut() {
		return validBiomesAtMiddleOfChunk_WitchHut;
	}
	
	public List<Biome> getValidBiomesAtMiddleOfChunk_OceanRuins() {
		return validBiomesAtMiddleOfChunk_OceanRuins;
	}
	
	public List<Biome> getValidBiomesAtMiddleOfChunk_Shipwreck() {
		return validBiomesAtMiddleOfChunk_Shipwreck;
	}

	public QuadFunction<Long, Byte, Byte, Boolean, StructureAlgorithm> getVillageStructureAlgorithmFactory() {
		return villageStructureAlgorithmFactory;
	}

	public BiFunction<Long, Boolean, MineshaftAlgorithm_Base> getMineshaftAlgorithmFactory() {
		return mineshaftAlgorithmFactory;
	}

	public Function5<Long, BiomeDataOracle, List<Biome>, List<Biome>, Boolean, LocationChecker> getOceanMonumentLocationCheckerFactory() {
		return oceanMonumentLocationCheckerFactory;
	}

	public List<Biome> getValidBiomesAtMiddleOfChunk_OceanMonument() {
		return validBiomesAtMiddleOfChunk_OceanMonument;
	}

	public List<Biome> getValidBiomesForStructure_OceanMonument() {
		return validBiomesForStructure_OceanMonument;
	}

	public List<Biome> getValidBiomesForStructure_WoodlandMansion() {
		return validBiomesForStructure_WoodlandMansion;
	}

	public Long getSeedForStructure_DesertTemple() {
		return seedForStructure_DesertTemple;
	}

	public Long getSeedForStructure_Igloo() {
		return seedForStructure_Igloo;
	}

	public Long getSeedForStructure_JungleTemple() {
		return seedForStructure_JungleTemple;
	}

	public Long getSeedForStructure_WitchHut() {
		return seedForStructure_WitchHut;
	}
	
	public Long getSeedForStructure_OceanRuins() {
		return seedForStructure_OceanRuins;
	}

	public Byte getMaxDistanceScatteredFeature_OceanRuins() {
		return maxDistanceScatteredFeature_OceanRuins;
	}

	public Byte getMinDistanceScatteredFeature_OceanRuins() {
		return minDistanceScatteredFeature_OceanRuins;
	}

	public Boolean getUseTwoValuesForUpdate_OceanRuins() {
		return useTwoValuesForUpdate_OceanRuins;
	}
	
	public Long getSeedForStructure_Shipwreck() {
		return seedForStructure_Shipwreck;
	}
	
	public Byte getMaxDistanceScatteredFeatures_Shipwreck() {
		return maxDistanceScatteredFeatures_Shipwreck;
	}

	public Byte getMinDistanceScatteredFeatures_Shipwreck() {
		return minDistanceScatteredFeatures_Shipwreck;
	}

	public Boolean getBuggyStructureCoordinateMath() {
		return buggyStructureCoordinateMath;
	}

	public Boolean getMersenneTwister() {
		return mersenneTwister;
	}
}
