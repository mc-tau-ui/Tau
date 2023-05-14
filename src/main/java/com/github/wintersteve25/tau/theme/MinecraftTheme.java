package com.github.wintersteve25.tau.theme;

import com.github.wintersteve25.tau.Tau;
import com.github.wintersteve25.tau.utils.Color;
import com.github.wintersteve25.tau.utils.InteractableState;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.TooltipRenderUtil;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.client.gui.ScreenUtils;
import org.joml.Matrix4f;

import java.util.List;

public class MinecraftTheme implements Theme {
    public static final MinecraftTheme INSTANCE = new MinecraftTheme();

    private static final ResourceLocation TEXTURE = new ResourceLocation(Tau.MOD_ID, "textures/gui/container.png");
    private static final Color TEXT = new Color(0xFFE8E8E8);
    private static final Color TOOLTIP = new Color(ScreenUtils.DEFAULT_BACKGROUND_COLOR);
    private static final Color TOOLTIP_BORDER_START = new Color(ScreenUtils.DEFAULT_BORDER_COLOR_START);
    private static final Color TOOLTIP_BORDER_END = new Color(ScreenUtils.DEFAULT_BORDER_COLOR_END);

    @Override
    public void drawButton(PoseStack postStack, int x, int y, int width, int height, float partialTicks, int mouseX, int mouseY, InteractableState state) {
        ScreenUtils.blitWithBorder(postStack, TEXTURE, x, y, 0, 166 + state.getNumber() * 20, width, height, 200, 20, 2, 3, 2, 2, 0);
    }

    @Override
    public void drawContainer(PoseStack poseStack, int x, int y, int width, int height, float partialTicks, int mouseX, int mouseY) {
        ScreenUtils.blitWithBorder(poseStack, TEXTURE, x, y, 0, 0, width, height, 176, 166, 4, 0);
    }

    @Override
    public void drawScrollbar(PoseStack poseStack, int x, int y, int width, int height, float partialTicks, int mouseX, int mouseY) {

    }
    
    // Basically copied from Screen.renderTooltip
    @Override
    public void drawTooltip(PoseStack poseStack, int mouseX, int mouseY, int screenWidth, int screenHeight, Font font, List<FormattedText> tooltips) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        List<ClientTooltipComponent> components = ForgeHooksClient.gatherTooltipComponents(ItemStack.EMPTY, tooltips, mouseX, screenWidth, screenHeight, font, font);

        if (!tooltips.isEmpty()) {
            RenderTooltipEvent.Pre preEvent = ForgeHooksClient.onRenderTooltipPre(ItemStack.EMPTY, poseStack, mouseX, mouseY, screenWidth, screenHeight, components, font, font);
            if (preEvent.isCanceled()) return;
            int i = 0;
            int j = components.size() == 1 ? -2 : 0;

            for (ClientTooltipComponent clienttooltipcomponent : components) {
                int k = clienttooltipcomponent.getWidth(preEvent.getFont());
                if (k > i) {
                    i = k;
                }

                j += clienttooltipcomponent.getHeight();
            }

            int k1 = preEvent.getX() + 12;
            int l1 = preEvent.getY() - 12;
            poseStack.pushPose();
            float f = itemRenderer.blitOffset;
            itemRenderer.blitOffset = 400.0F;
            Tesselator tesselator = Tesselator.getInstance();
            BufferBuilder bufferbuilder = tesselator.getBuilder();
            RenderSystem.setShader(GameRenderer::getPositionColorShader);
            bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
            Matrix4f matrix4f = poseStack.last().pose();
            TooltipRenderUtil.renderTooltipBackground((Matrix4f pMatrix, BufferBuilder pBuilder, int pX1, int pY1, int pX2, int pY2, int pBlitOffset, int pColorA, int pColorB) -> {
                float ff = (float) (pColorA >> 24 & 255) / 255.0F;
                float f1 = (float) (pColorA >> 16 & 255) / 255.0F;
                float f2 = (float) (pColorA >> 8 & 255) / 255.0F;
                float f3 = (float) (pColorA & 255) / 255.0F;
                float f4 = (float) (pColorB >> 24 & 255) / 255.0F;
                float f5 = (float) (pColorB >> 16 & 255) / 255.0F;
                float f6 = (float) (pColorB >> 8 & 255) / 255.0F;
                float f7 = (float) (pColorB & 255) / 255.0F;
                pBuilder.vertex(pMatrix, (float) pX2, (float) pY1, (float) pBlitOffset).color(f1, f2, f3, ff).endVertex();
                pBuilder.vertex(pMatrix, (float) pX1, (float) pY1, (float) pBlitOffset).color(f1, f2, f3, ff).endVertex();
                pBuilder.vertex(pMatrix, (float) pX1, (float) pY2, (float) pBlitOffset).color(f5, f6, f7, f4).endVertex();
                pBuilder.vertex(pMatrix, (float) pX2, (float) pY2, (float) pBlitOffset).color(f5, f6, f7, f4).endVertex();
            }, matrix4f, bufferbuilder, k1, l1, i, j, 400);
            RenderSystem.enableDepthTest();
            RenderSystem.disableTexture();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            BufferUploader.drawWithShader(bufferbuilder.end());
            RenderSystem.disableBlend();
            RenderSystem.enableTexture();
            MultiBufferSource.BufferSource multibuffersource$buffersource = MultiBufferSource.immediate(Tesselator.getInstance().getBuilder());
            poseStack.translate(0.0F, 0.0F, 400.0F);
            int i1 = l1;

            for (int j1 = 0; j1 < components.size(); ++j1) {
                ClientTooltipComponent clienttooltipcomponent1 = components.get(j1);
                clienttooltipcomponent1.renderText(preEvent.getFont(), k1, i1, matrix4f, multibuffersource$buffersource);
                i1 += clienttooltipcomponent1.getHeight() + (j1 == 0 ? 2 : 0);
            }

            multibuffersource$buffersource.endBatch();
            poseStack.popPose();
            i1 = l1;

            for (int i2 = 0; i2 < components.size(); ++i2) {
                ClientTooltipComponent clienttooltipcomponent2 = components.get(i2);
                clienttooltipcomponent2.renderImage(preEvent.getFont(), k1, i1, poseStack, itemRenderer, 400);
                i1 += clienttooltipcomponent2.getHeight() + (i2 == 0 ? 2 : 0);
            }

            itemRenderer.blitOffset = f;
        }
    }

    @Override
    public Color getTextColor() {
        return TEXT;
    }
}
