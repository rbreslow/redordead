package me.rockybreslow.redordead.manager;

import java.util.*;
import java.util.function.Predicate;

/**
 * Manager data structure.
 *
 * @param <T> Template for managed data
 *
 * @author Rocky Breslow
 */
public abstract class Manager<T> {
    /** Internal storage for data */
    protected List<T> list = new ArrayList<>();

    /**
     * Get the objects that Manager is managing.
     *
     * @return registrees
     */
    public List<T> getRegistrees() {
        return Collections.unmodifiableList(list);
    }

    /**
     * Register an object.
     *
     * @param o Object to register
     */
    public void register(T o) {
        list.add(o);
    }

    /**
     * Destroy an object.
     *
     * @param o Object to destroy
     */
    public void destroy(T o) {
        if(list.contains(o)) {
            list.remove(o);
        }
    }

    /**
     * Destroy if condition is met on object.
     *
     * @param filter Lambda expression passed current object. Return boolean
     *
     * @return Whether or not something was removed
     */
    public boolean destroyIf(Predicate<T> filter) {
        Objects.requireNonNull(filter);

        boolean removed = false;
        final Iterator<T> each = list.iterator();

        while(each.hasNext()) {
            if(filter.test(each.next())) {
                each.remove();
                removed = true;
            }
        }

        return removed;
    }
}
