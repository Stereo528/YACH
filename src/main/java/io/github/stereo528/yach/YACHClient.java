package io.github.stereo528.yach;

import eu.midnightdust.lib.config.MidnightConfig;
import io.github.stereo528.yach.Config.ModConfig;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

import static io.github.stereo528.yach.YACH.MODID;

public class YACHClient implements ClientModInitializer {

	@Override
	public void onInitializeClient(ModContainer mod) {
		MidnightConfig.init(MODID, ModConfig.class);
	}
}
