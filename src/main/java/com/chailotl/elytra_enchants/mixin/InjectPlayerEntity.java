package com.chailotl.elytra_enchants.mixin;

import com.chailotl.elytra_enchants.Main;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class InjectPlayerEntity extends LivingEntity
{
	protected InjectPlayerEntity(EntityType<? extends LivingEntity> entityType, World world)
	{
		super(entityType, world);
	}

	@Inject(
		method = "startFallFlying",
		at = @At("HEAD"))
	private void elytraBoost(CallbackInfo info)
	{
		int level = Main.getElytraEnchantmentLevel(this, Main.LAUNCH_ENCHANTMENT);

		if (level > 0)
		{
			Vec3d vec = getRotationVector().multiply(level * 0.1f);
			addVelocity(vec.x, vec.y + 0.25f, vec.z);
		}
	}
}