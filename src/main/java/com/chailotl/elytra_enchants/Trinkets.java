package com.chailotl.elytra_enchants;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Pair;

public class Trinkets
{
	public static int getElytraEnchantmentLevel(LivingEntity entity, Enchantment enchantment)
	{
		int maxLevel = 0;

		TrinketComponent comp = TrinketsApi.getTrinketComponent(entity).get();

		for (Pair<SlotReference, ItemStack> pair: comp.getEquipped(Items.ELYTRA))
		{
			int level = EnchantmentHelper.getLevel(enchantment, pair.getRight());

			if (level > maxLevel)
			{
				maxLevel = level;
			}
		}

		return maxLevel;
	}
}