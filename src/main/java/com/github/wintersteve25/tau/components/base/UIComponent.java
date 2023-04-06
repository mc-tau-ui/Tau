package com.github.wintersteve25.tau.components.base;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.util.SoundEvent;
import com.github.wintersteve25.tau.layout.Layout;

/**
 * The base of all UI Components
 */
public interface UIComponent {
    UIComponent build(Layout layout);
    
    default void playSound(SoundEvent soundEvent) {
        playSound(soundEvent, 0.25f);
    }

    default void playSound(SoundEvent soundEvent, float volume) {
        playSound(soundEvent, volume, 1);
    }
    
    default void playSound(SoundEvent sound, float volume, float pitch) {
        Minecraft.getInstance().getSoundManager().play(SimpleSound.forUI(sound, pitch, volume));
    }
}
