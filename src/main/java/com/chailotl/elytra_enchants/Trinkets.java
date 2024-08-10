package com.chailotl.elytra_enchants;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Pair;
import org.apache.commons.lang3.mutable.MutableFloat;

public class Trinkets
{
	public static float getElytraLaunchStrength(LivingEntity entity)
	{
		float strength = 0;

		TrinketComponent comp = TrinketsApi.getTrinketComponent(entity).get();

		for (Pair<SlotReference, ItemStack> pair: comp.getEquipped(Items.ELYTRA))
		{
			MutableFloat mutableFloat = new MutableFloat(0.0F);

			EnchantmentHelper.forEachEnchantment(pair.getRight(), (enchantment, level) -> enchantment.value().modifyValue(Main.ELYTRA_LAUNCH_STRENGTH, entity.getRandom(), level, mutableFloat));

			if (mutableFloat.floatValue() > strength)
			{
				strength = mutableFloat.floatValue();
			}
		}

		return strength;
	}

	public static boolean isElytraBounceOffFloor(LivingEntity entity)
	{
		TrinketComponent comp = TrinketsApi.getTrinketComponent(entity).get();

		for (Pair<SlotReference, ItemStack> pair: comp.getEquipped(Items.ELYTRA))
		{
			if (EnchantmentHelper.hasAnyEnchantmentsWith(pair.getRight(), Main.ELYTRA_BOUNCE_OFF_FLOOR))
			{
				return true;
			}
		}

		return false;
	}
}