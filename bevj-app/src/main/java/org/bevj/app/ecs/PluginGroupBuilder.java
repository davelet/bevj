package org.bevj.app.ecs;

import java.util.ArrayList;

import org.bevj.utils.TypeId;
import org.bevj.utils.TypeIdMap;

public class PluginGroupBuilder implements PluginGroup {
    private String groupName;
    private TypeIdMap<PluginEntry> plugins;
    private ArrayList<TypeId> order;

    public <T extends Plugin> PluginGroupBuilder set(T plugin) {
        return this;
    }

    @Override
    public PluginGroupBuilder build() {
        return this;
    }

    // Start a new builder for the [`PluginGroup`].
    public static <PG extends PluginGroup> PluginGroupBuilder start(PG pg) {
        PluginGroupBuilder pluginGroupBuilder = new PluginGroupBuilder();
        pluginGroupBuilder.groupName = pg.name();
        pluginGroupBuilder.plugins = new TypeIdMap<>();
        pluginGroupBuilder.order = new ArrayList<>();
        return pluginGroupBuilder;
    }

    // Finds the index of a target [`Plugin`]. Panics if the target's [`TypeId`] is
    // not found.
    public int indexOf(Class<Plugin> target) {
        for (var i = 0; i < order.size(); i++) {
            if (order.get(i).equals(TypeId.of(target))) {
                return i;
            }
        }
        throw new RuntimeException("Plugin does not exist in group: " + target.getName());
    }

    // Insert the new plugin entry as enabled, and removes its previous ordering if
    // it was already present
    public void upsert_plugin_entry_state(TypeId key,
            PluginEntry plugin,
            int added_at_index) {
        plugins.put(plugin);

        var iter = order.iterator();
        var start = 0;
        while (iter.hasNext()) {
            var ty = iter.next();
            if (ty.equals(key) && start != added_at_index) {
                iter.remove();
            }
            start += 1;
        }
    }

    public void upsert_plugin_state(Plugin plugin, int added_at_index) {
        upsert_plugin_entry_state(
                TypeId.of(plugin.getClass()),
                new PluginEntry(plugin, true),
                added_at_index);
    }

}
