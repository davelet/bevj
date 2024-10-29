package org.bevj.app.ecs;

public interface PluginGroup {
    PluginGroupBuilder build();
    
    default String name() {
        return getClass().getSimpleName();
    }
    
    default <T extends Plugin> PluginGroupBuilder set(T plugin) {
        return build().set(plugin);
    }
}
