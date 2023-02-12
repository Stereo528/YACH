package io.github.stereo528.yach;

import io.github.stereo528.yach.Config.ModConfig;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class YACHKeybinds {
	public static final KeyMapping TOGGLE = KeyBindingHelper.registerKeyBinding(new KeyMapping("key.yach.toggle", GLFW.GLFW_KEY_K, "key.yach.yach"));


	public static void init() {}
}
