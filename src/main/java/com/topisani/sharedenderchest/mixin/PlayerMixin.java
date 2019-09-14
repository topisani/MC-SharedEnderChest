package com.topisani.sharedenderchest.mixin;

import com.topisani.sharedenderchest.IHasSharedEnderChest;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerMixin extends Entity {
    public PlayerMixin(EntityType<?> entityType_1, World world_1) {
        super(entityType_1, world_1);
    }

    @Inject(method = "getEnderChestInventory", at = @At("HEAD"), cancellable = true)
    public void onGetEnderChestInventory(CallbackInfoReturnable<EnderChestInventory> ci) {
        //System.out.println("Get shared enderChest");
        IHasSharedEnderChest hse = (IHasSharedEnderChest) this.world.getLevelProperties();
        ci.setReturnValue(hse.getSharedEnderChestInventory());
        ci.cancel();
    }
}
