package me.rockybreslow.redordead;

import me.rockybreslow.redordead.entity.Entity;
import me.rockybreslow.redordead.entity.FallingEntity;
import me.rockybreslow.redordead.entity.PlayerEntity;
import me.rockybreslow.redordead.manager.EntityManager;
import processing.core.PApplet;
import processing.core.PImage;

import java.awt.event.KeyEvent;
import java.util.logging.Logger;

/**
 * Main class for Red or Dead.
 *
 * @author Rocky Breslow
 */

public class Main extends PApplet {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private EntityManager<Entity> entityManager = new EntityManager<>();
    private FallingEntityGenerator fallingEntityGenerator = new FallingEntityGenerator(this, 1);

    private PImage background;
    private PlayerEntity playerEntity;

    public static void main(String[] args) {
        PApplet.main(Main.class.getName());
    }

    public void setup() {
        playerEntity = new PlayerEntity(this, "materials/player.png");
        entityManager.register(playerEntity);
        entityManager.register(new FallingEntity(this, "materials/cigar_128.png"));

        background = loadImage("materials/background.jpg");
        background.resize(width, height);

        background(background);

        frameRate(120);
    }

    public void settings() {
        fullScreen();
    }

    public void draw() {
        background(background);

        fallingEntityGenerator.generate();
        fallingEntityGenerator.update();

        entityManager.update();
    }

    @Override
    public void handleKeyEvent(processing.event.KeyEvent event) {
       switch(event.getKeyCode()) {
            case KeyEvent.VK_A:
                playerEntity.velocity.add(-2, 0);
                break;
            case KeyEvent.VK_D:
                playerEntity.velocity.add(2, 0);
                break;
            case KeyEvent.VK_SPACE:
                if(playerEntity.isGrounded()) {
                    System.out.print("Can jump");
                    playerEntity.velocity.add(0, -20);
                }
                break;
        }
    }
}