package com.github.wintersteve25.tau.utils;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class ItemRenderProvider implements RenderProvider {

    private final ItemStack itemStack;

    public ItemRenderProvider(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemRenderProvider(Item item) {
        this(new ItemStack(item));
    }

    public ItemRenderProvider(Block block) {
        this(new ItemStack(block.asItem()));
    }

    @Override
    public void render(PoseStack PoseStack, int mouseX, int mouseY, float partialTicks, int x, int y, int width, int height) {
        renderItemStackInGui(PoseStack, itemStack, x, y, width, height);
    }

    private static void renderItemStackInGui(PoseStack PoseStack, ItemStack stackToRender, int x, int y, int width, int height) {
        RenderSystem.pushMatrix();
        RenderSystem.multMatrix(PoseStack.last().pose());
        RenderSystem.enableDepthTest();
        RenderHelper.turnBackOn();
        renderItemModelIntoGUI(stackToRender, x, y, width, height);
        RenderSystem.disableBlend();
        RenderHelper.turnOff();
        RenderSystem.popMatrix();
    }

    //modified from renderItemModelIntoGUI in ItemRenderer class in vanilla
    private static void renderItemModelIntoGUI(ItemStack stack, int x, int y, float xScale, float yScale) {

        Minecraft minecraft = Minecraft.getInstance();
        ItemRenderer renderer = minecraft.getItemRenderer();
        BakedModel bakedmodel = renderer.getModel(stack, null, null, 0);

        RenderSystem.pushMatrix();
        minecraft.textureManager.bind(TextureAtlas.LOCATION_BLOCKS);
        minecraft.textureManager.getTexture(TextureAtlas.LOCATION_BLOCKS).setFilter(false, false);
        RenderSystem.enableRescaleNormal();
        RenderSystem.enableAlphaTest();
        RenderSystem.defaultAlphaFunc();
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.translatef((float)x, (float)y, 100.0F + renderer.blitOffset);
        RenderSystem.translatef(xScale / 2, yScale / 2, 0.0F);
        RenderSystem.scalef(1.0F, -1.0F, 1.0F);
        RenderSystem.scalef(xScale, yScale, 16.0F);
        PoseStack PoseStack = new PoseStack();
        IRenderTypeBuffer.Impl irendertypebuffer$impl = Minecraft.getInstance().renderBuffers().bufferSource();
        boolean flag = !bakedmodel.usesBlockLight();
        if (flag) {
            RenderHelper.setupForFlatItems();
        }

        renderer.render(stack, ItemCameraTransforms.TransformType.GUI, false, PoseStack, irendertypebuffer$impl, 15728880, OverlayTexture.NO_OVERLAY, bakedmodel);
        irendertypebuffer$impl.endBatch();
        RenderSystem.enableDepthTest();
        if (flag) {
            RenderHelper.setupFor3DItems();
        }

        RenderSystem.disableAlphaTest();
        RenderSystem.disableRescaleNormal();
        RenderSystem.popMatrix();
    }
}
