package com.chailotl.elytra_enchants;

import net.fabricmc.api.ModInitializer;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer
{
	public static final String MOD_ID = "elytra_enchants";

	public static final Logger LOGGER = LoggerFactory.getLogger("elytra_enchants");

	public static final Enchantment SKIPPING_ENCHANTMENT;
	public static final Enchantment LAUNCH_ENCHANTMENT;

	static
	{
		SKIPPING_ENCHANTMENT = Registry.register(Registries.ENCHANTMENT, new Identifier(MOD_ID, "skipping"), new SkippingEnchantment());
		LAUNCH_ENCHANTMENT = Registry.register(Registries.ENCHANTMENT, new Identifier(MOD_ID, "launch"), new LaunchEnchantment());
	}

	@Override
	public void onInitialize()
	{
		//LOGGER.info("Hello Fabric world!");
	}
}