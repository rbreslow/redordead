package me.rockybreslow.redordead.manager;

import me.rockybreslow.redordead.entity.Entity;
import me.rockybreslow.redordead.screen.GameScreen;
import processing.core.PApplet;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;

public class EntityManager<T extends Entity> extends Manager<T> {
    private PApplet applet;

    /**
     * Constructs an EntityManager.
     *
     * @param applet Parent applet
     */
    public EntityManager(PApplet applet) {
        this.applet = applet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void register(T entity) {
        super.register(entity);

        entity.onRegistration(applet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy(T entity) {
        super.destroy(entity);

        entity.onDestruction(applet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean destroyIf(Predicate<T> filter) {
        Objects.requireNonNull(filter);

        boolean removed = false;
        final Iterator<T> each = list.iterator();

        while(each.hasNext()) {
            T entity = each.next();
            if(filter.test(entity)) {
                entity.onDestruction(applet);
                each.remove();
                removed = true;
            }
        }

        return removed;
    }

    /**
     * Called within the main game loop to update all entities.
     *
     * @see GameScreen#onFrame()
     */
    public void update() {
        for (T entity : list) {
            entity.onFrame();
            entity.onUpdate();
        }
    }
}
