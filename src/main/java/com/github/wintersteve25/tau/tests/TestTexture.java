package com.github.wintersteve25.tau.tests;

import com.github.wintersteve25.tau.components.Center;
import com.github.wintersteve25.tau.components.Texture;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.utils.Vector2i;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;

public class TestTexture implements UIComponent {
    @Override
    public UIComponent build(Layout layout) {
        return new Center(new Texture.Builder(InventoryScreen.INVENTORY_LOCATION)
                .withUvSize(new Vector2i(176, 166))
                .build());
    }
}
