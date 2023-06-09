package io.github.stereo528.yach.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.stereo528.yach.Config.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@ClientOnly
@Mixin(Gui.class)
public abstract class GuiMixin {
	private static final Minecraft client = Minecraft.getInstance();
	@Shadow private int screenHeight;
	@Shadow private int screenWidth;
	@Final
	@Shadow private Minecraft minecraft;

	@Shadow private int toolHighlightTimer;
	@Shadow private ItemStack lastToolHighlight;

	@Shadow
	public abstract Font getFont();

	@Inject(method = "render", at = @At("TAIL"))
	public void renderCoordsOnHotbar(GuiGraphics guiGraphics, float f, CallbackInfo ci) {
		if(!client.options.renderDebug) {
			if (ModConfig.showOnHotbar) {
				assert client.gameMode != null;
				if(client.gameMode.getPlayerMode() == GameType.SPECTATOR && ModConfig.hideInSpectator) {
					guiGraphics.pose().pushPose();
					guiGraphics.pose().popPose();
				}
				else {
					assert client.player != null;
					boolean healthCheck = client.player.hasEffect(MobEffects.HEALTH_BOOST) || client.player.hasEffect(MobEffects.ABSORPTION);
					int yOffset;
					if (client.gameMode.getPlayerMode() == GameType.CREATIVE || client.gameMode.getPlayerMode() == GameType.SPECTATOR && !ModConfig.hideInSpectator) {
						yOffset = 33;
					} else {
						yOffset = 60;
						if (healthCheck) {
//							if (client.player.getArmorValue() > 0) yOffset += 5;
							yOffset += 10;
						}
						if (this.toolHighlightTimer > 0 && !this.lastToolHighlight.isEmpty()) {
							if(!healthCheck) yOffset += 10;
						}
					}


					guiGraphics.pose().pushPose();
					String x = "X: " + (int) client.player.getX();
					String y = "Y: " + (int) client.player.getY();
					String z = "Z: " + (int) client.player.getZ();
					guiGraphics.drawString(this.getFont(), x, (int) (((this.screenWidth / 2) - (x.length() / 2.0)) - (91 - x.length() / 2.0)), this.screenHeight - yOffset, 16777215);
					guiGraphics.drawString(this.getFont(), y, (int) ((this.screenWidth / 2) - (y.length() / 2.0)) - (y.length() * 2), this.screenHeight - yOffset, 16777215);
					guiGraphics.drawString(this.getFont(), z, (((this.screenWidth / 2) - z.length()) + (90 - z.length() * 4)), this.screenHeight - yOffset, 16777215);
					guiGraphics.pose().popPose();
				}
			}
		}
	}
}
