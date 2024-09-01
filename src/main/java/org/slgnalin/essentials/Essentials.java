package org.slgnalin.essentials;

import org.bukkit.plugin.java.JavaPlugin;
import org.slgnalin.essentials.command.TeleportRequestCommand;

public final class Essentials extends JavaPlugin {
    private TeleportRequestManager teleportRequestManager;

    @Override
    public void onLoad() {
        this.teleportRequestManager = new TeleportRequestManager();
    }

    @Override
    public void onEnable() {

        getCommand("teleport-request").setExecutor(new TeleportRequestCommand(teleportRequestManager));

        getLogger().info("Plugin initialised");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled");
    }

    public static Essentials getInstance(){
        return getPlugin(Essentials.class);
    }

}
