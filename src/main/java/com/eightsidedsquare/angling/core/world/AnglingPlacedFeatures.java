package com.eightsidedsquare.angling.core.world;

import com.eightsidedsquare.angling.core.tags.AnglingBiomeTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

import static com.eightsidedsquare.angling.core.AnglingMod.MOD_ID;

public class AnglingPlacedFeatures {
    public static final RegistryKey<PlacedFeature> PATCH_DUCKWEED = register("patch_duckweed",
            AnglingConfiguredFeatures.PATCH_DUCKWEED,
            List.of(RarityFilterPlacementModifier.of(3),
                    SquarePlacementModifier.of(),
                    PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                    BiomePlacementModifier.of()));

    public static final RegistryKey<PlacedFeature> PATCH_SARGASSUM = register("patch_sargassum",
            AnglingConfiguredFeatures.PATCH_SARGASSUM,
            List.of(RarityFilterPlacementModifier.of(70),
                    SquarePlacementModifier.of(),
                    PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                    BiomePlacementModifier.of()));

    public static final RegistryKey<PlacedFeature> OYSTER_REEF = register("oyster_reef",
                    AnglingConfiguredFeatures.OYSTER_REEF,
            List.of(RarityFilterPlacementModifier.of(14),
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                    BiomePlacementModifier.of()));

    public static final RegistryKey<PlacedFeature> CLAMS = register("clams",
            AnglingConfiguredFeatures.CLAMS,
            List.of(RarityFilterPlacementModifier.of(12),
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                    BiomePlacementModifier.of()));

    public static final RegistryKey<PlacedFeature> WORMY_BLOCK = register("wormy_block",
            AnglingConfiguredFeatures.WORMY_BLOCK,
            List.of(CountPlacementModifier.of(2),
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                    BiomePlacementModifier.of()));

    public static final RegistryKey<PlacedFeature> PATCH_PAPYRUS = register("patch_papyrus",
            AnglingConfiguredFeatures.PATCH_PAPYRUS,
            List.of(CountPlacementModifier.of(2),
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                    BiomePlacementModifier.of()));

    public static RegistryKey<PlacedFeature> register(String id, RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry, List<PlacementModifier> modifiers) {
        PlacedFeatures.register(MOD_ID + ":" + id, registryEntry, modifiers);
        return PlacedFeatures.of(id);
    }

    private static void addFeature(RegistryKey<PlacedFeature> key, GenerationStep.Feature step, TagKey<Biome> tag) {
        BiomeModifications.addFeature(ctx -> ctx.getBiomeRegistryEntry().isIn(tag), step, key);
    }

    public static void init() {
        addFeature(OYSTER_REEF, GenerationStep.Feature.VEGETAL_DECORATION, AnglingBiomeTags.OYSTER_REEF_BIOMES);
        addFeature(CLAMS, GenerationStep.Feature.VEGETAL_DECORATION, AnglingBiomeTags.CLAMS_BIOMES);
        addFeature(PATCH_DUCKWEED, GenerationStep.Feature.VEGETAL_DECORATION, AnglingBiomeTags.DUCKWEED_BIOMES);
        addFeature(PATCH_SARGASSUM, GenerationStep.Feature.VEGETAL_DECORATION, AnglingBiomeTags.SARGASSUM_BIOMES);
        addFeature(PATCH_PAPYRUS, GenerationStep.Feature.VEGETAL_DECORATION, AnglingBiomeTags.PAPYRUS_BIOMES);
        addFeature(WORMY_BLOCK, GenerationStep.Feature.UNDERGROUND_ORES, BiomeTags.IS_OVERWORLD);
    }
}
