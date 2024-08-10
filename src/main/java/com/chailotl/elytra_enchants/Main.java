package com.chailotl.elytra_enchants;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.component.ComponentType;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.effect.EnchantmentValueEffect;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer
{
	public static final String MOD_ID = "elytra_enchants";

	public static final Logger LOGGER = LoggerFactory.getLogger("elytra_enchants");

	public static final ComponentType<Unit> ELYTRA_BOUNCE_OFF_FLOOR;
	public static final ComponentType<EnchantmentValueEffect> ELYTRA_LAUNCH_STRENGTH;

	static
	{
		ELYTRA_BOUNCE_OFF_FLOOR = Registry.register(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, Identifier.of(MOD_ID, "elytra_bounce_off_floor"), ComponentType.<Unit>builder().codec(Unit.CODEC).build());
		ELYTRA_LAUNCH_STRENGTH = Registry.register(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, Identifier.of(MOD_ID, "elytra_launch_strength"), ComponentType.<EnchantmentValueEffect>builder().codec(EnchantmentValueEffect.CODEC).build());
	}

	@Override
	public void onInitialize()
	{
		//LOGGER.info("Hello Fabric world!");
	}

	public static float getElytraLaunchStrength(LivingEntity entity) {
		MutableFloat mutableFloat = new MutableFloat(0.0F);

		// Check chest slot
		EnchantmentHelper.forEachEnchantment(entity.getEquippedStack(EquipmentSlot.CHEST), (enchantment, level) -> enchantment.value().modifyValue(ELYTRA_LAUNCH_STRENGTH, entity.getRandom(), level, mutableFloat));

		// Trinkets compat
		if (FabricLoader.getInstance().isModLoaded("trinkets"))
		{
			float strength = Trinkets.getElytraLaunchStrength(entity);

			if (strength > mutableFloat.floatValue())
			{
				return strength;
			}
		}

		return mutableFloat.floatValue();
	}

	public static boolean isElytraBounceOffFloor(LivingEntity entity) {
		// Check chest slot
		if (EnchantmentHelper.hasAnyEnchantmentsWith(entity.getEquippedStack(EquipmentSlot.CHEST), Main.ELYTRA_BOUNCE_OFF_FLOOR))
		{
			return true;
		}

		// Trinkets compat
		if (FabricLoader.getInstance().isModLoaded("trinkets"))
		{
			return Trinkets.isElytraBounceOffFloor(entity);
		}

		return false;
	}
}