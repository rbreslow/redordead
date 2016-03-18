package me.rockybreslow.redordead.entity;

import processing.core.PVector;

public abstract class PhysicsEntity extends Entity {
    public final PVector gravity = new PVector(0, .5f);
    public PVector velocity = new PVector(0, 0);

    public int width;
    public int height;

    public PhysicsEntity(float x, float y, int width, int height) {
        super(x, y);

        this.width = width;
        this.height = height;
    }

    public boolean collides(PhysicsEntity entity) {
        return entity.position.x < position.x + width && entity.position.x + entity.width > position.x && entity.position.y < position.y + height && entity.height + entity.position.y > position.y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onUpdate() {
        velocity.mult(.9f);
        velocity.add(gravity);
        position.add(velocity);
    }
}
