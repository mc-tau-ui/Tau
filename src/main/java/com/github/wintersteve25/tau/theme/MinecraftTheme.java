package com.github.wintersteve25.tau.theme;

import com.github.wintersteve25.tau.Tau;
import com.github.wintersteve25.tau.utils.Color;
import com.github.wintersteve25.tau.utils.InteractableState;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.gui.GuiUtils;

import java.util.List;

public class MinecraftTheme implements Theme {
    public static final MinecraftTheme INSTANCE = new MinecraftTheme();

    private static final ResourceLocation TEXTURE = new ResourceLocation(Tau.MOD_ID, "textures/gui/container.png");
    private static final Color TEXT = new Color(0xFFE8E8E8);
    private static final Color TOOLTIP = new Color(GuiUtils.DEFAULT_BACKGROUND_COLOR);
    private static final Color TOOLTIP_BORDER_START = new Color(GuiUtils.DEFAULT_BORDER_COLOR_START);
    private static final Color TOOLTIP_BORDER_END = new Color(GuiUtils.DEFAULT_BORDER_COLOR_END);

    @Override
    public void drawButton(PoseStack postStack, int x, int y, int width, int height, float partialTicks, int mouseX, int mouseY, InteractableState state) {
        GuiUtils.drawContinuousTexturedBox(postStack, TEXTURE, x, y, 0, 166 + state.getNumber() * 20, width, height, 200, 20, 2, 3, 2, 2, 0);
    }

    @Override
    public void drawContainer(PoseStack poseStack, int x, int y, int width, int height, float partialTicks, int mouseX, int mouseY) {
        GuiUtils.drawContinuousTexturedBox(poseStack, TEXTURE, x, y, 0, 0, width, height, 176, 166, 4, 0);
    }

    @Override
    public void drawScrollbar(PoseStack poseStack, int x, int y, int width, int height, float partialTicks, int mouseX, int mouseY) {

    }
    
