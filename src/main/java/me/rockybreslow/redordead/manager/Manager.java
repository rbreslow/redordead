package me.rockybreslow.redordead.manager;

import java.util.ArrayList;
import java.util.List;

public abstract class Manager<T> {
    protected List<T> list = new ArrayList<>();

    public void register(T o) {
        list.add(o);
    }

    public void destroy(T o) {
        list.remove(o);
    }

    public abstract void update();
}
