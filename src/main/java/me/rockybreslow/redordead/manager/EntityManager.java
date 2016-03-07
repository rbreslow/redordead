package me.rockybreslow.redordead.manager;

import me.rockybreslow.redordead.entity.Entity;

public class EntityManager<T extends Entity> extends Manager<T> {
    private PhysicsManager physicsManager = new PhysicsManager();

    @Override
    public void register(T entity) {
        super.register(entity);
        physicsManager.register(entity);
    }

    @Override
    public void destroy(T entity) {
        super.destroy(entity);
        physicsManager.destroy(entity);
    }

    @Override
    public void update() {
        for(Entity entity : list) {
            entity.display();
            entity.update();
        }

        physicsManager.update();
    }
}
