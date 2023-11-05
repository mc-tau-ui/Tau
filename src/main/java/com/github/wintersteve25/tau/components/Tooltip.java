package com.github.wintersteve25.tau.components;

import com.github.wintersteve25.tau.build.UIBuilder;
import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.components.base.PrimitiveUIComponent;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.theme.Theme;
import com.github.wintersteve25.tau.utils.Vector2i;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public final class Tooltip implements PrimitiveUIComponent {

    private final List<ClientTooltipComponent> components;
    private final List<FormattedText> texts;
    private final UIComponent child;
    
    public Tooltip(List<ClientTooltipComponent> components, List<FormattedText> texts, UIComponent child) {
        this.components = components;
        this.texts = texts;
        this.child = child;
    }

    @Override
    public Vector2i build(Layout layout, Theme theme, List<Renderable> renderables, List<Renderable> tooltips, List<DynamicUIComponent> dynamicUIComponents, List<GuiEventListener> eventListeners) {
        Minecraft minecraft = Minecraft.getInstance();
        Font fontRenderer = minecraft.font;
        Window window = minecraft.getWindow();
        int screenWidth = window.getGuiScaledWidth();
        int screenHeight = window.getGuiScaledHeight();
        
        Vector2i size = UIBuilder.build(layout, theme, child, renderables, tooltips, dynamicUIComponents, eventListeners);
        Vector2i position = layout.getPosition(size);
        AtomicReference<List<ClientTooltipComponent>> combined = new AtomicReference<>();
        
        tooltips.add((pPoseStack, pMouseX, pMouseY, pPartialTicks) -> {
            if (Vector2i.within(pMouseX, pMouseY, position.x, position.y, size.x, size.y)) {
                List<ClientTooltipComponent> components = ForgeHooksClient.gatherTooltipComponents(ItemStack.EMPTY, texts, pMouseX, screenWidth, screenHeight, fontRenderer, fontRenderer);
                
                if (combined.get() == null)
                    combined.set(new ArrayList<>(components.size() + this.components.size()));
                else
                    combined.get().clear();
                combined.get().addAll(components);
                combined.get().addAll(this.components);
                
                theme.drawTooltip(pPoseStack, pMouseX, pMouseY, screenWidth, screenHeight, fontRenderer, combined.get());
            } 
        });

        return size;
    }
    
    public static final class Builder {
        private final List<ClientTooltipComponent> components;
        private final List<FormattedText> texts;

        public Builder() {
            components = new ArrayList<>();
            texts = new ArrayList<>();
        }

        public Builder withText(List<FormattedText> text) {
            this.texts.addAll(text);
            return this;
        }
        
        public Builder withText(FormattedText text) {
            this.texts.add(text);
            return this;
        }
        
        public Builder withComponent(List<ClientTooltipComponent> components) {
            this.components.addAll(components);
            return this;
        }
        
        public Builder withComponent(ClientTooltipComponent component) {
            this.components.add(component);
            return this;
        }
        
        public Tooltip build(UIComponent child) {
            return new Tooltip(components, texts, child);
        }
    }
}
