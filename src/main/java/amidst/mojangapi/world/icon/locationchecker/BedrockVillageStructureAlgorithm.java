package amidst.mojangapi.world.icon.locationchecker;

import amidst.mojangapi.minecraftinterface.MinecraftInterface;

import java.util.Random;

public class BedrockVillageStructureAlgorithm extends StructureAlgorithm {

    public BedrockVillageStructureAlgorithm(
            long seed,
            long magicNumberForSeed1,
            long magicNumberForSeed2,
            long magicNumberForSeed3,
            byte maxDistanceBetweenScatteredFeatures,
            byte minDistanceBetweenScatteredFeatures,
            boolean useTwoValuesForUpdate,
            boolean mersenneTwister) {
        super(
                seed,
                magicNumberForSeed1,
                magicNumberForSeed2,
                magicNumberForSeed3,
                maxDistanceBetweenScatteredFeatures,
                minDistanceBetweenScatteredFeatures,
                useTwoValuesForUpdate,
                mersenneTwister);
    }

    @Override
    public boolean isValidLocation(int x, int y) {
        int movedX = x;
        int movedY = y;
        if (x < 0)
            movedX -= maxDistanceBetweenScatteredFeatures - 1;
        if (y < 0)
            movedY -= maxDistanceBetweenScatteredFeatures - 1;
        int value1 = getInitialValue(x);
        int value2 = getInitialValue(y);
        Random random = mersenneTwister ? MinecraftInterface.createBedrockRandom(getSeed(movedX, movedY)) : new Random(getSeed(movedX, movedY));
        value1 = updateValue(random, value1);
        value2 = updateValue(random, value2);
        return x == value1 && y == value2;
    }
}
