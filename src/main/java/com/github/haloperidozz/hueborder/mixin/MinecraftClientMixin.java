package com.github.haloperidozz.hueborder.mixin;

import com.github.haloperidozz.hueborder.HueBorderAnimator;
import com.github.haloperidozz.hueborder.win32.version.WinVersion;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin({MinecraftClient.class})
public final class MinecraftClientMixin {
    @Shadow
    @Final
    private Window window;

    @Unique
    private HueBorderAnimator hueBorderAnimator = null;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        if (WinVersion.isWindows11Build22000OrGreater()) {
            hueBorderAnimator = new HueBorderAnimator(window);
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        if (hueBorderAnimator != null) {
            hueBorderAnimator.tick();
        }
    }
}
