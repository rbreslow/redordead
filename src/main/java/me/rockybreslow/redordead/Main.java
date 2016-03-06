package me.rockybreslow.redordead;

import me.rockybreslow.redordead.entity.Entity;
import me.rockybreslow.redordead.entity.PlayerEntity;
import processing.core.PApplet;

import java.awt.event.KeyEvent;
import java.util.logging.Logger;

/**
 * Main class for Red or Dead.
 *
 * @author Rocky Breslow
 */

public class Main extends PApplet {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private PlayerEntity player;

    public static void main(String[] args) {
        PApplet.main(Main.class.getName());
    }

    public void setup() {
        player = new PlayerEntity(this, "materials/fidelcastro.png");
        frameRate(120);
    }

    public void settings() {
        fullScreen();
    }

    public void draw() {
        background(g.backgroundColor);

        player.display();
        player.move();
    }

    @Override
    public void handleKeyEvent(processing.event.KeyEvent event) {
        switch(event.getKeyCode()) {
            case KeyEvent.VK_A:
                player.velocity.add(Entity.leftAcceleration);
                break;
            case KeyEvent.VK_D:
                player.velocity.add(Entity.rightAcceleration);
                break;
            case KeyEvent.VK_SPACE:
                player.velocity.add(Entity.upAcceleration);
                break;
        }
    }
}