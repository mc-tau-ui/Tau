package com.github.wintersteve25.tau.components.base;

import com.github.wintersteve25.tau.theme.Theme;
import net.minecraft.client.Minecraft;
import com.github.wintersteve25.tau.layout.Layout;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvent;

/**
 * The base of all UI Components
 */
public interface UIComponent {
    UIComponent build(Layout layout, Theme theme);
    
    default void playSound(SoundEvent soundEvent) {
        playSound(soundEvent, 0.25f);
    }

    default void playSound(SoundEvent soundEvent, float volume) {
        playSound(soundEvent, volume, 1);
    }
    
    default void playSound(SoundEvent sound, float volume, float pitch) {
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(sound, pitch, volume));
    }
}
