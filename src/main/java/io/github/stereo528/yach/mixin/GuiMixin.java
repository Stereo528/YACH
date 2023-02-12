package io.github.stereo528.yach.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.stereo528.yach.Config.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
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

	@Inject(method = "render", at = @At("TAIL"))
	public void renderCoordsOnHotbar(PoseStack poseStack, float timeDelta, CallbackInfo cir) {
		if(!client.options.renderDebug) {
			poseStack.pushPose();
			if(ModConfig.showOnHotbar) {
				assert client.player != null;
				String x = "X: " + (int) client.player.getX();
				String y = "Y: " + (int) client.player.getY();
				String z = "Z: " + (int) client.player.getZ();
				int xPlace = (int) (((this.screenWidth / 2) - (x.length() / 2.0)) - (91 - x.length()/2.0));
				int yPlace = (int) ((this.screenWidth / 2) - (y.length() / 2.0)) - (y.length()*2);
				int zPlace = (int) (((this.screenWidth / 2) - z.length()) + (90 - z.length()*4));
				client.font.drawShadow(poseStack, x, xPlace, this.screenHeight - 33, 16777215);
				client.font.drawShadow(poseStack, y, yPlace, this.screenHeight - 33, 16777215);
				client.font.drawShadow(poseStack, z, zPlace, this.screenHeight - 33, 16777215);
				poseStack.popPose();
			}
		}
	}
}
