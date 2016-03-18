package me.rockybreslow.redordead.screen;

import me.rockybreslow.redordead.GameManager;
import me.rockybreslow.redordead.SoundManager;
import me.rockybreslow.redordead.util.ImageLoader;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.awt.event.KeyEvent;
import java.io.File;

public class EndScreen extends Screen {
    private PImage background;
    private PFont font;
    private PFont headingFont;

    private File dataFile;
    private JSONArray scores;

    private boolean acceptingInput;
    private String initials;

    public EndScreen(ScreenManager screenManager) {
        super(screenManager);

        PApplet applet = screenManager.getApplet();

        background = ImageLoader.getInstance(applet).get("materials/background_end.jpg");
        background.resize(applet.width, applet.height);

        font = applet.createFont("fonts/Roboto-Bold.ttf", 48);
        headingFont = applet.createFont("fonts/VKB KonQa_Communist.otf", 128);

        dataFile = new File("data.json");

        try {
            scores = dataFile.exists() ? PApplet.loadJSONArray(dataFile) : new JSONArray();
        } catch(Exception e) {
            scores = new JSONArray();
        }

        acceptingInput = true;
        initials = "";

        SoundManager.END_MUSIC.play(true);

        sortScores();
    }

    private void sortScores() {
        for(int i = 0; i < scores.size() - 1; i++) {
            int index = i;
            for(int j = i + 1; j < scores.size(); j++) {
                if(scores.getJSONObject(j).getInt("score") > scores.getJSONObject(index).getInt("score")) {
                    index = j;
                }

                JSONObject temp = scores.getJSONObject(index);

                scores.setJSONObject(index, scores.getJSONObject(i));
                scores.setJSONObject(i, temp);
            }
        }
    }

    private void saveScores(String initials, int score) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.setString("initials", initials);
        jsonObject.setInt("score", score);

        scores.append(jsonObject);

        sortScores();

        scores.save(dataFile, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onFrame() {
        PApplet applet = screenManager.getApplet();

        applet.background(background);

        applet.fill(255, 0, 0, 255);

        applet.textFont(headingFont);
        applet.text("HALL OF COMRADES", applet.width / 2 - applet.textWidth("HALL OF COMRADES") / 2, applet.textAscent());

        applet.textFont(font);

        for(int i = 0; i < (scores.size() < 10 ? scores.size() : 10); i++) {
            String str = scores.getJSONObject(i).getString("initials") + "  " + scores.getJSONObject(i).getInt("score");
            applet.text(str, applet.width / 2 - applet.textWidth(str) / 2, applet.height / 2 - ((scores.size() < 10 ? scores.size() : 10) * applet.textAscent()) / 2 + i * applet.textAscent());
        }

        float y = applet.height / 2 - ((scores.size() < 10 ? scores.size() : 10) * applet.textAscent()) / 2 + (scores.size() < 10 ? scores.size() : 10) * applet.textAscent();

        if(acceptingInput) {
            applet.text("ENTER YOUR INITIALS: ", 100, y);
            applet.text(initials, 100, y + applet.textAscent());
        }

        applet.text("YOUR SCORE:", applet.width / 2 - applet.textWidth("YOUR SCORE:") / 2, applet.height - applet.textAscent() - applet.textAscent() / 2 - applet.textAscent());
        applet.text(GameManager.instance.score, applet.width / 2 - applet.textWidth(GameManager.instance.score + "") / 2, applet.height - applet.textAscent() - applet.textAscent() / 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onKeyEvent(processing.event.KeyEvent event) {
        if(!acceptingInput) {
            return;
        }

        if(event.getKeyCode() == KeyEvent.VK_BACK_SPACE && initials.length() > 0) {
            initials = initials.substring(0, initials.length() - 1);
        }

        if(event.getKeyCode() == KeyEvent.VK_ENTER && initials.length() > 0) {
            saveScores(initials, GameManager.instance.score);
            acceptingInput = false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onKeyPressed(char c, int code) {
        if(!acceptingInput) {
            SoundManager.END_MUSIC.stop();
            GameManager.instance.score = 0;
            screenManager.setActiveScreen(ScreenManager.ScreenType.Game);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onKeyTyped(char c, int code) {
        if(!acceptingInput) {
            return;
        }

        if(!Character.isAlphabetic(c)) {
            return;
        }

        c = Character.toUpperCase(c);

        if(initials.length() < 3) {
            initials = initials.concat(Character.toString(c));
        }
    }
}
