package org.slgnalin.enhance.command;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.slgnalin.enhance.manager.TeleportRequestManager;

/**
 * <p>
 * Handles the command responsible for denying teleport requests
 * </p>
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