package io.github.stereo528.yach.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.stereo528.yach.Config.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.GameType;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@ClientOnly
@Mixin(Gui.class)
public class GuiMixin {
	private static final Minecraft client = Minecraft.getInstance();
	@Shadow private int screenHeight;
	@Shadow private int screenWidth;
	@Shadow private Minecraft minecraft;

	@Inject(method = "render", at = @At("TAIL"))
	public void renderCoordsOnHotbar(PoseStack poseStack, float timeDelta, CallbackInfo cir) {
		if(!client.options.renderDebug) {
			if (ModConfig.showOnHotbar) {
				assert client.gameMode != null;
				if(client.gameMode.getPlayerMode() == GameType.SPECTATOR && ModConfig.hideInSpectator) {
					poseStack.pushPose();
					poseStack.popPose();
				}
				else {
					if (client.gameMode.getPlayerMode() == GameType.CREATIVE || client.gameMode.getPlayerMode() == GameType.SPECTATOR && !ModConfig.hideInSpectator) {
						poseStack.pushPose();
						assert client.player != null;
						String x = "X: " + (int) client.player.getX();
						String y = "Y: " + (int) client.player.getY();
						String z = "Z: " + (int) client.player.getZ();
						client.font.drawShadow(poseStack, x, (int) (((this.screenWidth / 2) - (x.length() / 2.0)) - (91 - x.length() / 2.0)), this.screenHeight - 33, 16777215);
						client.font.drawShadow(poseStack, y, (int) ((this.screenWidth / 2) - (y.length() / 2.0)) - (y.length() * 2), this.screenHeight - 33, 16777215);
						client.font.drawShadow(poseStack, z, (((this.screenWidth / 2) - z.length()) + (90 - z.length() * 4)), this.screenHeight - 33, 16777215);
						poseStack.popPose();
					}
					else {
						int yOffset = 60;
						assert client.player != null;
						if (client.player.hasEffect(MobEffects.HEALTH_BOOST) || client.player.hasEffect(MobEffects.ABSORPTION)) yOffset = 65;
						poseStack.pushPose();
						assert client.player != null;
						String x = "X: " + (int) client.player.getX();
						String y = "Y: " + (int) client.player.getY();
						String z = "Z: " + (int) client.player.getZ();
						client.font.drawShadow(poseStack, x, (int) (((this.screenWidth / 2) - (x.length() / 2.0)) - (91 - x.length() / 2.0)), this.screenHeight - yOffset, 16777215);
						client.font.drawShadow(poseStack, y, (int) ((this.screenWidth / 2) - (y.length() / 2.0)) - (y.length() * 2), this.screenHeight - yOffset, 16777215);
						client.font.drawShadow(poseStack, z, (((this.screenWidth / 2) - z.length()) + (90 - z.length() * 4)), this.screenHeight - yOffset, 16777215);
						poseStack.popPose();
					}
				}
			}
		}
	}
}
