package me.rockybreslow.redordead;

import kuusisto.tinysound.TinySound;
import me.rockybreslow.redordead.screen.ScreenManager;
import me.rockybreslow.redordead.util.ImageLoader;
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;

/**
 * Main class for Red or Dead.
 *
 * @author Rocky Breslow
 */
public class Main extends PApplet {
    private ScreenManager screenManager;

    public static void main(String[] args) {
        // Open Processing sketch
        PApplet.main(Main.class.getName());

        // Gracefully shutdown sound system when the process ends
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                TinySound.shutdown();
            }
        });
    }

    public void setup() {
        frameRate(60);

        // Title of window
        surface.setTitle("Red or Dead");
        surface.setIcon(loadImage("materials/man.png"));

        // Loading text
        background(0);

        // Initialize sound system
        TinySound.init();

        keyRepeatEnabled = true;

        // Set active screen
        screenManager = new ScreenManager(this, ScreenManager.ScreenType.Menu);
    }

    public void settings() {
        fullScreen();
    }

    public void draw() {
        screenManager.getActiveScreen().onFrame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleKeyEvent(KeyEvent event) {
        super.handleKeyEvent(event);

        screenManager.getActiveScreen().onKeyEvent(event);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);

        screenManager.getActiveScreen().onKeyPressed(event.getKey(), event.getKeyCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyTyped(KeyEvent event) {
        super.keyTyped(event);

        screenManager.getActiveScreen().onKeyTyped(event.getKey(), event.getKeyCode());
    }
}