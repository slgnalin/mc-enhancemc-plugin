package org.slgnalin.essentials.command;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.slgnalin.essentials.manager.TeleportRequestManager;

/**
 * <p>
 * Handles the command responsible for cancelling teleport requests
 * </p>
 *
 * @see TeleportRequestManager
 */
public class TeleportCancelCommand extends AbstractPlayerCommand {

    private final TeleportRequestManager teleportRequestManager;

    public TeleportCancelCommand(TeleportRequestManager teleportRequestManager) {
        this.teleportRequestManager = teleportRequestManager;
    }

    @Override
    public boolean handleCommand(@NotNull Player player, @NotNull Command command, @NotNull String commandAlias, @NotNull String[] args) {
        teleportRequestManager.cancelRequests(player);
        return true;
    }

}
