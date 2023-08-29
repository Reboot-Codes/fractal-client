package com.rebootcodes.fractalclient.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class SettingsButton extends Screen {
    @Unique
    private static final MinecraftClient CLIENT = MinecraftClient.getInstance();

    protected SettingsButton(Text title) {
        super(title);
    }

    @Inject(at = @At("TAIL"), method = "initWidgetsNormal")
    private void addLauncherButton(int y, int spacingY, CallbackInfo ci) {
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Fractal"), (button) -> {
            assert this.client != null;
            this.client.setScreen(new OptionsScreen(this, CLIENT.options));
        }).dimensions(this.width / 2 - 100, y - 25, 200, 20).build());
    }
}
