package me.rockybreslow.redordead.entity;

import processing.core.PApplet;
import processing.core.PVector;

public class FallingEntity extends Entity {
    public FallingEntity(PApplet parent, String image) {
        super(parent, image);

        this.position = new PVector((int) (Math.random() * parent.width), 0);
        this.velocity = new PVector(0, .2f, 0);
    }

    @Override
    public void move() {
        if ((position.x > parent.width - image.width) || (position.x < 0)) {
            velocity.x = velocity.x * -1;
        }

        if (position.y > parent.height - image.height) {
            velocity.y = (float) (velocity.y * -0.95);
            position.y = parent.height - image.height;
        }
    }
}
