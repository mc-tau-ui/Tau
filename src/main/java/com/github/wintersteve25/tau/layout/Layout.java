package com.github.wintersteve25.tau.layout;

import com.github.wintersteve25.tau.theme.ColorScheme;
import com.github.wintersteve25.tau.theme.DefaultColorScheme;
import com.github.wintersteve25.tau.utils.Vector2i;

public class Layout {

    private final int width;
    private final int height;
    private final ColorScheme colorScheme;

    private final StackedAxialSettings<Integer> offsets;
    private final StackedAxialSettings<Integer> sizeModification;
    private final StackedAxialSettings<LayoutSetting> layoutSettings;

    public Layout(int width, int height, ColorScheme colorScheme) {
        this(width, height, 0, 0, colorScheme);
    }

    public Layout(int width, int height, int xOffset, int yOffset, ColorScheme colorScheme) {
        this.width = width;
        this.height = height;
        this.colorScheme = colorScheme;

        this.offsets = new StackedAxialSettings<>();
        this.offsets.push(Axis.HORIZONTAL, xOffset);
        this.offsets.push(Axis.VERTICAL, yOffset);
        
        this.sizeModification = new StackedAxialSettings<>();
        this.sizeModification.push(Axis.HORIZONTAL, 0);
        this.sizeModification.push(Axis.VERTICAL, 0);
        
        this.layoutSettings = new StackedAxialSettings<>();
        this.layoutSettings.push(Axis.HORIZONTAL, LayoutSetting.START);
        this.layoutSettings.push(Axis.VERTICAL, LayoutSetting.START);
    }

    private Layout(int width, int height, ColorScheme colorScheme, StackedAxialSettings<Integer> offsets, StackedAxialSettings<Integer> sizeModification, StackedAxialSettings<LayoutSetting> layoutSettings) {
        this.width = width;
        this.height = height;
        this.colorScheme = colorScheme;
        this.offsets = offsets;
        this.sizeModification = sizeModification;
        this.layoutSettings = layoutSettings;
    }
    
    public ColorScheme getColorScheme() {
        return colorScheme;
    }

    public int getWidth() {
        return width + sizeModification.getLast(Axis.HORIZONTAL);
    }

    public int getHeight() {
        return height + sizeModification.getLast(Axis.VERTICAL);
    }
    
    public Vector2i getSize() {
        return new Vector2i(getWidth(), getHeight());
    }

    public LayoutSetting getLayoutSetting(Axis axis) {
        return layoutSettings.getLast(axis);
    }
    
    public void pushLayoutSetting(Axis axis, LayoutSetting layoutSetting) {
        layoutSettings.push(axis, layoutSetting);
    }
    
    public void popLayoutSetting(Axis axis) {
        layoutSettings.pop(axis);
    }
    
    public void pushOffset(Axis axis, int amount) {
        offsets.push(axis, amount);
    }
    
    public void popOffset(Axis axis) {
        offsets.pop(axis);
    }

    public void pushSizeMod(Axis axis, int amount) {
        sizeModification.push(axis, amount);
    }

    public void popSizeMod(Axis axis) {
        sizeModification.pop(axis);
    }

    public int getPosition(Axis axis, int length) {
        return getOffset(axis) + getLayoutSetting(axis).place(getMaximumLength(axis), length);
    }
    
    public Vector2i getPosition(Vector2i size) {
        return new Vector2i(getPosition(Axis.HORIZONTAL, size.x), getPosition(Axis.VERTICAL, size.y));
    }
    
    public int getOffset(Axis axis) {
        int result = 0;
        
        for (int offset : offsets.get(axis)) {
            result += offset;
        }
        
        return result;
    }
    
    public int getMaximumLength(Axis axis) {
        return axis == Axis.VERTICAL ? getHeight() : getWidth();
    }
    
    public Layout copy() {
        return new Layout(getWidth(), getHeight(), colorScheme, this.offsets.copy(), this.sizeModification.copy(), this.layoutSettings.copy());
    }
}