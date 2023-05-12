package com.github.wintersteve25.tau.components;

import com.github.wintersteve25.tau.theme.Theme;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.text.FormattedText;
import net.minecraft.util.text.StringTextComponent;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class TextField implements UIComponent {

    private final FormattedText message;
    private final FormattedText hintText;
    private final Consumer<String> onChange;
    private final Predicate<String> validator;
    private final BiFunction<String, Integer, IReorderingProcessor> formatter;

    public TextField(FormattedText message, Consumer<String> onChange, Predicate<String> validator, FormattedText hintText,
                     BiFunction<String, Integer, IReorderingProcessor> formatter) {
        this.message = message;
        this.onChange = onChange;
        this.validator = validator;
        this.hintText = hintText;
        this.formatter = formatter;
    }

    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new WidgetWrapper(new TextFieldWidget(message, hintText, onChange, validator, formatter));
    }
    
    private static final class TextFieldWidget extends net.minecraft.client.gui.components.MultilineTextField {
        public TextFieldWidget(Component message, Component hintText, Consumer<String> onChange,
                               Predicate<String> validator, BiFunction<String, Integer, IReorderingProcessor> formatter) {
            super(Minecraft.getInstance().font, 0, 0, 0, 0, message);
            if (validator != null) setFilter(validator);
            if (hintText != null) setSuggestion(hintText.getString());
            if (formatter != null) setFormatter(formatter);
            
            setResponder(text -> {
                if (onChange != null) {
                    onChange.accept(text);
                }
                
                if (!text.isEmpty()) {
                    setSuggestion("");
                } else {
                    if (hintText != null) setSuggestion(hintText.getString());
                }
            });
        }
    }

    public static final class Builder implements UIComponent {
        private FormattedText message;
        private FormattedText hintText;
        private Consumer<String> onChange;
        private Predicate<String> validator;
        private BiFunction<String, Integer, IReorderingProcessor> formatter;

        public Builder() {
        }

        public Builder withMessage(FormattedText message) {
            this.message = message;
            return this;
        }

        public Builder withHintText(FormattedText hintText) {
            this.hintText = hintText;
            return this;
        }

        public Builder withOnChange(Consumer<String> onChange) {
            this.onChange = onChange;
            return this;
        }

        public Builder withValidator(Predicate<String> validator) {
            this.validator = validator;
            return this;
        }

        public Builder withFormatter(BiFunction<String, Integer, IReorderingProcessor> formatter) {
            this.formatter = formatter;
            return this;
        }

        public TextField build() {
            return new TextField(message == null ? FormattedText.EMPTY : message, onChange, validator, hintText, formatter);
        }

        @Override
        public UIComponent build(Layout layout, Theme theme) {
            return build();
        }
    }
}
