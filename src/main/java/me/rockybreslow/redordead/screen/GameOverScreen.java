package me.rockybreslow.redordead.screen;

import me.rockybreslow.redordead.SoundManager;
import me.rockybreslow.redordead.util.ImageLoader;
import processing.core.PImage;

/**
 * Created by Rocky Breslow on 3/17/2016.
 */
public class GameOverScreen extends Screen {
    private PImage background;

    public GameOverScreen(ScreenManager screenManager) {
        super(screenManager);

        background = ImageLoader.getInstance(screenManager.getApplet()).get("materials/gameover.jpg");
        background.resize(screenManager.getApplet().width, screenManager.getApplet().height);

        SoundManager.FAILURE.play(false);
    }

    @Override
    public void onFrame() {
        screenManager.getApplet().background(background);

        if(SoundManager.FAILURE.done()) {
            screenManager.setActiveScreen(ScreenManager.ScreenType.End);
        }
    }

    @Override
    public void onKeyEvent(int code) {

    }

    @Override
    public void onKeyPressed(char c, int code) {

    }

    @Override
    public void onKeyTyped(char c, int code) {

    }
}
