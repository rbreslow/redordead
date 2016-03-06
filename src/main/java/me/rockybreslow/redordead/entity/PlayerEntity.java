package me.rockybreslow.redordead.entity;

import processing.core.PApplet;
import processing.core.PVector;

public class PlayerEntity extends Entity {
    public PlayerEntity(PApplet parent, String image) {
        super(parent, image);

        this.position = new PVector(this.parent.width / 2 - this.image.width / 2, this.parent.height - this.image.height);
        this.velocity = new PVector(0, 0);
    }

    @Override
    public void move() {
        velocity.add(gravity);
        position.add(velocity);

        velocity.mult(.75f);

        if ((position.x > parent.width - image.width) || (position.x < 0)) {
            velocity.x = velocity.x * -1;
        }

        if (position.y > parent.height - image.height) {
            velocity.y = (float) (velocity.y * -0.95);
            position.y = parent.height - image.height;
        }
    }
}
