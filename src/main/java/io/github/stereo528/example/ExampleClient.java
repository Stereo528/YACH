package io.github.stereo528.example;

import eu.midnightdust.lib.config.MidnightConfig;
import io.github.stereo528.example.Config.ModConfig;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

import static io.github.stereo528.example.Example.MODID;

public class ExampleClient implements ClientModInitializer {

	@Override
	public void onInitializeClient(ModContainer mod) {
		MidnightConfig.init(MODID, ModConfig.class);
	}
}
