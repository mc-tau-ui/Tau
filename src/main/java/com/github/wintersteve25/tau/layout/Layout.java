package wintersteve25.tau.layout;

import wintersteve25.tau.utils.Vector2i;

public class Layout {

    public static final Layout MAX = new Layout(Integer.MAX_VALUE, Integer.MAX_VALUE);
    
    private final int width;
    private final int height;

    private final StackedAxialSettings<Integer> offsets;
    private final StackedAxialSettings<LayoutSetting> layoutSettings;

    public Layout(int width, int height) {
        this(width, height, 0, 0);
    }

    public Layout(int width, int height, int xOffset, int yOffset) {
        this.width = width;
        this.height = height;

        this.offsets = new StackedAxialSettings<>();
        this.offsets.push(Axis.HORIZONTAL, xOffset);
        this.offsets.push(Axis.VERTICAL, yOffset);
        
        this.layoutSettings = new StackedAxialSettings<>();
        this.layoutSettings.push(Axis.HORIZONTAL, LayoutSetting.START);
        this.layoutSettings.push(Axis.VERTICAL, LayoutSetting.START);
    }

    private Layout(int width, int height, StackedAxialSettings<Integer> offsets, StackedAxialSettings<LayoutSetting> layoutSettings) {
        this.width = width;
        this.height = height;
        this.offsets = offsets;
        this.layoutSettings = layoutSettings;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public Vector2i getSize() {
        return new Vector2i(width, height);
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
        return axis == Axis.VERTICAL ? height : width;
    }
    
    public Layout copy() {
        return new Layout(this.width, this.height, this.offsets.copy(), this.layoutSettings.copy());
    }
}