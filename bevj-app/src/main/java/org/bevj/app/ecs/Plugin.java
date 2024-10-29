package org.bevj.app.ecs;

import org.bevj.app.App;

public abstract class Plugin {
    abstract void build(App app);
    
    public boolean ready() {
        return true;
    }

    public void finish() {}

    public void cleanup() {}

    public String name() {
        return getClass().getSimpleName();
    }

    public boolean isUnique() {
        return true;
    }
}
