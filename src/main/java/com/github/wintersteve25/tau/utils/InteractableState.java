package com.github.wintersteve25.tau.utils;

public enum InteractableState {
    IDLE(1),
    HOVERED(2),
    DISABLED(0);
    
    private int number;
    
    private InteractableState(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
