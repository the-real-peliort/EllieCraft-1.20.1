package net.ellieraven.elliecraft.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class ModKeybinds {
    public static final String CATEGORY = "key.elliecraft.elliecraft";

    public static final KeyMapping END_BLADE_KEY = new KeyMapping(
            "key.elliecraft.end_blade_blink_ability",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
            CATEGORY
    );
}
