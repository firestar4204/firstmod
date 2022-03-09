package io.github.firestar4204.firstmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FirstMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("modid");

	public static final Item MUD_BALL = new Item(new Item.Settings().group(ItemGroup.MISC));

	public static final Block MUD_BLOCK = new Block(FabricBlockSettings.of(Material.SOIL).strength(5.0F, 5.0F).sounds(BlockSoundGroup.GRAVEL));
	
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		Registry.register(Registry.ITEM, new Identifier("firstmod", "mud_ball"), MUD_BALL);
		Registry.register(Registry.BLOCK, new Identifier("firstmod", "mud_block"), MUD_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("firstmod", "mud_block"), new BlockItem(MUD_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

		LOGGER.info("FirstMod initialized.");
	}
}
