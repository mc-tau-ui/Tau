package com.github.wintersteve25.tau.tests;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;
import com.github.wintersteve25.tau.Tau;
import com.github.wintersteve25.tau.renderer.ScreenUIRenderer;

@Mod.EventBusSubscriber(modid = Tau.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientEvents {
    @SubscribeEvent
    public static void onKeyDown(InputEvent.KeyInputEvent evet) {
        if (evet.getKey() == GLFW.GLFW_KEY_G) {
            Minecraft.getInstance().setScreen(new ScreenUIRenderer(new TestPositioned()));
        }
    }
}