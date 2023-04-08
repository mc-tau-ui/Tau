package com.github.wintersteve25.tau.theme;

import com.github.wintersteve25.tau.utils.Color;

public interface ColorScheme {
    /**
     * @return Used by {@link com.github.wintersteve25.tau.components.Container} as default color
     */
    Color backgroundColor();

    /**
     * @return Not used by anything by default
     */
    Color secondaryBackgroundColor();

    /**
     * @return Used by {@link com.github.wintersteve25.tau.components.Text} as default color
     */
    Color textColor();

    /**
     * @return Used by {@link com.github.wintersteve25.tau.components.FlatButton} as default normal color
     */
    Color interactableColor();

    /**
     * @return Used by {@link com.github.wintersteve25.tau.components.FlatButton} as default hover color
     */
    Color interactableHoverColor();

    /**
     * @return Used by {@link com.github.wintersteve25.tau.components.FlatButton} as default disabled color
     */
    Color interactableDisabledColor();

    /**
     * @return Used by {@link com.github.wintersteve25.tau.components.Tooltip} as default tooltip color
     */
    Color tooltipColor();

    /**
     * @return Used by {@link com.github.wintersteve25.tau.components.Tooltip} as default tooltip border gradient start color
     */
    Color tooltipBorderColorStart();

    /**
     * @return Used by {@link com.github.wintersteve25.tau.components.Tooltip} as default tooltip border gradient end color
     */
    Color tooltipBorderColorEnd();
}