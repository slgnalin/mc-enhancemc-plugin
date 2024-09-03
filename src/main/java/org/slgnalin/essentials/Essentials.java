package org.slgnalin.essentials;

import org.bukkit.plugin.java.JavaPlugin;
import org.slgnalin.essentials.command.TeleportAcceptCommand;
import org.slgnalin.essentials.command.TeleportCancelCommand;
import org.slgnalin.essentials.command.TeleportDenyCommand;
import org.slgnalin.essentials.command.TeleportRequestCommand;
import org.slgnalin.essentials.manager.TeleportRequestManager;
import org.slgnalin.essentials.manager.TeleportRequestManagerImpl;

/**
 * <p>
 * The main plugin class, providing functionality for initializing and managing the required plugin components
 * </p>
 */
public final class Essentials extends JavaPlugin {

    /**
     * The {@link Essentials} plugin instance
     */
    private static final Essentials ESSENTIALS_PLUGIN_INSTANCE = getPlugin(Essentials.class);

    /**
     * Manages teleport requests
     */
    private TeleportRequestManager teleportRequestManager;

    /**
     * <p>
     * This method is called when the plugin is loaded
     * </p>
     */
    @Override
    public void onLoad() {
        this.teleportRequestManager = new TeleportRequestManagerImpl();
    }

    /**
     * This method is called when the plugin is disabled
     */
    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled");
    }

    /**
     * This method is called when the plugin is enabled
     */
    @Override
    public void onEnable() {
        registerCommands();

        getLogger().info("Plugin initialised");
    }

    /**
     * Registers commands with their corresponding executors
     */
    private void registerCommands() {
        getCommand("teleport-request").setExecutor(new TeleportRequestCommand(teleportRequestManager));
        getCommand("teleport-accept").setExecutor(new TeleportAcceptCommand(teleportRequestManager));
        getCommand("teleport-cancel").setExecutor(new TeleportCancelCommand(teleportRequestManager));
        getCommand("teleport-deny").setExecutor(new TeleportDenyCommand(teleportRequestManager));
    }

    /**
     * Gets the instance of the {@link Essentials} plugin
     *
     * @return {@link #ESSENTIALS_PLUGIN_INSTANCE}
     */
    public static Essentials getEssentialsPluginInstance() {
        return ESSENTIALS_PLUGIN_INSTANCE;
    }

}
