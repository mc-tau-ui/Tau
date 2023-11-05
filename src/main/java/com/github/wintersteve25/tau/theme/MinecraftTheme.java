package com.github.wintersteve25.tau.theme;

import com.github.wintersteve25.tau.Tau;
import com.github.wintersteve25.tau.utils.Color;
import com.github.wintersteve25.tau.utils.InteractableState;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.DefaultTooltipPositioner;
import net.minecraft.client.gui.screens.inventory.tooltip.TooltipRenderUtil;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.client.gui.ScreenUtils;
import org.joml.Matrix4f;
import org.joml.Vector2i;
import org.joml.Vector2ic;

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
    public void drawTooltip(PoseStack poseStack, int mouseX, int mouseY, int screenWidth, int screenHeight, Font font, List<ClientTooltipComponent> tooltips) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        if (!tooltips.isEmpty()) {
            RenderTooltipEvent.Pre preEvent = ForgeHooksClient.onRenderTooltipPre(
                    ItemStack.EMPTY,
                    poseStack,
                    mouseX,
                    mouseY,
                    screenWidth,
                    screenHeight,
                    tooltips,
                    font,
                    font
            );

            if (preEvent.isCanceled()) return;
            int i = 0;
            int j = tooltips.size() == 1 ? -2 : 0;

            for (ClientTooltipComponent clienttooltipcomponent : tooltips) {
                int k = clienttooltipcomponent.getWidth(preEvent.getFont());
                if (k > i) {
                    i = k;
                }

                j += clienttooltipcomponent.getHeight();
            }

            Vector2ic vector2ic = positionTooltip(screenWidth, screenHeight, preEvent.getX(), preEvent.getY(), i, j);
            int l = vector2ic.x();
            int i1 = vector2ic.y();
            poseStack.pushPose();

            Tesselator tesselator = Tesselator.getInstance();
            BufferBuilder bufferbuilder = tesselator.getBuilder();
            RenderSystem.setShader(GameRenderer::getPositionColorShader);
            bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
            Matrix4f matrix4f = poseStack.last().pose();
            RenderTooltipEvent.Color colorEvent = ForgeHooksClient.onRenderTooltipColor(ItemStack.EMPTY, poseStack, l, i1, preEvent.getFont(), tooltips);
            TooltipRenderUtil.renderTooltipBackground(MinecraftTheme::fillGradient, matrix4f, bufferbuilder, l, i1, i, j, 400, colorEvent.getBackgroundStart(), colorEvent.getBackgroundEnd(), colorEvent.getBorderStart(), colorEvent.getBorderEnd());
            RenderSystem.enableDepthTest();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            BufferUploader.drawWithShader(bufferbuilder.end());
            MultiBufferSource.BufferSource multibuffersource$buffersource = MultiBufferSource.immediate(Tesselator.getInstance().getBuilder());
            poseStack.translate(0.0F, 0.0F, 400.0F);
            int k1 = i1;

            for (int l1 = 0; l1 < tooltips.size(); ++l1) {
                ClientTooltipComponent clienttooltipcomponent1 = tooltips.get(l1);
                clienttooltipcomponent1.renderText(preEvent.getFont(), l, k1, matrix4f, multibuffersource$buffersource);
                k1 += clienttooltipcomponent1.getHeight() + (l1 == 0 ? 2 : 0);
            }

            multibuffersource$buffersource.endBatch();
            k1 = i1;

            for (int i2 = 0; i2 < tooltips.size(); ++i2) {
                ClientTooltipComponent clienttooltipcomponent2 = tooltips.get(i2);
                clienttooltipcomponent2.renderImage(preEvent.getFont(), l, k1, poseStack, itemRenderer);
                k1 += clienttooltipcomponent2.getHeight() + (i2 == 0 ? 2 : 0);
            }

            poseStack.popPose();
        }
    }

    private Vector2ic positionTooltip(int screenWidth, int screenHeight, int pMouseX, int pMouseY, int pWidth, int pHeight) {
        Vector2i vector2i = (new Vector2i(pMouseX, pMouseY)).add(12, -12);
        this.positionTooltip(screenWidth, screenHeight, vector2i, pWidth, pHeight);
        return vector2i;
    }

    private void positionTooltip(int screenWidth, int screenHeight, Vector2i pPosition, int pWidth, int pHeight) {
        if (pPosition.x + pWidth > screenWidth) {
            pPosition.x = Math.max(pPosition.x - 24 - pWidth, 4);
        }

        int i = pHeight + 3;
        if (pPosition.y + i > screenHeight) {
            pPosition.y = screenHeight - i;
        }
    }

    private static void fillGradient(Matrix4f pMatrix, BufferBuilder pBuilder, int pX1, int pY1, int pX2, int pY2, int pBlitOffset, int pColorA, int pColorB) {
        float f = (float) FastColor.ARGB32.alpha(pColorA) / 255.0F;
        float f1 = (float) FastColor.ARGB32.red(pColorA) / 255.0F;
        float f2 = (float) FastColor.ARGB32.green(pColorA) / 255.0F;
        float f3 = (float) FastColor.ARGB32.blue(pColorA) / 255.0F;
        float f4 = (float) FastColor.ARGB32.alpha(pColorB) / 255.0F;
        float f5 = (float) FastColor.ARGB32.red(pColorB) / 255.0F;
        float f6 = (float) FastColor.ARGB32.green(pColorB) / 255.0F;
        float f7 = (float) FastColor.ARGB32.blue(pColorB) / 255.0F;
        pBuilder.vertex(pMatrix, (float) pX1, (float) pY1, (float) pBlitOffset).color(f1, f2, f3, f).endVertex();
        pBuilder.vertex(pMatrix, (float) pX1, (float) pY2, (float) pBlitOffset).color(f5, f6, f7, f4).endVertex();
        pBuilder.vertex(pMatrix, (float) pX2, (float) pY2, (float) pBlitOffset).color(f5, f6, f7, f4).endVertex();
        pBuilder.vertex(pMatrix, (float) pX2, (float) pY1, (float) pBlitOffset).color(f1, f2, f3, f).endVertex();
    }

    @Override
    public Color getTextColor() {
        return TEXT;
    }
}
