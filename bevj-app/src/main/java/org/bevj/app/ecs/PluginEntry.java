package org.bevj.app.ecs;

public class PluginEntry {
    private Plugin plugin;
    private boolean enabled;

    public PluginEntry(Plugin plugin, boolean enabled) {
        this.plugin = plugin;
        this.enabled = enabled;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PluginEntry that = (PluginEntry) o;
        return enabled == that.enabled && plugin.equals(that.plugin);
    }

    @Override
    public int hashCode() {
        int result = plugin.hashCode();
        result = 31 * result + (enabled ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PluginEntry{" +
                "plugin=" + plugin +
                ", enabled=" + enabled +
                '}';
    }

    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
