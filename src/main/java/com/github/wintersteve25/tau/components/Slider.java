package com.github.wintersteve25.tau.components;

import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.theme.Theme;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraftforge.client.gui.widget.ForgeSlider;

import java.util.function.Consumer;

public final class Slider implements UIComponent {
    
    private final FormattedText prefix;
    private final FormattedText suffix;
    private final boolean showDecimals;
    private final double value;
    private final double minimum;
    private final double maximum;
    private final Runnable onPress;
    private final Consumer<Double> onValueChanged;

    public Slider(FormattedText prefix, FormattedText suffix, boolean showDecimals, double value, double minimum,
                  double maximum, Runnable onPress, Consumer<Double> onValueChanged) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.showDecimals = showDecimals;
        this.value = value;
        this.minimum = minimum;
        this.maximum = maximum;
        this.onPress = onPress;
        this.onValueChanged = onValueChanged;
    }

    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new WidgetWrapper(new SliderWidget(prefix, suffix, showDecimals, minimum, maximum, value, onPress, onValueChanged));
    }
    
    private static final class SliderWidget extends ForgeSlider {
        public SliderWidget(FormattedText prefix, FormattedText suffix, boolean showDecimals, double minVal, double maxVal, double currentVal, Runnable onPress, Consumer<Double> onValueChange) {
            super(0, 0, 0, 0, (Component) prefix, (Component) suffix, minVal, maxVal, currentVal, 1, showDecimals ? 2 : 0, true);
//                    (b) -> {
//                if (onPress != null) onPress.run();
//            }, (slider) -> {
//                if (onValueChange != null) onValueChange.accept(slider.getValue());
//            }
        }
    }

    public static final class Builder implements UIComponent {
        private FormattedText prefix;
        private FormattedText suffix;
        private boolean showDecimals = true;
        private double value;
        private double minimum = 0;
        private double maximum = 1;
        private Runnable onPress;
        private Consumer<Double> onValueChanged;

        public Builder() {
        }

        public Builder withPrefix(FormattedText prefix) {
            this.prefix = prefix;
            return this;
        }

        public Builder withSuffix(FormattedText suffix) {
            this.suffix = suffix;
            return this;
        }

        public Builder withShowDecimals(boolean showDecimals) {
            this.showDecimals = showDecimals;
            return this;
        }

        public Builder withValue(double value) {
            this.value = value;
            return this;
        }

        public Builder withMinimum(double minimum) {
            this.minimum = minimum;
            return this;
        }

        public Builder withMaximum(double maximum) {
            this.maximum = maximum;
            return this;
        }

        public Builder withOnPress(Runnable onPress) {
            this.onPress = onPress;
            return this;
        }

        public Builder withOnValueChanged(Consumer<Double> onValueChanged) {
            this.onValueChanged = onValueChanged;
            return this;
        }

        public Slider build() {
            return new Slider(
                    prefix == null ? FormattedText.EMPTY : prefix,
                    suffix == null ? FormattedText.EMPTY : suffix,
                    showDecimals, 
                    value, 
                    minimum, 
                    maximum, 
                    onPress, 
                    onValueChanged
            );
        }

        @Override
        public UIComponent build(Layout layout, Theme theme) {
            return build();
        }
    }
}
