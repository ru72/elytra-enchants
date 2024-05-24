package com.chailotl.elytra_enchants.mixin;

import com.chailotl.elytra_enchants.Main;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class RedirectLivingEntity extends Entity
{
	private int ticksOnGround = 0;

	public RedirectLivingEntity(EntityType<?> type, World world)
	{
		super(type, world);
	}

	@Redirect(
		method = "travel",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/entity/LivingEntity;setFlag(IZ)V"
		))
	public void cancelElytraCancel(LivingEntity entity, int index, boolean value)
	{
		if (Main.getElytraEnchantmentLevel(entity, Main.SKIPPING_ENCHANTMENT) == 0)
		{
			setFlag(index, value);
		}
	}

	@Redirect(
		method = "tickFallFlying",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/entity/LivingEntity;setFlag(IZ)V"
		))
	public void initAi(LivingEntity entity, int index, boolean value)
	{
		if (Main.getElytraEnchantmentLevel(entity, Main.SKIPPING_ENCHANTMENT) == 0)
		{
			setFlag(index, value);
		}
		else if (entity.getVelocity().y == 0)
		{
			if (++ticksOnGround >= 4) { setFlag(7, value); Main.LOGGER.info("   4"); }
		}
	}

	@Inject(
		method = "tick",
		at = @At("TAIL")
	)
	public void resetTicksOnGround(CallbackInfo ci)
	{
		if (getVelocity().y != 0)
		{
			ticksOnGround = 0;
		}
	}
}
