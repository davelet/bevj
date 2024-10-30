package org.bevj.app.ecs;

import java.util.ArrayList;

import org.bevj.app.App;
import org.bevj.utils.TypeId;
import org.bevj.utils.TypeIdMap;

public class PluginGroupBuilder implements PluginGroup {
    private String groupName;
    private TypeIdMap<PluginEntry> plugins;
    private ArrayList<TypeId> order;

    /**
     * set when exists in the group
     */
    public <T extends Plugin> PluginGroupBuilder set(T plugin) {
        PluginEntry p = plugins.get(TypeId.of(plugin.getClass()));
        if (p == null) {
            throw new RuntimeException("Plugin does not exist in group: " + plugin.name());
        }
        p.setPlugin(plugin);
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
    public int index_of(Class<Plugin> target) {
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
            start++;
        }
    }

    public void upsert_plugin_state(Plugin plugin, int added_at_index) {
        upsert_plugin_entry_state(
                TypeId.of(plugin.getClass()),
                new PluginEntry(plugin, true),
                added_at_index);
    }

    // Adds the plugin [`Plugin`] at the end of this [`PluginGroupBuilder`].
    public PluginGroupBuilder add(Plugin plugin) {
        var target_index = order.size();
        order.add(TypeId.of(plugin.getClass()));
        this.upsert_plugin_state(plugin, target_index);
        return this;
    }

    // Adds a [`PluginGroup`] at the end of this [`PluginGroupBuilder`]. If the
    // plugin was already in the group, it is removed from its previous place.
    public PluginGroupBuilder add_group(PluginGroup group) {
        PluginGroupBuilder pluginGroupBuilder = group.build();
        var plugins = pluginGroupBuilder.plugins;
        var order = pluginGroupBuilder.order;

        for (var plugin_id : order) {
            this.upsert_plugin_entry_state(
                    plugin_id,
                    plugins.remove(plugin_id),
                    order.size());

            order.add(plugin_id); // FIXME: add in for-each ?
        }

        return this;
    }

    // Adds a [`Plugin`] in this [`PluginGroupBuilder`] before the plugin of type
    // `Target`.
    // If the plugin was already the group, it is removed from its previous place.
    public PluginGroupBuilder add_before(Plugin plugin, Class<Plugin> target) {
        var target_index = this.index_of(target);
        this.order.add(target_index, TypeId.of(plugin.getClass()));
        this.upsert_plugin_state(plugin, target_index);
        return this;
    }

    // Adds a [`Plugin`] in this [`PluginGroupBuilder`] after the plugin of type
    // `Target`.
    // If the plugin was already the group, it is removed from its previous place.
    public PluginGroupBuilder add_after(Plugin plugin, Class<Plugin> target) {
        var target_index = this.index_of(target) + 1;
        this.order.add(target_index, TypeId.of(plugin.getClass()));
        this.upsert_plugin_state(plugin, target_index);
        return this;
    }

    // Enables a [`Plugin`].
    public PluginGroupBuilder enable(Class<Plugin> target) {
        var plugin_entry = this.plugins
                .get(TypeId.of(target));
        if (plugin_entry == null) {
            throw new RuntimeException("Plugin does not exist in group: " + target.getName());
        }
        plugin_entry.setEnabled(true);
        return this;
    }

    // Disables a [`Plugin`], preventing it from being added to the [`App`] with the
    // rest of the [`PluginGroup`]
    public PluginGroupBuilder disable(Class<Plugin> target) {
        var plugin_entry = this.plugins
                .get(TypeId.of(target));
        if (plugin_entry == null) {
            throw new RuntimeException("Plugin does not exist in group: " + target.getName());
        }
        plugin_entry.setEnabled(false);
        return this;
    }

    // Consumes the [`PluginGroupBuilder`] and [builds](Plugin::build) the contained
    // [`Plugin`]s
    // in the order specified.
    //
    // # Panics
    //
    // Panics if one of the plugin in the group was already added to the
    // application.
//     public void finish(App app) {
//         for (var ty : this.order ){
//             if (this.plugins.remove(ty) != null) {
//                 app.add_boxed_plugin(ty.get());
//             }

//     let Some(entry) = self.plugins.remove(ty) {
//                 if entry.enabled {
//                     debug!("added plugin: {}", entry.plugin.name());
//                     if

//     let Err(AppError::DuplicatePlugin { plugin_name }) =
//                         app.add_boxed_plugin(entry.plugin)
//                     {
//                         panic!(
//                             "Error adding plugin {} in group {}: plugin was already added in application",
//                             plugin_name,
//                             self.group_name
//                         );
//                     }
//                 }
// }}}
}
