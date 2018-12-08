package amidst.mojangapi.world.oracle;

import amidst.mojangapi.world.biome.Biome;
import amidst.mojangapi.world.biome.UnknownBiomeIndexException;
import amidst.mojangapi.world.coordinates.CoordinatesInWorld;

import java.util.List;

public class BedrockWorldSpawnOracle implements WorldSpawnOracle {

    private final long seed;
    private final BiomeDataOracle biomeDataOracle;
    private final List<Biome> allowedBiomes;

    public BedrockWorldSpawnOracle(long seed, BiomeDataOracle biomeDataOracle, List<Biome> allowedBiomes) {
        this.seed = seed;
        this.biomeDataOracle = biomeDataOracle;
        this.allowedBiomes = allowedBiomes;
    }

    @Override
    public CoordinatesInWorld get() {
        int x = 0;
        CoordinatesInWorld spawn;
        do {
            spawn = findSpawnInSquare(x);
            x += 40;
        } while (spawn == null);
        return spawn;
    }

    private CoordinatesInWorld findSpawnInSquare(int startX) {
        short[][] biomes2d = new short[10][10];
        biomeDataOracle.populateArray(CoordinatesInWorld.from(startX, 0), biomes2d, true);
        Biome[] biomes = new Biome[10 * 10];
        for (int x = 0; x < 10; x++) {
            for (int z = 0; z < 10; z++) {
                try {
                    biomes[BiomeDataOracle.getBiomeDataIndex(x, z, 10)] = Biome.getByIndex(biomes2d[x][z]);
                } catch (UnknownBiomeIndexException ignore) {}
            }
        }
        for (int dz = 1; dz < 9; dz++) {
            for (int dx = 0; dx < 10; dx++) {
                if (allowedBiomes.contains(biomes[dz * 10 + dx])
                        && allowedBiomes.contains(biomes[(dz - 1) * 10 + dx])
                        && allowedBiomes.contains(biomes[(dz + 1) & 10 + dx])
                        && allowedBiomes.contains(biomes[dz * 10 + (dx - 1)])
                        && allowedBiomes.contains(biomes[dz * 10 + (dx + 1)])) {
                    return CoordinatesInWorld.from(startX + dx, dz);
                }
            }
        }

        return null;
    }
}
