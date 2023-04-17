package com.github.wintersteve25.tau.theme;

import com.github.wintersteve25.tau.utils.Color;
import net.minecraftforge.fml.client.gui.GuiUtils;

public class DefaultColorScheme implements ColorScheme {
    
    public static final DefaultColorScheme INSTANCE = new DefaultColorScheme();
    
    private static final Color BACKGROUND = new Color(0xFF252525);
    private static final Color ON_BACKGROUND = new Color(0xFF373737);
    private static final Color SECONDARY_BACKGROUND = new Color(0xFF424242);
    private static final Color ON_SECONDARY_BACKGROUND = new Color(0xFF565656);
    private static final Color TEXT = new Color(0xFFE8E8E8);
    private static final Color INTERACTABLE = new Color(0xFF8B8B8B); 
    private static final Color HOVER = new Color(0xFFC6C6C6); 
    private static final Color DISABLED = new Color(0xFF555555); 
    private static final Color TOOLTIP = new Color(GuiUtils.DEFAULT_BACKGROUND_COLOR);
    private static final Color TOOLTIP_BORDER_START = new Color(GuiUtils.DEFAULT_BORDER_COLOR_START);
    private static final Color TOOLTIP_BORDER_END = new Color(GuiUtils.DEFAULT_BORDER_COLOR_END);
    
    @Override
    public Color backgroundColor() {
        return BACKGROUND;
    }

    @Override
    public Color secondaryBackgroundColor() {
        return SECONDARY_BACKGROUND;
    }

    @Override
    public Color onBackground() {
        return ON_BACKGROUND;
    }

    @Override
    public Color onSecondary() {
        return ON_SECONDARY_BACKGROUND;
    }

    @Override
    public Color textColor() {
        return TEXT;
    }

    @Override
    public Color interactableColor() {
        return INTERACTABLE;
    }

    @Override
    public Color interactableHoverColor() {
        return HOVER;
    }

    @Override
    public Color interactableDisabledColor() {
        return DISABLED;
    }

    @Override
    public Color tooltipColor() {
        return TOOLTIP;
    }

    @Override
    public Color tooltipBorderColorStart() {
        return TOOLTIP_BORDER_START;
    }

    @Override
    public Color tooltipBorderColorEnd() {
        return TOOLTIP_BORDER_END;
    }
}
