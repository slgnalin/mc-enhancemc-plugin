package org.slgnalin.essentials.command;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.slgnalin.essentials.manager.TeleportRequestManager;

/**
 * Handles the command responsible for denying teleport requests
 *
 * @see TeleportRequestManager
 */
public class TeleportDenyCommand extends AbstractPlayerCommand {

    private final TeleportRequestManager teleportRequestManager;

    public TeleportDenyCommand(TeleportRequestManager teleportRequestManager) {
        this.teleportRequestManager = teleportRequestManager;
    }

    @Override
    public boolean handleCommand(@NotNull Player player, @NotNull Command command, @NotNull String commandAlias, @NotNull String[] args) {
        teleportRequestManager.denyRequest(player);

        return true;
    }

}