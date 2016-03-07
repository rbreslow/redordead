package me.rockybreslow.redordead.entity;

import processing.core.PApplet;
import processing.core.PVector;

public class PlayerEntity extends Entity {
    public PlayerEntity(PApplet parent, String image) {
        super(parent, image);

        this.position = new PVector(this.parent.width / 2 - this.image.width / 2, this.parent.height - this.image.height);
        this.velocity = new PVector(0, 0);
    }

    public boolean isGrounded() {
        return (int) position.y == parent.height - image.height;
    }

    @Override
    public void update() {
        if ((position.x > parent.width - image.width) || (position.x < 0)) {
            position.x = position.x < 0 ? parent.width - image.width : 0;
        }

        if (position.y > parent.height - image.height) {
            velocity.y = 0;
            position.y = parent.height - image.height;
        }
    }
}
