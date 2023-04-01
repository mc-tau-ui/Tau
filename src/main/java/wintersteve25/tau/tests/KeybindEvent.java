package wintersteve25.tau.tests;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;
import wintersteve25.tau.Tau;
import wintersteve25.tau.renderer.ScreenUIRenderer;

@Mod.EventBusSubscriber(modid = Tau.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class KeybindEvent {
    @SubscribeEvent
    public static void onKeyDown(InputEvent.KeyInputEvent evet) {
        if (evet.getKey() == GLFW.GLFW_KEY_G) {
            Minecraft.getInstance().setScreen(new ScreenUIRenderer(new DynamicWrapper()));
        }
    }
}
