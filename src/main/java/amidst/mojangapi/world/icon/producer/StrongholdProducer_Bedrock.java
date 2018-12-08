package amidst.mojangapi.world.icon.producer;

import amidst.mojangapi.world.icon.locationchecker.VillageLocationChecker;

public class StrongholdProducer_Bedrock extends MultiProducer<Void> {

    public StrongholdProducer_Bedrock(long seed, VillageLocationChecker villageLocationChecker) {
        super(
                new StrongholdProducer_BedrockVillage(seed, villageLocationChecker),
                new StrongholdProducer_BedrockScattered(seed));
    }

}
