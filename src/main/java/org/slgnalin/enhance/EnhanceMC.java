package org.slgnalin.enhance;

import org.bukkit.plugin.java.JavaPlugin;
import org.slgnalin.enhance.command.TeleportAcceptCommand;
import org.slgnalin.enhance.command.TeleportCancelCommand;
import org.slgnalin.enhance.command.TeleportDenyCommand;
import org.slgnalin.enhance.command.TeleportRequestCommand;
import org.slgnalin.enhance.manager.TeleportRequestManager;
import org.slgnalin.enhance.manager.TeleportRequestManagerImpl;

/**
 * <p>
 * The main plugin class, providing functionality for initializing and managing the required plugin components
 * </p>
 */
public final class EnhanceMC extends JavaPlugin {

    /**
     * The {@link EnhanceMC} plugin instance
     */
    private static final EnhanceMC ENHANCE_MC_PLUGIN_INSTANCE = getPlugin(EnhanceMC.class);

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
     * Gets the instance of the {@link EnhanceMC} plugin
     *
     * @return {@link #ENHANCE_MC_PLUGIN_INSTANCE}
     */
    public static EnhanceMC getInstance() {
        return ENHANCE_MC_PLUGIN_INSTANCE;
    }

}
