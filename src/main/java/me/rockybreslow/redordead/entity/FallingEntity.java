package me.rockybreslow.redordead.entity;

import processing.core.PApplet;
import processing.core.PVector;

public class FallingEntity extends Entity {
    public FallingEntity(PApplet parent, String image) {
        super(parent, image);

        this.position = new PVector((int) (Math.random() * parent.width), 0);
        this.velocity = new PVector(0, 0);
    }

    @Override
    public void update() {
        if(position.y > parent.height - image.height) {
            
        }
    }
}
