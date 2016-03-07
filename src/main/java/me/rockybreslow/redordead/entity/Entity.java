package me.rockybreslow.redordead.entity;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public abstract class Entity {
    public final PVector gravity = new PVector(0, .5f);

    public PApplet parent;

    public PImage image;

    public PVector position;
    public PVector velocity;

    public Entity(PApplet parent, String image) {
        this.parent = parent;

        this.image = parent.loadImage(image);
    }

    public void display() {
        parent.image(image, position.x, position.y, image.width, image.height);
    }

    public abstract void update();
}
