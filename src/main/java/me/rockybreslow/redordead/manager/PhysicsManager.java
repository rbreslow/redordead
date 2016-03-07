package me.rockybreslow.redordead.manager;

import me.rockybreslow.redordead.entity.Entity;

public class PhysicsManager extends Manager<Entity> {
    @Override
    public void update() {
        for(Entity entity : list) {
            entity.velocity.mult(.9f);
            entity.velocity.add(0, .5f);
            entity.position.add(entity.velocity);
        }
    }
}
