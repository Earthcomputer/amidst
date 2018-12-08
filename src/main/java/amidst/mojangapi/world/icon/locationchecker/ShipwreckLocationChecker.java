package amidst.mojangapi.world.icon.locationchecker;

import amidst.mojangapi.minecraftinterface.MinecraftInterfaceException;
import amidst.mojangapi.world.biome.Biome;
import amidst.mojangapi.world.biome.UnknownBiomeIndexException;
import amidst.mojangapi.world.coordinates.CoordinatesInWorld;
import amidst.mojangapi.world.oracle.BiomeDataOracle;

import java.util.List;

public class ShipwreckLocationChecker extends ScatteredFeaturesLocationChecker {

    private final BiomeDataOracle biomeDataOracle;
    private final boolean avoidMonuments;
    private final LocationChecker monumentLocationChecker;

    public ShipwreckLocationChecker(
            long seed,
            BiomeDataOracle biomeDataOracle,
            byte maxDistanceBetweenStructures,
            byte minDistanceBetweenStructures,
            List<Biome> validBiomesAtMiddleOfChunk,
            long magicNumber,
            boolean avoidMonuments,
            LocationChecker monumentLocationChecker,
            boolean useTwoValuesForUpdate,
            boolean buggyStructureCoordinateMath,
            boolean mersenneTwister) {
        super(
                seed,
                biomeDataOracle,
                maxDistanceBetweenStructures,
                minDistanceBetweenStructures,
                validBiomesAtMiddleOfChunk,
                magicNumber,
                useTwoValuesForUpdate,
                buggyStructureCoordinateMath,
                mersenneTwister);
        this.biomeDataOracle = biomeDataOracle;
        this.avoidMonuments = avoidMonuments;
        this.monumentLocationChecker = monumentLocationChecker;
    }

    @Override
    public boolean isValidLocation(int x, int y) {
        if (!super.isValidLocation(x, y))
            return false;

        if (!avoidMonuments)
            return true;

        for (int dx = x - 5; dx < x + 5; dx++) {
            for (int dy = y - 5; dy < y + 5; dy++) {
                if (monumentLocationChecker.isValidLocation(dx, dy))
                    return false;
            }
        }

        Biome biome;
        try {
            biome = biomeDataOracle.getBiomeAtMiddleOfChunk(x, y);
        } catch (MinecraftInterfaceException | UnknownBiomeIndexException e) {
            return true;
        }
        int radius = biome == Biome.beach || biome == Biome.coldBeach || biome == Biome.mushroomIslandShore ? 10 : 20;
        short[][] biomeArray = new short[(radius >> 1) + 1][(radius >> 1) + 1];
        biomeDataOracle.populateArray(CoordinatesInWorld.from((x << 4) + 8 - radius, (y << 4) + 8 - radius), biomeArray, true);
        short first = biomeArray[0][0];
        for (short[] aBiomeArray : biomeArray) {
            for (int dy = 0; dy < biomeArray.length; dy++) {
                if (aBiomeArray[dy] != first)
                    return false;
            }
        }

        return true;
    }
}
