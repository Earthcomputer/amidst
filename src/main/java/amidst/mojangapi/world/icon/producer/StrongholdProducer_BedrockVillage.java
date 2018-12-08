package amidst.mojangapi.world.icon.producer;

import amidst.mojangapi.minecraftinterface.MinecraftInterface;
import amidst.mojangapi.world.biome.Biome;
import amidst.mojangapi.world.coordinates.CoordinatesInWorld;
import amidst.mojangapi.world.icon.WorldIcon;
import amidst.mojangapi.world.icon.locationchecker.VillageLocationChecker;
import amidst.mojangapi.world.oracle.BiomeDataOracle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StrongholdProducer_BedrockVillage extends CachedWorldIconProducer {

    private final long seed;
    private final VillageLocationChecker villageLocationChecker;

    public StrongholdProducer_BedrockVillage(long seed, VillageLocationChecker villageLocationChecker) {
        this.seed = seed;
        this.villageLocationChecker = villageLocationChecker;
    }

    @Override
    protected List<WorldIcon> doCreateCache() {
        List<WorldIcon> worldIcons = new ArrayList<>(3);

        Random rand = MinecraftInterface.createBedrockRandom(seed);
        float angle = rand.nextFloat() * (float) Math.PI * 2.0f;
        int radius = rand.nextInt(16) + 40;

        int count = 0;
        while (count < 3) {
            int cx = (int) Math.floor(radius * Math.cos(angle));
            int cz = (int) Math.floor(radius * Math.sin(angle));

            boolean placedStronghold = false;
            outerLoop:
            for (int offX = cx - 8; offX < cx + 8; offX++) {
                for (int offZ = cz - 8; offZ < cz + 8; offZ++) {
                    if (villageLocationChecker.isValidLocation(offX, offZ)) {
                        worldIcons.add(StrongholdProducer_Base.createWorldIcon(CoordinatesInWorld.from((offX << 4) + 8, (offZ << 4) + 8)));
                        count++;
                        placedStronghold = true;
                        break outerLoop;
                    }
                }
            }
            if (placedStronghold) {
                angle += 0.6f * (float) Math.PI;
                radius += 8;
            } else {
                angle += 0.25f * (float) Math.PI;
                radius += 4;
            }
        }

        return worldIcons;
    }
}
