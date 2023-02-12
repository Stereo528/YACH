package io.github.stereo528.yach;

import eu.midnightdust.lib.config.MidnightConfig;
import io.github.stereo528.yach.Config.ModConfig;
import net.minecraft.client.Minecraft;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.lifecycle.api.client.event.ClientTickEvents;

import static io.github.stereo528.yach.YACH.LOGGER;
import static io.github.stereo528.yach.YACH.MODID;

public class YACHClient implements ClientModInitializer {

	public void onClick(Minecraft mc) {
		while(YACHKeybinds.TOGGLE.consumeClick()) {
			ModConfig.showOnHotbar = !ModConfig.showOnHotbar;
		}
	}
	@Override
	public void onInitializeClient(ModContainer mod) {
		MidnightConfig.init(MODID, ModConfig.class);
		YACHKeybinds.init();
	ClientTickEvents.END.register(this::onClick);
	}
}
