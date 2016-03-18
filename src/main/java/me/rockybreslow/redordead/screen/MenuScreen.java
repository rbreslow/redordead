package me.rockybreslow.redordead.screen;

import me.rockybreslow.redordead.SoundManager;
import me.rockybreslow.redordead.util.ImageLoader;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import java.awt.event.KeyEvent;
import java.util.Random;

public class MenuScreen extends Screen {
    private int x;
    private int y;
    private int type;
    private int index;
    private int fontSize = 94;
    private int fontSizeMult = -1;

    private PImage[] backgrounds;

    private PFont headingLeft;
    private PFont headingRight;
    private PFont def;

    public MenuScreen(ScreenManager screenManager) {
        super(screenManager);

        PApplet applet = screenManager.getApplet();

        type = (int) (Math.random() * 2 + 1);
        if(type == 1) {
            x = (-1) * (int) (applet.width * 1.5) / 4;
        } else {
            x = 0;
        }
        y = (-1) * (int) (applet.height * 1.5) / 4;

        backgrounds = new PImage[]{
                ImageLoader.getInstance(applet).get("materials/background.jpg"),
                ImageLoader.getInstance(applet).get("materials/background_menu/background_1.jpg"),
                ImageLoader.getInstance(applet).get("materials/background_menu/background_2.jpg"),
                ImageLoader.getInstance(applet).get("materials/background_menu/background_3.jpg"),
                ImageLoader.getInstance(applet).get("materials/background_menu/background_4.jpg"),
                ImageLoader.getInstance(applet).get("materials/background_menu/background_5.JPG"),

        };

        for(PImage background : backgrounds) {
            background.resize((int) (applet.width * 1.5), (int) (applet.height * 1.5));
        }

        headingLeft = applet.createFont("fonts/VKB KonQa_Communist.otf", fontSize);
        headingRight = applet.createFont("fonts/Dead End.ttf", fontSize);
        def = applet.createFont("fonts/Roboto-Bold.ttf", fontSize);

        index  = new Random().nextInt(backgrounds.length);

        SoundManager.MENU_MUSIC.play(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onFrame() {
        PApplet applet = screenManager.getApplet();

        // Calculate background position and font size
        if(type == 1) {
            x += 1;
            y += 1;
        } else {
            x -= 1;
            y -= 1;
        }

        boolean check;
        if(type == 1) {
            check = x > 0 || y >= 0;
        } else {
            check = x < -400 || y > (applet.height * 2) * -1;
        }

        if(check) {
            if(index + 1 != backgrounds.length) {
                index++;
            } else {
                index = 0;
            }
            type = (int) (Math.random() * 2 + 1);
            if(type == 1) {
                x = (-1) * (int) (applet.width * 1.5) / 4;
            } else {
                x = 0;
            }
            y = (-1) * (int) (applet.height * 1.5) / 4;
        }

        if(fontSize <= 94) {
            fontSizeMult = 1;
        }
        if(fontSize >= 128) {
            fontSizeMult = -1;
        }

        fontSize += fontSizeMult;

        // Background
        applet.background(0);
        applet.image(backgrounds[index], x, y);

        // Main title
        applet.textAlign(PApplet.BASELINE, PApplet.CENTER);
        applet.textFont(def);
        applet.textSize(fontSize);
        float height = applet.textAscent();
        applet.textFont(headingLeft);
        applet.textSize(fontSize);
        drawShadowedText("RED", applet.width / 2 - applet.textWidth("RED OR DEAD") / 2, applet.height / 2 - applet.textAscent() / 2);
        applet.textFont(def);
        applet.textSize(fontSize - fontSize / 4);
        drawShadowedText(" OR ", applet.width / 2 - applet.textWidth(" OR ") / 2, applet.height / 2 - applet.textAscent() / 2);
        applet.textFont(headingRight);
        applet.textSize(fontSize);
        drawShadowedText("DEAD", applet.width / 2 + applet.textWidth("DEAD") / 2, applet.height / 2 - applet.textAscent() / 2);


        // Lower copyright text
        applet.textFont(def);
        applet.textSize(44);
        applet.fill(0);
        applet.text("Copyright \u00A9 Rbreslow Inc. 2016", applet.width / 2 - applet.textWidth("Copyright \u00A9 Rbreslow Inc. 2016") / 2, applet.height / 2 - 44 / 2 + 128 + 128);
        applet.fill(255);
        applet.text("Copyright \u00A9 Rbreslow Inc. 2016", applet.width / 2 - applet.textWidth("Copyright \u00A9 Rbreslow Inc. 2016") / 2 - 2, applet.height / 2 - 44 / 2 + 128 + 128 - 2);
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
    public void onKeyEvent(int code) {
        if(code == KeyEvent.VK_SPACE || code == KeyEvent.VK_ENTER) {
            SoundManager.MENU_MUSIC.stop();
            screenManager.setActiveScreen(ScreenManager.ScreenType.Story);
        }
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
