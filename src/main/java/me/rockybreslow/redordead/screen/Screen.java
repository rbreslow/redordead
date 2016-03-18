package me.rockybreslow.redordead.screen;

import processing.event.KeyEvent;

public abstract class Screen {
    protected final ScreenManager screenManager;

    public Screen(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }

    public abstract void onFrame();

    public abstract void onKeyEvent(KeyEvent event);

    public abstract void onKeyPressed(char c, int code);

    public abstract void onKeyTyped(char c, int code);
}
