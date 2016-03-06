package me.rockybreslow.redordead.entity;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public abstract class Entity {
    public static final PVector gravity = new PVector(0, 4f);
    public static final PVector leftAcceleration = new PVector(-4f, 0);
    public static final PVector rightAcceleration = new PVector(4f, 0);
    public static final PVector upAcceleration = new PVector(0, -35f);

    protected PApplet parent;

    protected PImage image;

    public PVector position;
    public PVector velocity;

    public Entity(PApplet parent, String image) {
        this.parent = parent;

        this.image = parent.loadImage(image);
    }

    public void display() {
        parent.image(image, position.x, position.y, image.height, image.width);
    }

    public abstract void move();
}
