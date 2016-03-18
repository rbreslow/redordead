package me.rockybreslow.redordead.entity;

import me.rockybreslow.redordead.util.ImageLoader;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public class FallingEntity extends PhysicsEntity {
    private String imagePath;
    private int angle;
    private int angleMultiplier;
    public boolean doFadeOut = false;
    public int alpha = 255;
    public int score;
    public boolean didScore = false;

    public FallingEntity(int width, int height, String imagePath, int score) {
        super(0, -height, width, height);

        this.imagePath = imagePath;

        this.angle = (int) Math.round(Math.random() * 4) * 90;
        this.angleMultiplier = Math.random() < 0.5 ? 1 : -1;
        this.score = score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onRegistration(PApplet applet) {
        super.onRegistration(applet);

        position.x = (float) (Math.random() * getPApplet().width);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onFrame() {
        PApplet applet = getPApplet();

        /*applet.pushMatrix();

        float transformX = position.x + (width / 2);
        float transformY = position.y + (height / 2);

        applet.translate(transformX, transformY);
        applet.rotate(PApplet.radians(this.angle));
        applet.translate(-width / 2, -height / 2);

        applet.image(ImageLoader.getInstance(applet).get(imagePath), 0, 0, width, height);

        applet.popMatrix();*/

        applet.tint(255, alpha);

        applet.image(ImageLoader.getInstance(applet).get(imagePath),position.x, position.y, width, height);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onUpdate() {
        super.onUpdate();

        angle += angleMultiplier;

        if(angle >= 360) {
            angle = 0;
        }

        if(doFadeOut) {
            alpha -= 20;
        }
    }
}
