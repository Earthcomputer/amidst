package amidst.mojangapi.world.oracle;

import amidst.documentation.Immutable;
import amidst.mojangapi.minecraftinterface.MinecraftInterface;

import java.util.Random;

@Immutable
public class SlimeChunkOracle_Bedrock extends SlimeChunkOracle {
    public SlimeChunkOracle_Bedrock(long seed) {
        super(seed);
    }

    @Override
    protected Random createRandom(long seed) {
        return MinecraftInterface.createBedrockRandom(seed);
    }

    @Override
    protected long getSeed(int chunkX, int chunkY) {
        return (chunkX * 522133279) ^ chunkY;
    }
}
