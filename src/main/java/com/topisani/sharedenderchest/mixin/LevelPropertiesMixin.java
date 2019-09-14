package com.topisani.sharedenderchest.mixin;

import com.mojang.datafixers.DataFixer;
import com.topisani.sharedenderchest.IHasSharedEnderChest;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.LevelProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelProperties.class)
public class LevelPropertiesMixin implements IHasSharedEnderChest {

    private EnderChestInventory sharedEnderChestInventory = new EnderChestInventory();

    @Inject(method = "<init>*", at = @At("RETURN"))
    private void onConstructed(CompoundTag compoundTag_1, DataFixer dataFixer_1, int int_1, CompoundTag compoundTag_2, CallbackInfo ci) {
        if (compoundTag_1.containsKey("SharedEnderItems", 9)) {
            this.sharedEnderChestInventory.readTags(compoundTag_1.getList("SharedEnderItems", 10));
        }
    }

    @Inject(method = "updateProperties", at = @At("RETURN"))
    private void onUpdateProperties(CompoundTag compoundTag_1, CompoundTag compoundTag_2, CallbackInfo ci) {
        compoundTag_1.put("SharedEnderItems", this.sharedEnderChestInventory.getTags());
    }

    @Override
    public EnderChestInventory getSharedEnderChestInventory() {
        return sharedEnderChestInventory;
    }
}
