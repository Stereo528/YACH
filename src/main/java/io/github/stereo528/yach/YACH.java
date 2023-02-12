package io.github.stereo528.yach;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YACH implements ModInitializer {
	public static final String MODID = "yach";
	public static final Logger LOGGER = LoggerFactory.getLogger("YACH");

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Hello world from {}!", mod.metadata().name());
	}
}
