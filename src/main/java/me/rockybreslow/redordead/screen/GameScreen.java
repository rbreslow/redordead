package me.rockybreslow.redordead.screen;

import me.rockybreslow.redordead.FallingEntityGenerator;
import me.rockybreslow.redordead.GameManager;
import me.rockybreslow.redordead.SoundManager;
import me.rockybreslow.redordead.StatueOfLibertyEntityGenerator;
import me.rockybreslow.redordead.entity.*;
import me.rockybreslow.redordead.manager.EntityManager;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.event.KeyEvent;

public class GameScreen extends Screen {
    private EntityManager<Entity> entityManager;
    private FallingEntityGenerator fallingEntityGenerator;
    private StatueOfLibertyEntityGenerator statueOfLibertyEntityGenerator;

    private PImage background;
    private PFont scoreFont;

    private PlayerEntity playerEntity;

    private int realScore;

    public GameScreen(ScreenManager screenManager) {
        super(screenManager);

        PApplet applet = screenManager.getApplet();

        entityManager = new EntityManager<>(applet);
        fallingEntityGenerator = new FallingEntityGenerator(100);
        statueOfLibertyEntityGenerator = new StatueOfLibertyEntityGenerator(applet);

        background = applet.loadImage("materials/background2.jpg");
        background.resize(applet.width, applet.height);
        scoreFont = applet.createFont("fonts/VKB KonQa_Communist.otf", 48);

        playerEntity = new PlayerEntity();
        entityManager.register(playerEntity);

        realScore = 1000;

        SoundManager.GAME_MUSIC.play(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onFrame() {
        PApplet applet = screenManager.getApplet();
        applet.background(background);

        // Register falling entities
        for(FallingEntity fallingEntity : fallingEntityGenerator.generateEntities()) {
            entityManager.register(fallingEntity);
        }

        // Register statue of liberty entities
        for(StatueOfLibertyEntity statueOfLibertyEntity : statueOfLibertyEntityGenerator.getStatueOfLibertyEntities()) {
            // entityManager.register(statueOfLibertyEntity);
        }

        // Destroy falling entities outside of the screen
        entityManager.destroyIf(p -> p instanceof FallingEntity && p.position.y > applet.height - ((PhysicsEntity) p).height);

        // Update our entities
        entityManager.update();

        // Calculate collisions and scoring for falling entities
        for(Entity entity : entityManager.getRegistrees()) {
            if(entity instanceof StatueOfLibertyEntity) {
                if(((StatueOfLibertyEntity) entity).collides(playerEntity)) {
                    // Take action on Statue of Liberty hitting player
                }
            }

            if(!(entity instanceof FallingEntity)) {
                continue;
            }

            FallingEntity fallingEntity = (FallingEntity) entity;

            if(fallingEntity.collides(playerEntity)) {
                fallingEntity.velocity.add(PVector.sub(playerEntity.position, entity.position).setMag(.5f));
                fallingEntity.doFadeOut = true;
                if(!fallingEntity.didScore) {
                    GameManager.instance.score += Math.abs(fallingEntity.score);
                    realScore += fallingEntity.score;
                    realScore = Math.min(realScore, 3000);
                    fallingEntity.didScore = true;
                    if(fallingEntity.score > 0) {
                        SoundManager.playBeep();
                        if((int) (Math.random() * 4) == 1) {
                            statueOfLibertyEntityGenerator.generateStatueOfLibertyEntities();
                        }
                    } else {
                        SoundManager.playDarn();
                    }
                }

            }
        }

        // Adjust scores
        if(GameManager.instance.score > 30000) {
            fallingEntityGenerator.setFrequency(30);
        } else if(GameManager.instance.score > 20000) {
            fallingEntityGenerator.setFrequency(50);
        } else if(GameManager.instance.score > 10000) {
            fallingEntityGenerator.setFrequency(80);
        }

        // Paint scores
        applet.textFont(scoreFont);
        String str = "" + GameManager.instance.score;
        applet.text(str, applet.width - applet.textWidth(str) - 10, applet.textAscent() + 10);

        if(realScore < 0) {
            SoundManager.GAME_MUSIC.stop();
            screenManager.setActiveScreen(ScreenManager.ScreenType.End);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onKeyEvent(int code) {
        switch(code) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                playerEntity.moveLeft();
                break;

            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                playerEntity.moveRight();
                break;
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
