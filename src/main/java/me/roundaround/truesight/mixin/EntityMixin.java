package me.roundaround.truesight.mixin;

import me.roundaround.truesight.Tracker;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
  @Shadow
  private World world;

  @Inject(method = "isInvisible", at = @At("HEAD"), cancellable = true)
  private void onIsInvisible(CallbackInfoReturnable<Boolean> info) {
    if (!world.isClient) {
      return;
    }

    if (Tracker.getInstance().isEnabled()) {
      info.setReturnValue(false);
    }
  }
}
