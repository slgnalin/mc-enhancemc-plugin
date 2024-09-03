package org.slgnalin.essentials.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.slgnalin.essentials.manager.TeleportRequestManager;

/**
 * Handles the command responsible for cancelling teleport requests
 *
 * @see TeleportRequestManager
 */
public class TeleportCancelCommand implements CommandExecutor {

    private final TeleportRequestManager teleportRequestManager;

    public TeleportCancelCommand(TeleportRequestManager teleportRequestManager) {
        this.teleportRequestManager = teleportRequestManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage("This command can only be executed by a player");
            return true;
        }

        teleportRequestManager.cancelRequests(player);

        return true;
    }

}