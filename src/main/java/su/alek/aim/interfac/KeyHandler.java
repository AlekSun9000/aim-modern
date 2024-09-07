package su.alek.aim.interfac;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;

public final class KeyHandler {
    private KeyHandler(){}
    /**
     * @see org.lwjgl.glfw.GLFW
     * @param keyId GLFW Key ID, 340 for L_shift
     * @return is key pressed?
     */
    public static boolean keyPressed(int keyId){
        return InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), keyId);
    }
}
