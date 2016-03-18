package me.rockybreslow.redordead.entity;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * @author Rocky Breslow
 */
public abstract class Entity {
    /** Entity position */
    public PVector position;
    /** Parent applet */
    private PApplet applet;

    /**
     * Construct's an Entity.
     *
     * @param x entity's x coordinate
     * @param y entity's y coordinate
     */
    public Entity(float x, float y) {
        this.position = new PVector(x, y);
    }

    /**
     * Get's the entity's parent applet.
     *
     * @return p parent applet
     */
    protected PApplet getPApplet() {
        return applet;
    }

    /**
     * Called when Entity is registered with an EntityManager.
     *
     * @param applet Parent applet
     *
     * @see me.rockybreslow.redordead.manager.EntityManager#register(Entity)
     */
    public void onRegistration(PApplet applet) {
        this.applet = applet;
    }

    /**
     * Called when Entity is destroyed with an EntityManager.
     *
     * @param applet Parent applet
     *
     * @see me.rockybreslow.redordead.manager.EntityManager#destroy(Entity)
     */
    public void onDestruction(PApplet applet) {
        this.applet = null;
    }

    /**
     * Called every render frame.
     *
     * @see PApplet#draw()
     */
    public abstract void onFrame();

    /**
     * Called every render frame.
     *
     * @see PApplet#draw()
     */
    public abstract void onUpdate();
}
