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
import processing.event.KeyEvent;

public class GameScreen extends Screen {
    private EntityManager<Entity> entityManager;
    private FallingEntityGenerator fallingEntityGenerator;
    private StatueOfLibertyEntityGenerator statueOfLibertyEntityGenerator;

    private PImage background;
    private PFont scoreFont;

    private PlayerEntity playerEntity;

    private int realScore;

    private boolean moving;

    public GameScreen(ScreenManager screenManager) {
        super(screenManager);

        PApplet applet = screenManager.getApplet();

        entityManager = new EntityManager<>(applet);
        fallingEntityGenerator = new FallingEntityGenerator(100);
        statueOfLibertyEntityGenerator = new StatueOfLibertyEntityGenerator(applet);

        background = applet.loadImage("materials/background2.jpg");
        background.resize(applet.width, applet.height);
        scoreFont = applet.createFont("fonts/Roboto-Bold.ttf", 60);

        playerEntity = new PlayerEntity();
        entityManager.register(playerEntity);

        realScore = 1000;

        moving = false;

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
            entityManager.register(statueOfLibertyEntity);
        }

        // Destroy falling entities outside of the screen
        entityManager.destroyIf(p -> p instanceof FallingEntity && p.position.y > applet.height + (((PhysicsEntity) p).height * 2));

        // Update our entities
        entityManager.update();

        // Destroy disposed liberties
        entityManager.destroyIf(p -> p instanceof StatueOfLibertyEntity && ((StatueOfLibertyEntity) p).dispose);

        // Calculate collisions and scoring for falling entities
        for(Entity entity : entityManager.getRegistrees()) {
            if(entity instanceof StatueOfLibertyEntity) {
                if(((StatueOfLibertyEntity) entity).collides(playerEntity)) {
                    playerEntity.velocity.mult(-1);
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

                        boolean canGenerateStatues = true;
                        for(Entity potential : entityManager.getRegistrees()) {
                            if(potential instanceof StatueOfLibertyEntity) {
                                canGenerateStatues = false;
                            }
                        }

                        if(GameManager.instance.score > 20000 && ((int) (Math.random() * 15) == 1)) {
                            if(canGenerateStatues) {
                                statueOfLibertyEntityGenerator.generateStatueOfLibertyEntities();
                                SoundManager.ANTHEM.play(2);
                            }
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
        applet.fill(255, 0, 0, 255);
        String str = "" + GameManager.instance.score;
        applet.fill(0);
        applet.text(str, applet.width / 2 - applet.textWidth(str) / 2, applet.textAscent() + 10);
        applet.fill(255);
        applet.text(str, applet.width / 2 - applet.textWidth(str) / 2 - 2, applet.textAscent() + 10 - 2);

        if(realScore < 0) {
            SoundManager.GAME_MUSIC.stop();
            screenManager.setActiveScreen(ScreenManager.ScreenType.GameOver);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onKeyEvent(KeyEvent event) {
        if(event.getAction() == KeyEvent.RELEASE) {
            //playerEntity.velocity = new PVector();
            return;
        }

        switch(event.getKeyCode()) {
            case java.awt.event.KeyEvent.VK_LEFT:
                playerEntity.moveLeft();
                break;
            case java.awt.event.KeyEvent.VK_A:
                playerEntity.moveLeft();
                break;

            case java.awt.event.KeyEvent.VK_RIGHT:
                playerEntity.moveRight();
                break;
            case java.awt.event.KeyEvent.VK_D:
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
