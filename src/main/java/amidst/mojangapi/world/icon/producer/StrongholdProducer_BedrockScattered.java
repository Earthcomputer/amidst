package amidst.mojangapi.world.icon.producer;

import amidst.mojangapi.minecraftinterface.MinecraftInterface;
import amidst.mojangapi.world.Dimension;
import amidst.mojangapi.world.coordinates.Resolution;
import amidst.mojangapi.world.icon.type.DefaultWorldIconTypes;

import java.util.Random;

public class StrongholdProducer_BedrockScattered extends StructureProducer<Void> {

    public StrongholdProducer_BedrockScattered(long seed) {
        super(Resolution.CHUNK, 8, (x, y) -> {
            if (x * x + y * y < 10 * 10)
                return false;

            int value1 = x;
            int value2 = y;
            if (x < 0)
                value1 -= 200 - 1;
            if (y < 0)
                value2 -= 200 - 1;
            value1 /= 200;
            value2 /= 200;
            Random random = MinecraftInterface.createBedrockRandom(784295783249L * value1 + 827828252345L * value2 + seed + 97858791);
            value1 *= 200;
            value2 *= 200;
            value1 += 50 + random.nextInt(100);
            value2 += 50 + random.nextInt(100);
            return x == value1 && y == value2 && random.nextFloat() < 0.25;
        }, (x, y, nuffink) -> DefaultWorldIconTypes.STRONGHOLD, Dimension.OVERWORLD, false);
    }
}