    // Basically copied from Screen.renderTooltip
    @Override
    public void drawTooltip(PoseStack poseStack, int mouseX, int mouseY, int screenWidth, int screenHeight, Font font, List<ClientTooltipComponent> tooltips) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        if (!tooltips.isEmpty()) {
         net.minecraftforge.client.event.RenderTooltipEvent.Pre preEvent = net.minecraftforge.client.ForgeHooksClient.onRenderTooltipPre(ItemStack.EMPTY, poseStack, mouseX, mouseY, screenWidth, screenHeight, tooltips, font, font);
         if (preEvent.isCanceled()) return;
         int i = 0;
         int j = tooltips.size() == 1 ? -2 : 0;

         for(ClientTooltipComponent clienttooltipcomponent : tooltips) {
            int k = clienttooltipcomponent.getWidth(preEvent.getFont());
            if (k > i) {
               i = k;
            }

            j += clienttooltipcomponent.getHeight();
         }

         int j2 = preEvent.getX() + 12;
         int k2 = preEvent.getY() - 12;
         if (j2 + i > screenWidth) {
            j2 -= 28 + i;
         }

         if (k2 + j + 6 > screenHeight) {
            k2 = screenHeight - j - 6;
         }

         poseStack.pushPose();
         int l = -267386864;
         int i1 = 1347420415;
         int j1 = 1344798847;
         int k1 = 400;
         float f = itemRenderer.blitOffset;
         itemRenderer.blitOffset = 400.0F;
         Tesselator tesselator = Tesselator.getInstance();
         BufferBuilder bufferbuilder = tesselator.getBuilder();
         RenderSystem.setShader(GameRenderer::getPositionColorShader);
         bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
         Matrix4f matrix4f = poseStack.last().pose();
         net.minecraftforge.client.event.RenderTooltipEvent.Color colorEvent = net.minecraftforge.client.ForgeHooksClient.onRenderTooltipColor(ItemStack.EMPTY, poseStack, j2, k2, preEvent.getFont(), tooltips);
         fillGradient(matrix4f, bufferbuilder, j2 - 3, k2 - 4, j2 + i + 3, k2 - 3, 400, colorEvent.getBackgroundStart(), colorEvent.getBackgroundStart());
         fillGradient(matrix4f, bufferbuilder, j2 - 3, k2 + j + 3, j2 + i + 3, k2 + j + 4, 400, colorEvent.getBackgroundEnd(), colorEvent.getBackgroundEnd());
         fillGradient(matrix4f, bufferbuilder, j2 - 3, k2 - 3, j2 + i + 3, k2 + j + 3, 400, colorEvent.getBackgroundStart(), colorEvent.getBackgroundEnd());
         fillGradient(matrix4f, bufferbuilder, j2 - 4, k2 - 3, j2 - 3, k2 + j + 3, 400, colorEvent.getBackgroundStart(), colorEvent.getBackgroundEnd());
         fillGradient(matrix4f, bufferbuilder, j2 + i + 3, k2 - 3, j2 + i + 4, k2 + j + 3, 400, colorEvent.getBackgroundStart(), colorEvent.getBackgroundEnd());
         fillGradient(matrix4f, bufferbuilder, j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + j + 3 - 1, 400, colorEvent.getBorderStart(), colorEvent.getBorderEnd());
         fillGradient(matrix4f, bufferbuilder, j2 + i + 2, k2 - 3 + 1, j2 + i + 3, k2 + j + 3 - 1, 400, colorEvent.getBorderStart(), colorEvent.getBorderEnd());
         fillGradient(matrix4f, bufferbuilder, j2 - 3, k2 - 3, j2 + i + 3, k2 - 3 + 1, 400, colorEvent.getBorderStart(), colorEvent.getBorderStart());
         fillGradient(matrix4f, bufferbuilder, j2 - 3, k2 + j + 2, j2 + i + 3, k2 + j + 3, 400, colorEvent.getBorderEnd(), colorEvent.getBorderEnd());
         RenderSystem.enableDepthTest();
         RenderSystem.disableTexture();
         RenderSystem.enableBlend();
         RenderSystem.defaultBlendFunc();
         bufferbuilder.end();
         BufferUploader.end(bufferbuilder);
         RenderSystem.disableBlend();
         RenderSystem.enableTexture();
         MultiBufferSource.BufferSource multibuffersource$buffersource = MultiBufferSource.immediate(Tesselator.getInstance().getBuilder());
         poseStack.translate(0.0D, 0.0D, 400.0D);
         int l1 = k2;

         for(int i2 = 0; i2 < tooltips.size(); ++i2) {
            ClientTooltipComponent clienttooltipcomponent1 = tooltips.get(i2);
            clienttooltipcomponent1.renderText(preEvent.getFont(), j2, l1, matrix4f, multibuffersource$buffersource);
            l1 += clienttooltipcomponent1.getHeight() + (i2 == 0 ? 2 : 0);
         }

         multibuffersource$buffersource.endBatch();
         poseStack.popPose();
         l1 = k2;

         for(int l2 = 0; l2 < tooltips.size(); ++l2) {
            ClientTooltipComponent clienttooltipcomponent2 = tooltips.get(l2);
            clienttooltipcomponent2.renderImage(preEvent.getFont(), j2, l1, poseStack, itemRenderer, 400);
            l1 += clienttooltipcomponent2.getHeight() + (l2 == 0 ? 2 : 0);
         }

         itemRenderer.blitOffset = f;
      }
    }
    
    protected static void fillGradient(Matrix4f pMatrix, BufferBuilder pBuilder, int pX1, int pY1, int pX2, int pY2, int pBlitOffset, int pColorA, int pColorB) {
        float f = (float)(pColorA >> 24 & 255) / 255.0F;
        float f1 = (float)(pColorA >> 16 & 255) / 255.0F;
        float f2 = (float)(pColorA >> 8 & 255) / 255.0F;
        float f3 = (float)(pColorA & 255) / 255.0F;
        float f4 = (float)(pColorB >> 24 & 255) / 255.0F;
        float f5 = (float)(pColorB >> 16 & 255) / 255.0F;
        float f6 = (float)(pColorB >> 8 & 255) / 255.0F;
        float f7 = (float)(pColorB & 255) / 255.0F;
        pBuilder.vertex(pMatrix, (float)pX2, (float)pY1, (float)pBlitOffset).color(f1, f2, f3, f).endVertex();
        pBuilder.vertex(pMatrix, (float)pX1, (float)pY1, (float)pBlitOffset).color(f1, f2, f3, f).endVertex();
        pBuilder.vertex(pMatrix, (float)pX1, (float)pY2, (float)pBlitOffset).color(f5, f6, f7, f4).endVertex();
        pBuilder.vertex(pMatrix, (float)pX2, (float)pY2, (float)pBlitOffset).color(f5, f6, f7, f4).endVertex();
    }
    
    @Override
    public Color getTextColor() {
        return TEXT;
    }
}