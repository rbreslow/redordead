package me.rockybreslow.redordead.screen;

import processing.core.PApplet;

import java.lang.reflect.InvocationTargetException;

public class ScreenManager {
    private final PApplet applet;
    private Screen activeScreen;

    public ScreenManager(PApplet applet, ScreenType screenType) {
        this.applet = applet;
        activeScreen = screenType.getScreen(this);
    }

    public void setActiveScreen(ScreenType screenType) {
        activeScreen = screenType.getScreen(this);
    }

    public PApplet getApplet() { return applet; }

    public Screen getActiveScreen() { return activeScreen; }

    public enum ScreenType {
        Game(GameScreen.class), Menu(MenuScreen.class), Story(StoryScreen.class), End(EndScreen.class), GameOver(GameOverScreen.class);

        private Class<? extends Screen> screen;

        ScreenType(Class<? extends Screen> screen) {
            this.screen = screen;
        }

        public Screen getScreen(ScreenManager screenManager) {
            try {
                return screen.getDeclaredConstructor(ScreenManager.class).newInstance(screenManager);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
