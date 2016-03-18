package me.rockybreslow.redordead.entity;

import me.rockybreslow.redordead.SoundManager;
import me.rockybreslow.redordead.util.ImageLoader;
import processing.core.PApplet;
import processing.core.PVector;

public class PlayerEntity extends PhysicsEntity {
    private int alpha = 255;

    public PlayerEntity() {
        super(0, 0, 128, 151);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onRegistration(PApplet applet) {
        super.onRegistration(applet);

        position = new PVector(applet.width / 2 - width / 2, applet.height - height);
    }

    public boolean isGrounded() {
        PApplet applet = getPApplet();

        return (int) position.y == applet.height - height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onFrame() {
        getPApplet().tint(255, alpha);
        getPApplet().image(ImageLoader.getInstance(getPApplet()).get("materials/player.png"), position.x, position.y, width, height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onUpdate() {
        super.onUpdate();

        velocity.mult(1.05f);

        PApplet applet = getPApplet();

        if((position.x > applet.width - width) || (position.x < 0)) {
            position.x = position.x < 0 ? applet.width - width : 0;
            alpha = 0;
            SoundManager.playWoosh();
        }

        if(alpha < 255) {
            alpha += 5;
        }

        if (position.y > applet.height - height) {
            velocity.y = 0;
            position.y = applet.height - height;
        }
    }

    public void moveLeft() {
        velocity.add(-8f, 0);
    }

    public void moveRight() {
        velocity.add(8f, 0);
    }
}
