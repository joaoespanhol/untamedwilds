package untamedwilds.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import untamedwilds.entity.ComplexMob;
import untamedwilds.world.FaunaHandler;
import untamedwilds.world.FaunaSpawn;

import java.util.Random;

public class FeatureCritters extends Feature<NoFeatureConfig> {

    public FeatureCritters(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        pos = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, pos.add(8, 0, 8));
        Biome biome = world.getBiome(pos);
        EntityType<? extends ComplexMob> type;
        int groupSize = 1;
        type = (EntityType<? extends ComplexMob>) WeightedRandom.getRandomItem(rand, FaunaHandler.getSpawnableList(FaunaHandler.animalType.CRITTER)).entityType;

        int diff = Math.abs(pos.getY() - world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, pos).getY());
        if (diff >= 10) {
            FaunaSpawn.performWorldGenSpawning(type, EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, world, world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, pos).up(4), rand, groupSize);
            return true;
        }
        FaunaSpawn.performWorldGenSpawning(type, EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, world, pos, rand, groupSize);
        return true;
    }
}
