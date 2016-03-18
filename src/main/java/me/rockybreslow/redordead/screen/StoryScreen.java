package me.rockybreslow.redordead.screen;

import me.rockybreslow.redordead.SoundManager;
import me.rockybreslow.redordead.util.ImageLoader;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.event.KeyEvent;

public class StoryScreen extends Screen {
    private String[] subtitles = {
            "The year is 1962",
            "right before the Cuban Missile Crisis.",
            "And, at the height of failed western relations.",
            "You're in control of the protagonist, Jesus Christ--",
            "the one, and only, mesiah,",
            "on loan from G-d as a contractor to the Cuban government.",
            "Your mission is to produce propaganda\nto bolster dictator Fidel Cashflow's\nwealth and avoid western ideals."
    };

    private int subtitlesIndex = 0;

    private PImage background;
    private PFont font;

    public StoryScreen(ScreenManager screenManager) {
        super(screenManager);

        PApplet applet = screenManager.getApplet();

        background = ImageLoader.getInstance(applet).get("materials/confrence.jpg");
        background.resize(applet.width, applet.height);

        font = applet.createFont("fonts/Roboto-Bold.ttf", 42);

        SoundManager.STORY[subtitlesIndex].play(false);
        SoundManager.STORY_MUSIC.play(true, 0.2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onFrame() {
        PApplet applet = screenManager.getApplet();

        // Draw background
        applet.background(0);
        applet.tint(200, 0, 0, 255);
        applet.image(background, 0, 0);
        applet.tint(200, 0, 0, 255);

        // Render current subtitle
        applet.textFont(font);
        applet.textAlign(PApplet.BASELINE, PApplet.CENTER);
        drawShadowedText(subtitles[subtitlesIndex], applet.width / 2 - applet.textWidth(subtitles[subtitlesIndex]) / 2, applet.height / 2 - 60 / 2);

        // Advance font/story
        if(SoundManager.STORY[subtitlesIndex].done() && subtitlesIndex == 6) {
            SoundManager.STORY_MUSIC.stop();
            screenManager.setActiveScreen(ScreenManager.ScreenType.Game);
        } else if(SoundManager.STORY[subtitlesIndex].done()) {
            subtitlesIndex++;
            SoundManager.STORY[subtitlesIndex].play(false);
        }
    }

    public void drawShadowedText(String text, float x, float y) {
        PApplet applet = screenManager.getApplet();

        applet.fill(0, 0, 0, 255);
        applet.text(text, x, y);
        applet.fill(255, 0, 0, 255);
        applet.text(text, x - 3, y - 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onKeyEvent(KeyEvent event) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onKeyPressed(char c, int code) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onKeyTyped(char c, int code) {

    }
}
