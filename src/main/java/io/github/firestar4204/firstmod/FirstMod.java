package io.github.firestar4204.firstmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;

public class FirstMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger FIRSTMOD = LogManager.getLogger("modid");

	public static final Item MUD_BALL = new Item(new Item.Settings().group(ItemGroup.MISC));

	public static final Block MUD_BLOCK = new Block(FabricBlockSettings.of(Material.SOIL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.GRAVEL));
	
	public static final Item DRIED_MUD_BALL = new Item(new Item.Settings().group(ItemGroup.FOOD).food(MudFoodComponents.DRIED_MUD_BALL));

	public static final Block MUD_ORE = new MudOreBlock(FabricBlockSettings.copy(Blocks.STONE));

	private static ConfiguredFeature<?, ?> ORE_MUD_OVERWORLD = Feature.ORE
    		.configure(new OreFeatureConfig(
      			OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
      			FirstMod.MUD_ORE.getDefaultState(),
      			9)) // Vein size
    		.range(new RangeDecoratorConfig(
      		// You can also use one of the other height providers if you don't want a uniform distribution
      			UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(64)))) // Inclusive min and max height
    		.spreadHorizontally()
    		.repeat(20);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		Registry.register(Registry.ITEM, new Identifier("firstmod", "mud_ball"), MUD_BALL);
		Registry.register(Registry.BLOCK, new Identifier("firstmod", "mud_block"), MUD_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("firstmod", "mud_block"), new BlockItem(MUD_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, new Identifier("firstmod", "dried_mud_ball"), DRIED_MUD_BALL);
		Registry.register(Registry.BLOCK, new Identifier("firstmod", "mud_ore"), MUD_ORE);
		Registry.register(Registry.ITEM, new Identifier("firstmod", "mud_ore"), new BlockItem(MUD_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

		RegistryKey<ConfiguredFeature<?, ?>> oreWoolOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier("tutorial", "ore_wool_overworld"));
    		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreWoolOverworld.getValue(), ORE_MUD_OVERWORLD);
    		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreWoolOverworld);
		FIRSTMOD.info("FirstMod initialized.  Prepare to mud the world.");
	}
}
